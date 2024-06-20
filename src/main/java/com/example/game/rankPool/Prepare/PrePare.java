package com.example.game.rankPool.Prepare;

import com.example.game.entity.RankGame;
import com.example.game.socket.SocketServer;
import com.example.game.utils.GenerateGameId;
import com.example.game.utils.GridGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
@Component
public class PrePare {
    private static final Logger logger = LoggerFactory.getLogger(PrePare.class);
    private Executor executorService;

    @Autowired
    public void Prepare(@Qualifier("taskExecutor") Executor executorService) {
        this.executorService = executorService;
    }

    @Async
    @Scheduled(fixedDelay = 1000)
    public void performTask() {
        ConcurrentLinkedQueue<WaitQueue> waitQueue = WaitPool.INSTANCE.getWaitQueue();
        try {
            Iterator<WaitQueue> iterator = waitQueue.iterator();
            while (iterator.hasNext()) {
                WaitQueue queueItem = iterator.next();
                if (queueItem != null) {
                    logger.info("Processing waitQueue: {}", queueItem);
                    // 根据条件判断是否移除
                    if (shouldRemove(queueItem) == 1) {
                        iterator.remove();
                        logger.info("Removed waitQueue: {}", queueItem);
                        startMatch(queueItem.rankGame, queueItem.rankGame2);
                    } else if ( shouldRemove(queueItem) == 2){
                        iterator.remove();
                        logger.info("Removed waitQueue: {}", queueItem);
                        rejectMatch(queueItem.rankGame, queueItem.rankGame2);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while performing tasks", e);
        }
    }

    private int shouldRemove(WaitQueue queueItem) {
        if (queueItem.status1 == 2 || queueItem.status2 == 2)
            return 2;
        if (queueItem.status1 == 1 && queueItem.status2 == 1)
            return 1;
        else return 0;
    }

    public void startMatch(RankGame rankGame1, RankGame rankGame2) {
        //准备开始游戏：分配对局Id，创建格子
        String gameId = new GenerateGameId().generateGameId();
        String grid = GridGenerator.generateGrid();

        SocketServer.getClientHandler(rankGame1.getUserAccount()).sendMessage("ok/"+gameId+"/"+grid);
        SocketServer.getClientHandler(rankGame2.getUserAccount()).sendMessage("ok/"+gameId+"/"+grid);


        logger.info("GameId: {}, Grid: {}", gameId, grid);
    }

    public void rejectMatch(RankGame rankGame1, RankGame rankGame2) {
        //拒绝匹配：返回匹配失败信息

    }




}
