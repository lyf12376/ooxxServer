package com.example.game.controller;


import com.example.game.common.Response;
import com.example.game.entity.PassNumRank;
import com.example.game.entity.RankGame;
import com.example.game.service.impl.RankGameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/game/rank")
public class RankGameController {
    @Resource
    RankGameServiceImpl rankGameService;

    @Resource
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/matching")
    public DeferredResult<ResponseEntity<Response<List<RankGame>>>> startMatching(@RequestBody RankGame rankGame) {
        DeferredResult<ResponseEntity<Response<List<RankGame>>>> output = new DeferredResult<>();
        rankGameService.matching(rankGame, output);
        return output;
    }

    @PostMapping("/cancel")
    public void cancelMatching(@RequestBody RankGame rankGame) {
        rankGameService.cancelMatching(rankGame);
    }

    @PostMapping("/start")
    public void startGame(@RequestBody RankGame rankGame) {

    }



}
