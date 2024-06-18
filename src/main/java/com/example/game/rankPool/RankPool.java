package com.example.game.rankPool;

import com.example.game.common.Response;
import com.example.game.entity.RankGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public enum RankPool {
    INSTANCE;

    private ConcurrentLinkedQueue<RankQueue> rankQueue;

    RankPool() {
        rankQueue = new ConcurrentLinkedQueue<>();
    }

    public ConcurrentLinkedQueue<RankQueue> getRankQueue() {
        return rankQueue;
    }

    public void addRankQueue(RankQueue queue) {
        rankQueue.add(queue);
    }
}
