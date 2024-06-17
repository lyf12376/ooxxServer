package com.example.game.service;

import com.example.game.common.Response;
import com.example.game.entity.RankGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;

public interface RankGameService {
    void WaitGame(String userAccount, String userName);

    void matching(RankGame rankGame, DeferredResult<ResponseEntity<Response<List<RankGame>>>> output);

    void matchingSuccess(String userAccount1, String userAccount2);
}
