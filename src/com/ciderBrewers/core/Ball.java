package com.ciderBrewers.core;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

class Ball extends PhysicsObject {

    Ball(SpriteSheet spriteSheet) {
        setSprite(new Animation(spriteSheet, 300));
        setX(100);
        setY(100);
        setSpeedX(0.5f);
        setOriginX((float) getSprite().getWidth() / 2);
        setOriginY((float) getSprite().getHeight() / 2);
        setScale(SharedData.BALL_SCALE);
    }

    void draw() {
        draw(false);
    }
}
