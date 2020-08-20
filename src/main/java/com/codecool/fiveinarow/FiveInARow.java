package com.codecool.fiveinarow;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FiveInARow {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(10, 10);
//        game.enableAi(1);
//        game.enableAi(2);
        game.play(5);


    }
}
