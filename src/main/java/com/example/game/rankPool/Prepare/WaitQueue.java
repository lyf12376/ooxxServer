package com.example.game.rankPool.Prepare;

import com.example.game.entity.RankGame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitQueue {
    RankGame rankGame;
    int status1;
    RankGame rankGame2;
    int status2;
    //0:等待接受 1:接受 2:拒绝
}
