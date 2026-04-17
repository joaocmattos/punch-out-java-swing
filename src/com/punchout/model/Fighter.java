package com.punchout.model;

public abstract class Fighter {
    protected String name;
    protected int health;
    protected FighterState currentState;
    private boolean isInvincible = false;

    public Fighter(String name, int health){
        this.name = name;
        this.health = health;
        this.currentState = FighterState.IDLE;
    }

    public void takeDamage(int amount){
        this.health -= amount;
        if (this.health <= 0){
            this.health = 0;
            this.currentState = FighterState.DEFEATED;
        }
    }

    public int getHealth() {
        return health;
    }

    public FighterState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(FighterState currentState) {
        this.currentState = currentState;
    }

    public void triggerHitFlash(){
        this.isInvincible = true;
        javax.swing.Timer flashTimer = new javax.swing.Timer(100, e -> {
            this.isInvincible = false;
        });
        flashTimer.setRepeats(false);
        flashTimer.start();
    }
    public boolean isInvincible(){
        return isInvincible;
    }

}
