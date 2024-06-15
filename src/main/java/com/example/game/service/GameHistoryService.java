package com.example.game.service;

import com.example.game.entity.GameHistory;


import java.util.List;
public interface GameHistoryService {
    List<GameHistory> getGameHistoryByUserAccount(String userAccount);

    void insertGameHistory(GameHistory gameHistory);
}
