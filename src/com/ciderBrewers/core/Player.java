package com.ciderBrewers.core;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Player {
    private int xDir;
    private int yDir;
    private int scores;
    private SpriteSheet playerSprite;
    private Animation playerAnimation;
    private int position;
    private int orientation;

    public Player(SpriteSheet playerSprite, int x, int y, int position) {
        this.xDir = x;
        this.yDir = y;
        scores = 0;
        this.playerSprite = playerSprite;
        this.playerAnimation = new Animation(playerSprite, 300);
        this.position = position;
        if (position == 0) {
            this.orientation = 44;
        } else {
            this.orientation = -44;
        }
    }

    public void draw() {
        playerAnimation.draw(xDir, yDir, orientation, 123);
    }

    public double getXDir() {
        return xDir;
    }
    public void setXDir(int xDir) {
        this.xDir += xDir;
    }
    public double getYDir() {
        return yDir;
    }
    public void setYDir(int yDir) {
        this.yDir += yDir;
    }
    public int getScores() {
        return scores;
    }
    public void setScores(int scores) {
        this.scores = scores;
    }
    public SpriteSheet getPlayerSprite() {
        return playerSprite;
    }
    public void setPlayerSprite(SpriteSheet playerSprite) {
        this.playerSprite = playerSprite;
    }
}
