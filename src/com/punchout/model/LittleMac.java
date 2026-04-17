package com.punchout.model;

public class LittleMac extends Fighter {
    private int stamina;
    private boolean exhausted = false;

    public LittleMac(){
        super("Little Mac", 100);
    }

    public void spendStamina(int amount){
        stamina -= amount;
        if (stamina <= 0){
            stamina = 0;
            exhausted = true;

            new javax.swing.Timer(2000, e -> {
                stamina = 100;
                exhausted = false;
            }).start();
        }
    }
    public void recoverStamina(int amount){
        if (!exhausted && stamina < 100) stamina += amount;
    }

    public int getStamina(){
        return stamina;
    }

    public boolean isExhausted(){
        return exhausted;
    }
}
