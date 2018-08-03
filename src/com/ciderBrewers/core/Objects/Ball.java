package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;

public class Ball extends PhysicsObject {

    public Ball(SpriteSheet spriteSheet) {
        setSprite(new Animation(spriteSheet, 100));
        setX(400);
        setY(500);
        setSpeedX(0.0f);
        setOriginX((float) getSprite().getWidth() / 2);
        setOriginY((float) getSprite().getHeight() / 2);
        setScale(SharedData.BALL_SCALE);
        setCollider(new Rectangle(
                new Float(getX() - getOriginX() * getScale()).intValue(),
                new Float(getY() - getOriginX() * getScale()).intValue(),
                new Float(getSprite().getWidth() * getScale()).intValue(),
                new Float(getSprite().getHeight() * getScale()).intValue()));
        setSolid(true);
    }

    //Overriding update to check for player collision and create momentum.
    public void update(GameContainer c, int delta) {
        // Check for player collision
        for (Player player : SharedData.getInstance().players) {
            if (getCollider().intersects(player.getCollider())) {
                int direction = 1;
                if (getX() - player.getX() < 0) direction = -1;

                setSpeedX(getSpeedX() + SharedData.PLAYER_HORIZONTAL_FORCE * direction);
                setSpeedY(getSpeedY() - SharedData.PLAYER_VERTICAL_FORCE);
            }
        }
        for (GenericObject object : SharedData.getInstance().genericObjects) {
            if (getCollider().intersects(object.getCollider())) {
                setSpeedX(-getSpeedX());
            }
        }

        // Creating momentum based on player x-speed.
        setMomentum(getSpeedX());

        // Continue update
        super.update(c, delta);
    }
}
