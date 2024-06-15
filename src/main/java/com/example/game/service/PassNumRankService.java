package com.example.game.service;

import com.example.game.entity.PassNumRank;

import java.util.List;

public interface PassNumRankService {

    List<PassNumRank> getPassNumRank();

    void insertPassNum(String userAccount, String userName,int passNum);

    void updatePassNum(String userAccount, String userName,int rank,int passNum);
}
