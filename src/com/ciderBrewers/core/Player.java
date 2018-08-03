package com.ciderBrewers.core;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;

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
        setCollider(new Rectangle(
                new Float(getX() - getOriginX() * getScale()).intValue(),
                new Float(getY() - getOriginY() * getScale()).intValue(),
                new Float(getSprite().getWidth() * getScale()).intValue(),
                new Float(getSprite().getHeight() * getScale()).intValue()));
        setSolid(true);
    }

    // Overriding update to process controller commands and calculate sprite facing
    void update(GameContainer c, int delta) {
        if (nextStep != 0) {
            getSprite().start();
            getSprite().update(delta);
            float deltaX = nextStep * stepMultiplier * delta;
            setX(checkStep(deltaX));
            nextStep = 0;
        } else {
            getSprite().stop();
        }

        if (getX() < SharedData.SCREEN_WIDTH / 2) setReversed(false);
        else setReversed(true);

        // Todo: Fix this hardcoded piece of crap
        getCollider().x = new Float(getX() - 4).intValue();
        getCollider().y = new Float(getY() - getOriginY() * getScale() + 32).intValue();
    }

    // Calculate next step and return next player position
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
