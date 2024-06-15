package com.example.game.mapper;

import com.example.game.entity.PassNumRank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PassNumRankMapper {
    @Select("SELECT * FROM pass_num_rank")
    List<PassNumRank> getPassNumRank();

    @Insert("INSERT INTO pass_num_rank(user_account, user_name, pass_num) VALUES(#{userAccount}, #{userName}, #{passNum})")
    void insertPassNum(String userAccount, String userName, int passNum);

    @Update("UPDATE pass_num_rank SET pass_num = #{passNum} WHERE user_account = #{userAccount}")
    void updatePassNum(String userAccount, String userName, int rank, int passNum);
}
