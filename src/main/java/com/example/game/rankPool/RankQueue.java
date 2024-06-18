package com.example.game.rankPool;

import com.example.game.common.Response;
import com.example.game.entity.RankGame;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
@Data

public class RankQueue {
    RankGame rankGame;
    DeferredResult<ResponseEntity<Response<List<RankGame>>>> output;

    public RankQueue(RankGame rankGame, DeferredResult<ResponseEntity<Response<List<RankGame>>>> output) {
        this.rankGame = rankGame;
        this.output = output;
    }
}
