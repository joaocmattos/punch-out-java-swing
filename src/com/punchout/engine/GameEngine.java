package com.punchout.engine;

import javax.swing.Timer;
import com.punchout.model.FighterState;
import com.punchout.model.LittleMac;
import com.punchout.model.MikeTyson;
import com.punchout.view.GamePanel;

public class GameEngine {
    private MikeTyson tyson;
    private LittleMac player;
    private GamePanel panel;


    private boolean gameOver = false;
    private String winner = "";

    private Timer aiTimer;

    public GameEngine() {
        this.player = new LittleMac();
        this.tyson = new MikeTyson();
    }

    public void setPanel(GamePanel panel){
        this.panel = panel;
    }

    public void startFight() {
        aiTimer = new Timer(2000, e -> tysonAttackRoutine());
        aiTimer.start();
    }

    public void tysonAttackRoutine() {

        if (gameOver || tyson.getCurrentState() == FighterState.STUNNED || tyson.getHealth() <= 0) return;

        tyson.setCurrentState(FighterState.PREPARING);

        Timer hitTimer = new Timer(800, e -> {
            if (tyson.getCurrentState() == FighterState.PREPARING) {
                tyson.setCurrentState(FighterState.ATTACKING);
                checkCollision();
            }
        });
        hitTimer.setRepeats(false);
        hitTimer.start();
    }

    private void checkCollision() {
        if (tyson.getCurrentState() != FighterState.ATTACKING) return;

        if (player.getCurrentState() == FighterState.DODGING) {
            tyson.setCurrentState(FighterState.STUNNED);
            aiTimer.stop();

            Timer recoverTime = new Timer(1500, e -> {
                tyson.setCurrentState(FighterState.IDLE);
                if (!gameOver) aiTimer.restart(); // Só reinicia se o jogo não acabou
            });
            recoverTime.setRepeats(false);
            recoverTime.start();
        } else {
            player.takeDamage(28);
            player.triggerHitFlash();
            if (panel != null) panel.startShake(10, 300);

            tyson.setCurrentState(FighterState.IDLE);

            if (player.getHealth() <= 0) {
                gameOver = true;
                winner = "TYSON";
                aiTimer.stop();
            }
        }
    }

    public void checkPlayerAttack() {
        if (gameOver || player.isExhausted()) return;

        if (player.getCurrentState() == FighterState.ATTACKING) {
            player.spendStamina(15);
            if (tyson.getCurrentState() == FighterState.STUNNED ||
                    tyson.getCurrentState() == FighterState.PREPARING) {

                tyson.takeDamage(10);
                tyson.triggerHitFlash();
                if (panel != null) panel.startShake(4, 150);

                tyson.setCurrentState(FighterState.IDLE);
                aiTimer.restart();
            }


            if (tyson.getHealth() <= 0) {
                gameOver = true;
                winner = "MAC";
                aiTimer.stop();
            }
        }
    }


    public boolean isGameOver() { return gameOver; }
    public String getWinner() { return winner; }

    public MikeTyson getTyson() { return tyson; }
    public LittleMac getPlayer() { return player; }
}