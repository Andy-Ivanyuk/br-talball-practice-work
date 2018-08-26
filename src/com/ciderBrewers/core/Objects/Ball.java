package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SpriteSheet;

public class Ball extends PhysicsObject {

    public Ball(SpriteSheet spriteSheet) {
        setSprite(new Animation(spriteSheet, 100));
        setX((float) SharedData.SCREEN_WIDTH / 2);
        setY(100);
        setSpeedX(0.0f);
        setOriginX((float) getSprite().getWidth() / 2);
        setOriginY((float) getSprite().getHeight() / 2);
        setScale(SharedData.BALL_SCALE);
        setCollider(new Collider(
                getX(),
                getY(),
                getSprite().getWidth() * getScale() / 2,
                getSprite().getWidth() * getScale() / 2,
                getSprite().getHeight() * getScale() / 2,
                getSprite().getHeight() * getScale() / 2));
        setSolid(true);

        SharedData.getInstance().balls.add(this);
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
                float horizontalDelta = getCollider().x - getCollider().left - object.getCollider().x - object.getCollider().left;
                float verticalDelta = getCollider().y - getCollider().up - object.getCollider().y - object.getCollider().up;

                if (verticalDelta < 0) setSpeedY(-getSpeedY() / SharedData.FRICTION);
                else setSpeedX(-getSpeedX() / SharedData.FRICTION);
            }
        }

        // Creating momentum based on player x-speed.
        setMomentum(getSpeedX());

        // Continue update
        super.update(c, delta);
    }
}
