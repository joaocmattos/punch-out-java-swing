package com.punchout.view;

import com.punchout.engine.GameEngine;
import com.punchout.model.Fighter;
import com.punchout.model.FighterState;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameEngine engine;
    private int shakeMagnitude = 0;
    private int offsetX = 0;
    private int offsetY = 0;

    public void startShake(int intensity, int duration){
        this.shakeMagnitude = intensity;

        Timer shakeTimer = new Timer(20, null);
        long endTime = System.currentTimeMillis() + duration;

        shakeTimer.addActionListener(e -> {
            if (System.currentTimeMillis() < endTime){
                offsetX = (int) (Math.random() * shakeMagnitude * 2) - shakeMagnitude;
                offsetY = (int) (Math.random() * shakeMagnitude * 2) - shakeMagnitude;
                repaint();
            }
            else {
                offsetX = 0;
                offsetY = 0;
                shakeTimer.stop();
                repaint();
            }
        });
        shakeTimer.start();

    }

    public GamePanel(GameEngine engine){
        this.engine = engine;

        Timer repaintTimer = new Timer (16, e -> repaint());
        repaintTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        g2.translate(offsetX, offsetY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(0, 0, getWidth(), getHeight());


        FighterState tysonState = engine.getTyson().getCurrentState();
        drawFighter(g2, 250, 120, 100, 150, tysonState, true);

        FighterState macState = engine.getPlayer().getCurrentState();
        int macPosX = 275;
        if (macState == FighterState.DODGING) {
            macPosX -= 60;
        }
        drawFighter(g2, macPosX, 350, 50, 80, macState, false);


        g2.translate(-offsetX, -offsetY);


        drawHealthBar(g2, 150, 50, 300, 20, engine.getTyson().getHealth(), "Mike Tyson");
        drawHealthBar(g2, 150, 500, 300, 20, engine.getPlayer().getHealth(), "Little Mac");
        drawStaminaBar(g2, 150, 530, 200, 10, engine.getPlayer().getStamina());

        if (engine.isGameOver()) {

            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setFont(new Font("Courier New", Font.BOLD, 50));

            if (engine.getWinner().equals("MAC")) {
                g2.setColor(Color.GREEN);
                g2.drawString("YOU WIN!", 180, 250);
            } else {
                g2.setColor(Color.RED);
                g2.drawString("GAME OVER", 160, 250);
            }

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.WHITE);
            g2.drawString("Final health: " +
                    (engine.getWinner().equals("MAC") ? engine.getPlayer().getHealth() : "0"), 220, 300);
        }
    }

    private void drawFighter(Graphics2D g2, int x, int y, int width, int height, FighterState state, boolean isBoss){
        boolean flashing = isBoss ? engine.getTyson().isInvincible() : engine.getPlayer().isInvincible();
        if (flashing){
            g2.setColor(Color.WHITE);
        }
        else{

            if (state == FighterState.PREPARING) g2.setColor(Color.YELLOW);
            else if (state == FighterState.ATTACKING) g2.setColor(Color.RED);
            else if (state == FighterState.STUNNED) g2.setColor(new Color(255, 0, 255));
            else if (state == FighterState.DODGING) g2.setColor(Color.CYAN);
            else g2.setColor(isBoss ? Color.WHITE : new Color(50, 205, 50));
        }
        g2.fillRect(x, y , width, height);
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, width, height);
        }

    private void drawHealthBar(Graphics2D g2, int x, int y, int maxWidth, int height, int health, String label){
        g2.setColor(new Color(60, 60, 60));
        g2.fillRect(x, y, maxWidth, height);
        if (health > 60) g2.setColor(Color.GREEN);
        else if (health > 30) g2.setColor(Color.ORANGE);
        else g2.setColor(Color.RED);

        int currentWidth = (int) ((health / 100.0) * maxWidth);
        g2.fillRect(x, y, currentWidth, height);
        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, maxWidth, height);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString(label+": " + health +"% ", x, y - 5);
    }

    public void drawStaminaBar(Graphics2D g2, int x, int y, int maxWidth, int height, int stamina){
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x, y, maxWidth, height);

        g2.setColor(engine.getPlayer().isExhausted() ? Color.LIGHT_GRAY : Color.CYAN);

        int currentWidth = (int) ((stamina / 100.0) * maxWidth);
        g2.fillRect(x, y, currentWidth, height);
    }


}
