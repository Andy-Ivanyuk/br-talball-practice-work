package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Shared.SharedResources;
import com.ciderBrewers.core.Utils.Collider;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

public class Ball extends PhysicsObject {

    ArrayList<Sound> punchSounds = new ArrayList<>();
    ArrayList<Sound> wallSounds = new ArrayList<>();
    long lastTouch = 0;

    public Ball(float x, float y, SpriteSheet spriteSheet) {
        setSprite(new Animation(spriteSheet, 100));
        setX(x);
        setY(y);
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

        setPhysicsEnabled(false);

        punchSounds.addAll(SharedResources.getInstance().BALL_PUNCH_SOUNDS);
        wallSounds.addAll(SharedResources.getInstance().BALL_WALL_SOUNDS);

        SharedData.getInstance().balls.add(this);
    }

    //Overriding update to check for player collision and create momentum.
    public void update(GameContainer c, int delta) {
        // Check for player collision
        for (Player player : SharedData.getInstance().players) {
            if (getCollider().intersects(player.getCollider())) {
                playPunchSound();
                setPhysicsEnabled(true);

                int direction = 1;
                if (getX() - player.getX() < 0) direction = -1;

                setSpeedX(getSpeedX() + SharedData.PLAYER_HORIZONTAL_FORCE * direction * delta);
                setSpeedY(getSpeedY() - SharedData.PLAYER_VERTICAL_FORCE * delta);

                lastTouch = System.currentTimeMillis();
            }
        }
        for (GenericObject object : SharedData.getInstance().genericObjects) {
            if (getCollider().intersects(object.getCollider())) {
                playWallSound();
                float horizontalDelta = getCollider().x - getCollider().left - object.getCollider().x - object.getCollider().left;
                float verticalDelta = getCollider().y - getCollider().up - object.getCollider().y - object.getCollider().up;

                if (verticalDelta < 0) setSpeedY(-getSpeedY() / SharedData.FRICTION);
                else setSpeedX(-getSpeedX() / SharedData.FRICTION);

                lastTouch = System.currentTimeMillis();
            }
        }

        // Creating momentum based on player x-speed.
        setMomentum(getSpeedX());

        // Continue update
        super.update(c, delta);

        if (didTouchWall()) {
            playWallSound();
        }
    }

    private void playPunchSound() {
        if (System.currentTimeMillis() - lastTouch >= SharedData.SOUND_COOLDOWN_MS) {
            int index = (int) (Math.random() * punchSounds.size());
            punchSounds.get(index).play();
        }
    }

    private void playWallSound() {
        if (System.currentTimeMillis() - lastTouch >= SharedData.SOUND_COOLDOWN_MS) {
            int index = (int) (Math.random() * wallSounds.size());
            wallSounds.get(index).play();
        }
    }
}
