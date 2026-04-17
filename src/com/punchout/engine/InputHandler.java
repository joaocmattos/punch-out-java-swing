package com.punchout.engine;

import com.punchout.model.Fighter;
import com.punchout.model.FighterState;
import com.punchout.model.LittleMac;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class InputHandler implements KeyListener {
    private LittleMac player;
    private GameEngine engine;

    public InputHandler(LittleMac player, GameEngine engine){
        this.player = player;
        this.engine = engine;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
            player.setCurrentState(FighterState.DODGING);
            Timer returnTimer = new Timer(500, ev -> {
                player.setCurrentState(FighterState.IDLE);
            });
            returnTimer.setRepeats(false);
            returnTimer.start();
            System.out.println("Little Mac: Dodging!");
        }

        if (code == KeyEvent.VK_SPACE) {
            player.setCurrentState(FighterState.ATTACKING);
            System.out.println("Little Mac: PUNCH!!");
            engine.checkPlayerAttack();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
