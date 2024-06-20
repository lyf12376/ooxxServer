package com.example.game.mapper;

import com.example.game.entity.OnlineGameHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OnlineGameHistoryMapper {

    @Insert("INSERT INTO online_game_history (game_id, grid, init, user_account1, user_account2, user_nick_name1, user_nick_name2, start_time, game_time, winner) " +
            "VALUES (#{gameId}, #{grid}, #{init}, #{userAccount1}, #{userAccount2}, #{userNickName1}, #{userNickName2}, #{startTime}, #{gameTime}, #{winner})")
    void insertGameHistory(OnlineGameHistory gameHistory);

    @Delete("DELETE FROM online_game_history WHERE game_id = #{gameId}")
    void deleteGameHistory(@Param("gameId") String gameId);

    @Select("SELECT * FROM online_game_history WHERE game_id = #{gameId}")
    OnlineGameHistory selectGameHistoryById(@Param("gameId") String gameId);

    @Select("SELECT * FROM online_game_history WHERE user_account1 = #{userAccount} OR user_account2 = #{userAccount}")
    List<OnlineGameHistory> selectGameHistoriesByUserAccount(@Param("userAccount") String userAccount);

    @Select("SELECT * FROM online_game_history WHERE start_time BETWEEN #{startTime} AND #{endTime}")
    List<OnlineGameHistory> selectGameHistoriesByTimeRange(@Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT COUNT(*) FROM online_game_history WHERE winner = #{userAccount}")
    int countWinsByUser(@Param("userAccount") String userAccount);

}
