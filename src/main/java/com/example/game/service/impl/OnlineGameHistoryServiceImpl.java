package com.example.game.service.impl;

import com.example.game.entity.GameHistory;
import com.example.game.entity.OnlineGameHistory;
import com.example.game.mapper.OnlineGameHistoryMapper;
import com.example.game.service.OnlineGameHistoryService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class OnlineGameHistoryServiceImpl implements OnlineGameHistoryService {

    @Resource
    OnlineGameHistoryMapper onlineGameHistoryMapper;

    @Override
    public void saveOnlineGameHistory(OnlineGameHistory onlineGameHistory) {
        onlineGameHistoryMapper.insertGameHistory(onlineGameHistory);
    }

    @Override
    public void deleteOnlineGameHistory(String gameId) {
        onlineGameHistoryMapper.deleteGameHistory(gameId);
    }

    @Override
    public OnlineGameHistory getOnlineGameHistoryById(String gameId) {
        return onlineGameHistoryMapper.selectGameHistoryById(gameId);
    }

    @Override
    public List<GameHistory> getOnlineGameHistoryByUserAccount(String userAccount) {
        List<GameHistory> gameHistories = new ArrayList<>();
        List<OnlineGameHistory> his = onlineGameHistoryMapper.selectGameHistoriesByUserAccount(userAccount);
        for (OnlineGameHistory h : his) {
            GameHistory gameHistory = new GameHistory();
            gameHistory.setGameId(h.getGameId());
            gameHistory.setStartTime(h.getStartTime());
            gameHistory.setGameTime(h.getGameTime());
            if (h.getWinner().equals(userAccount)) {
                gameHistory.setState(1);
            } else {
                gameHistory.setState(0);
            }
            gameHistories.add(gameHistory);
        }
        return gameHistories;
    }

    @Override
    public int countWinsByUser(String userAccount) {
        return 0;
    }
}
