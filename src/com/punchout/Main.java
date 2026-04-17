package com.punchout;

import com.punchout.engine.GameEngine;
import com.punchout.view.GameWindow;

public class Main {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        new GameWindow(engine.getPlayer(), engine   ) ;
        engine.startFight();

        System.out.println("Game Window is now active, first try!");
    }
}