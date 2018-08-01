package com.ciderBrewers.core;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

class Player extends GenericObject {
    int nextStep = 0;
    private float stepMultiplier = 0.2f;
    private Animation playerAnimation;
    private int score = 0;

    Player(SpriteSheet spriteSheet) {
        this.playerAnimation = new Animation(spriteSheet, 300);
    }

    void update(int delta) {
        if (nextStep != 0) {
            playerAnimation.start();
            playerAnimation.update(delta);
            float deltaX = nextStep * stepMultiplier * delta;
            setX(checkStep(deltaX));
            nextStep = 0;
        } else {
            playerAnimation.stop();
        }
    }

    void draw() {
        int side = 1;
        if (getX() < SharedData.SCREEN_WIDTH / 2) side = -1;
        playerAnimation.draw(
                getX() - (playerAnimation.getWidth() * SharedData.PLAYER_SCALE / 2) * side,
                getY() - playerAnimation.getHeight() * SharedData.PLAYER_SCALE,
                playerAnimation.getWidth() * SharedData.PLAYER_SCALE * side,
                playerAnimation.getHeight() * SharedData.PLAYER_SCALE);
    }

    private float checkStep(float deltaX) {
        if (getX() + deltaX - playerAnimation.getWidth() * SharedData.PLAYER_SCALE / 2 <= 0) {
            return playerAnimation.getWidth() * SharedData.PLAYER_SCALE / 2;
        }
        if (getX() + deltaX + playerAnimation.getWidth() * SharedData.PLAYER_SCALE / 2 >= SharedData.SCREEN_WIDTH) {
            return SharedData.SCREEN_WIDTH - playerAnimation.getWidth() * SharedData.PLAYER_SCALE / 2;
        }
        return getX() + deltaX;
    }
}
