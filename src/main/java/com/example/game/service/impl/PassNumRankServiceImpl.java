package com.example.game.service.impl;

import com.example.game.entity.PassNumRank;
import com.example.game.mapper.PassNumRankMapper;
import com.example.game.service.PassNumRankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PassNumRankServiceImpl implements PassNumRankService {

    @Resource
    PassNumRankMapper passNumRankMapper;
    @Override
    public List<PassNumRank> getPassNumRank() {
        return passNumRankMapper.getPassNumRank();
    }

    @Override
    public void insertPassNum(String userAccount, String userName, int passNum) {

    }

    @Override
    public void updatePassNum(String userAccount, String userName, int rank, int passNum) {

    }
}
