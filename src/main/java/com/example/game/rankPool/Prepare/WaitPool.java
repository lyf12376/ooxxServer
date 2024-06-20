package com.example.game.rankPool.Prepare;

import com.example.game.entity.RankGame;

import java.util.concurrent.ConcurrentLinkedQueue;

public enum WaitPool {
    INSTANCE;

    private final ConcurrentLinkedQueue<WaitQueue> waitQueue;

    WaitPool() {
        waitQueue = new ConcurrentLinkedQueue<>();
    }

    public ConcurrentLinkedQueue<WaitQueue> getWaitQueue() {
        return waitQueue;
    }

    public void addWaitQueue(RankGame rankGame, RankGame rankGame2) {
        waitQueue.add(new WaitQueue(rankGame,0, rankGame2,0));
    }
}
