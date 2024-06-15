package com.example.game.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("game_history")
public class GameHistory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userAccount;
    private String gameId;
    private String startTime;
    private String gameTime;
    private int state;
}
