package com.example.game.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pass_num_rank")
public class PassNumRank {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userAccount;
    private String userName;
    private Integer passNum;
}
