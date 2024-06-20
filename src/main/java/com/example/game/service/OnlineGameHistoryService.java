package com.example.game.service;

import com.example.game.entity.GameHistory;
import com.example.game.entity.OnlineGameHistory;

import java.util.List;

public interface OnlineGameHistoryService {
    void saveOnlineGameHistory(OnlineGameHistory onlineGameHistory);

    void deleteOnlineGameHistory(String gameId);

    OnlineGameHistory getOnlineGameHistoryById(String gameId);

    List<GameHistory> getOnlineGameHistoryByUserAccount(String userAccount);

    int countWinsByUser(String userAccount);



}
