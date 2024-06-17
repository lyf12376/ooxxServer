package com.example.game.mapper;

import com.example.game.entity.RankGame;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RankGameMapper {
    @Insert("INSERT INTO rank_game(user_account, user_name) VALUES(#{userAccount}, #{userName})")
    void startRanking(String userAccount, String userName);

    @Delete("DELETE FROM rank_game WHERE user_account = #{userAccount}")
    void cancelRanking(String userAccount);

    @Select("SELECT * FROM rank_game")
    List<RankGame> getCompetitor();
}
