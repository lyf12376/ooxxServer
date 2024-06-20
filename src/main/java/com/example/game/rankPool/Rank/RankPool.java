package com.example.game.rankPool.Rank;

import java.util.concurrent.ConcurrentLinkedQueue;

public enum RankPool {
    INSTANCE;

    private final ConcurrentLinkedQueue<RankQueue> rankQueue;

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
