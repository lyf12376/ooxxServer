package com.example.game.mapper;


import com.example.game.entity.GameHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GameHistoryMapper {
    @Select("SELECT * FROM game_history WHERE user_account = #{useraccount}")
    List<GameHistory> getUserGameHistoryByUserAccount(String useraccount);
}
