package com.example.game.mapper;


import com.example.game.entity.GameHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GameHistoryMapper {
    @Select("SELECT * FROM game_history WHERE user_account = #{userAccount}")
    List<GameHistory> getUserGameHistoryByUserAccount(String userAccount);

    @Insert("INSERT INTO game_history(user_account, game_id, start_time, game_time,state) VALUES(#{userAccount}, #{gameId}, #{startTime}, #{gameTime}, #{state})")
    void insertGameHistory(GameHistory gameHistory);
}
