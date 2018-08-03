package com.ciderBrewers.core;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;

class Ball extends PhysicsObject {

    Ball(SpriteSheet spriteSheet) {
        setSprite(new Animation(spriteSheet, 100));
        setX(400);
        setY(500);
        setSpeedX(0.0f);
        setOriginX((float) getSprite().getWidth() / 2);
        setOriginY((float) getSprite().getHeight() / 2);
        setScale(SharedData.BALL_SCALE);
        setCollider(new Rectangle(
                new Float(getX()).intValue(),
                new Float(getY()).intValue(),
                new Float(getSprite().getWidth() * getScale()).intValue(),
                new Float(getSprite().getHeight() * getScale()).intValue()));
        setSolid(true);
    }

    //Overriding update to check for player collision and create momentum.
    void update(GameContainer c, int delta) {
        // Check for player collision
        for (Player player : SharedData.getInstance().players) {
            if (getCollider().intersects(player.getCollider())) {
                setSpeedX(getSpeedX() + (getX() - player.getX()) / 1000);
                setSpeedY(getSpeedY() - 0.05f);
                if (SharedData.getInstance().background != null) {
                    SharedData.getInstance().background.getSprite().start();
                }
            }
        }

        // Creating momentum based on player x-speed.
        setMomentum(getSpeedX());

        // Continue update
        super.update(c, delta);
    }
}
