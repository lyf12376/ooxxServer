package com.example.game.controller;

import com.example.game.common.Response;
import com.example.game.entity.GameHistory;
import com.example.game.entity.PassNumRank;
import com.example.game.service.impl.PassNumRankServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/game/passnumrank")
public class PassNumRankCtroller {

    @Resource
    PassNumRankServiceImpl passNumRankService;

    @GetMapping("/rank")
    public ResponseEntity<Response<List<PassNumRank>>> getPassNumRank(HttpServletRequest request) {
        List<PassNumRank> result = passNumRankService.getPassNumRank();
        return ResponseEntity.ok(new Response<>(200,"成功",result));
    }


}
