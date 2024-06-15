package com.example.game.service.impl;

import com.example.game.entity.GameHistory;
import com.example.game.mapper.GameHistoryMapper;
import com.example.game.service.GameHistoryService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
@Service
public class GameHistoryServiceImpl implements GameHistoryService {
    @Resource
    GameHistoryMapper gameHistoryMapper;

    @Override
    public List<GameHistory> getGameHistoryByUserAccount(String userAccount) {
        return gameHistoryMapper.getUserGameHistoryByUserAccount(userAccount);
    }

    @Override
    public void insertGameHistory(GameHistory gameHistory) {
        gameHistoryMapper.insertGameHistory(gameHistory);
    }
}
