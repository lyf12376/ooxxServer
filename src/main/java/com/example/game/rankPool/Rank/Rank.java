package com.example.game.rankPool.Rank;

import com.example.game.common.Response;
import com.example.game.entity.RankGame;
import com.example.game.rankPool.Prepare.WaitPool;
import com.example.game.rankPool.Prepare.WaitQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@Component
public class Rank {

    private static final Logger logger = LoggerFactory.getLogger(Rank.class);
    private final Executor executorService;

    @Autowired
    public Rank(@Qualifier("taskExecutor") Executor executorService) {
        this.executorService = executorService;
    }

    @Async
    @Scheduled(fixedDelay = 1000) // 增加任务调度频率
    public void performTask() {
        ConcurrentLinkedQueue<RankQueue> rankQueue = RankPool.INSTANCE.getRankQueue();
        List<Future<?>> futures = new ArrayList<>(); // 用于存储所有的Future对象

        try {
            while (!rankQueue.isEmpty()) { // 处理队列中的所有请求
                ConcurrentLinkedQueue<RankQueue> group = new ConcurrentLinkedQueue<>();
                for (int i = 0; i < 40 && !rankQueue.isEmpty(); i++) {
                    RankQueue queueItem = rankQueue.poll();
                    if (queueItem != null) {
                        group.add(queueItem);
                    }
                }
                // 提交任务到线程池
                Future<?> future = ((ThreadPoolTaskExecutor) executorService).submit(() -> processGroup(group));
                futures.add(future);
            }
            // 等待所有任务完成
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (Exception e) {
            logger.error("Error occurred while performing tasks", e);
        }
    }

    private void processGroup(ConcurrentLinkedQueue<RankQueue> group) {
        try {
            while (group.size() >= 2) { // 处理队列中的所有请求
                ArrayList<RankGame> gameList = new ArrayList<>();
                RankQueue competitor1 = group.poll();
                RankQueue competitor2 = group.poll();
                ConcurrentLinkedQueue<WaitQueue> waitQueue = WaitPool.INSTANCE.getWaitQueue();
                if (competitor1 != null && competitor2 != null) {
                    waitQueue.add(new WaitQueue(competitor1.rankGame, 0,competitor2.rankGame,0));
                }
                if (competitor1 != null && competitor2 != null) {
                    gameList.add(competitor1.rankGame);
                    gameList.add(competitor2.rankGame);
                    competitor1.getOutput().setResult(ResponseEntity.ok(new Response<>(200, "匹配成功", gameList)));
                    competitor2.getOutput().setResult(ResponseEntity.ok(new Response<>(200, "匹配成功", gameList)));
                }
            }
            ConcurrentLinkedQueue<RankQueue> rankQueue = RankPool.INSTANCE.getRankQueue();
            rankQueue.addAll(group);
        } catch (Exception e) {
            logger.error("Error occurred while processing group", e);
        }
    }
}