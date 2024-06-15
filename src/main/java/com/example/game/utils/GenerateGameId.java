package com.example.game.utils;

import java.util.Random;

public class GenerateGameId {
    public String generateGameId() {
        long time = System.currentTimeMillis();
        int random = new Random(time).nextInt(89999)+10000;
        return String.valueOf(time) + random;
    }
}
