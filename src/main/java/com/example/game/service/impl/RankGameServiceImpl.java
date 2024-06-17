package com.example.game.service.impl;

import com.example.game.common.Response;
import com.example.game.entity.RankGame;
import com.example.game.mapper.RankGameMapper;
import com.example.game.service.RankGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RankGameServiceImpl implements RankGameService {
    @Resource
    private RankGameMapper rankGameMapper;

    private Boolean stopMatching = false;

    @Override
    public void WaitGame(String userAccount, String userName) {
        rankGameMapper.startRanking(userAccount, userName);
    }

    @Override
    public void matching(RankGame rankGame, DeferredResult<ResponseEntity<Response<List<RankGame>>>> output) {
        boolean isMatched = false;
        List<RankGame> gameList = new ArrayList<>();
        while (!isMatched) {
            try {
                List<RankGame> waiting = rankGameMapper.getCompetitor();
                if (waiting.size() > 1) {
                    for (int i = 0; i < waiting.size() / 2; i++) {
                        RankGame competitor1 = waiting.get(2*i);
                        RankGame competitor2 = waiting.get(2*i+1);
                        new Thread(() -> matchingSuccess(competitor1.getUserAccount(), competitor2.getUserAccount())).start();
                        if (competitor1.getUserAccount().equals(rankGame.getUserAccount()) || competitor2.getUserAccount().equals(rankGame.getUserAccount())) {
                            gameList.add(competitor1);
                            gameList.add(competitor2);
                            output.setResult(ResponseEntity.ok(new Response<>(200, "匹配成功", gameList)));
                        }
                    }
                    isMatched = true;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void matchingSuccess(String userAccount1, String userAccount2) {
        rankGameMapper.cancelRanking(userAccount1);
        rankGameMapper.cancelRanking(userAccount2);

    }

    public void stopMatching() {
        stopMatching = true;
    }
}
