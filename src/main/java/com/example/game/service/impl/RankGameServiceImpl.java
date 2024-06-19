package com.example.game.service.impl;

import com.example.game.common.Response;
import com.example.game.entity.RankGame;
import com.example.game.mapper.RankGameMapper;
import com.example.game.rankPool.RankPool;
import com.example.game.rankPool.RankQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @Override
    public void matching(RankGame rankGame, DeferredResult<ResponseEntity<Response<List<RankGame>>>> output) {
        Logger logger = LoggerFactory.getLogger(RankGameServiceImpl.class);
        logger.error(rankGame.toString());
        RankPool.INSTANCE.addRankQueue(new RankQueue(rankGame, output));
    }

    @Override
    public void cancelMatching(RankGame rankGame) {
        RankPool.INSTANCE.getRankQueue().removeIf(rankQueue -> rankQueue.getRankGame().getUserAccount().equals(rankGame.getUserAccount()));
    }


}
