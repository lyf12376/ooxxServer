package com.example.game.controller;

import com.example.game.entity.GameHistory;
import com.example.game.common.Response;
import com.example.game.service.GameHistoryService;
import com.example.game.service.impl.GameHistoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/game/history")
public class GameHistoryController {
    @Resource
    GameHistoryServiceImpl gamehistoryService;
    @GetMapping("/histories")
    public ResponseEntity<Response<List<GameHistory>>> getGameHistoryByUserAccount(@RequestParam String userAccount) {
        List<GameHistory> result = gamehistoryService.getGameHistoryByUserAccount(userAccount);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(404, "未找到记录", null));
        }
        return ResponseEntity.ok(new Response<>(200, "成功", result));
    }

    @PostMapping("/insert")
    public ResponseEntity<Response<String>> insertGameHistory(@RequestBody GameHistory gamehistory) {
        try {
            gamehistoryService.insertGameHistory(gamehistory);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>(200, "插入成功", ""));
        } catch (Exception e) {
            // 捕获并处理可能的异常，返回具体的错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(500, "插入失败: " + e.getMessage(), ""));
        }
    }
}
