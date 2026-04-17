package com.punchout.view;



import com.punchout.engine.GameEngine;
import com.punchout.engine.InputHandler;
import com.punchout.model.LittleMac;

import javax.swing.*;

public class GameWindow  extends JFrame {

    public GameWindow(LittleMac player, GameEngine engine){
        setTitle("Mini Punch-Out! - MVP");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GamePanel gamePanel = new GamePanel(engine);
        this.add(gamePanel);
        engine.setPanel(gamePanel);
        this.addKeyListener(new InputHandler(player, engine));
        this.setFocusable(true);
        this.requestFocusInWindow();
        setVisible(true);
    }
}
