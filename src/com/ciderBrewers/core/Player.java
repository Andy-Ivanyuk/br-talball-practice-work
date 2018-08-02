package com.ciderBrewers.core;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

class Player extends GenericObject {
    int nextStep = 0;
    private float stepMultiplier = 0.2f;
    private int score = 0;

    Player(SpriteSheet spriteSheet) {
        this.setSprite(new Animation(spriteSheet, 300));
        setY(SharedData.SCREEN_HEIGHT - SharedData.STARTING_OFFSET);
        setOriginX((float) getSprite().getWidth() / 2);
        setOriginY(getSprite().getHeight() - 5);
        setScale(SharedData.PLAYER_SCALE);
    }

    void update(int delta) {
        if (nextStep != 0) {
            getSprite().start();
            getSprite().update(delta);
            float deltaX = nextStep * stepMultiplier * delta;
            setX(checkStep(deltaX));
            nextStep = 0;
        } else {
            getSprite().stop();
        }
    }

    void draw() {
        if (getX() < SharedData.SCREEN_WIDTH / 2) draw(false);
        else draw(true);
    }

    private float checkStep(float deltaX) {
        if (getX() + deltaX - getSprite().getWidth() * getScale() / 2 <= 0) {
            return getSprite().getWidth() * getScale() / 2;
        }
        if (getX() + deltaX + getSprite().getWidth() * getScale() / 2 >= SharedData.SCREEN_WIDTH) {
            return SharedData.SCREEN_WIDTH - getSprite().getWidth() * getScale() / 2;
        }
        return getX() + deltaX;
    }
}
