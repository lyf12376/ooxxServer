package com.example.game.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("online_game_history")
public class OnlineGameHistory {
    @TableId
    private String gameId;
    private String grid;
    private String init;
    private String userAccount1;
    private String userAccount2;
    private String userNickName1;
    private String userNickName2;
    private String startTime;
    private int gameTime;
    private String winner;
}
