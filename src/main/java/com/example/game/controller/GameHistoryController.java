package com.example.game.controller;

import com.example.game.entity.GameHistory;
import com.example.game.common.Response;
import com.example.game.service.GameHistoryService;
import com.example.game.service.impl.GameHistoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/game/history")
public class GameHistoryController {
    @Resource
    GameHistoryServiceImpl gamehistoryService;
    @GetMapping("/histories")
    public ResponseEntity<Response<List<GameHistory>>> selectAll(HttpServletRequest request, @RequestBody GameHistory gamehistory) {
        List<GameHistory> result = gamehistoryService.getGameHistoryByUserAccount(gamehistory.getUserAccount());
        return ResponseEntity.ok(new Response<>(200,"成功",result));
    }
}
