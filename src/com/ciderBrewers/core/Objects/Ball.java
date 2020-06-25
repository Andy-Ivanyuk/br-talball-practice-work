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
    boolean touchEnabled = true;
    long collisionTimer = 0;
    int frame = 0;

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

        setPhysicsEnabled(false);

        punchSounds.addAll(SharedResources.getInstance().BALL_PUNCH_SOUNDS);
        wallSounds.addAll(SharedResources.getInstance().BALL_WALL_SOUNDS);

        SharedData.getInstance().balls.add(this);
    }

    //Overriding update to check for player collision and create momentum.
    public void update(GameContainer c, int delta) {
        if (touchEnabled) {
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
                    /*
                    float horizontalDelta = getCollider().x - getCollider().left - object.getCollider().x - object.getCollider().left;
                    float verticalDelta = getCollider().y - getCollider().up - object.getCollider().y - object.getCollider().up;

                    if (verticalDelta < 0) setSpeedY(-getSpeedY() / SharedData.FRICTION);
                    else setSpeedX(-getSpeedX() / SharedData.FRICTION);
                    */

                    boolean left = false;
                    boolean right = false;
                    boolean top = false;
                    boolean bottom = false;

                    if (object.getCollider().intersectsLine(
                            getCollider().x - getCollider().left,
                            getCollider().y - getCollider().up,
                            getCollider().x - getCollider().left,
                            getCollider().y + getCollider().down))
                        left = true;
                    if (object.getCollider().intersectsLine(
                            getCollider().x + getCollider().right,
                            getCollider().y - getCollider().up,
                            getCollider().x + getCollider().right,
                            getCollider().y + getCollider().down))
                        right = true;
                    if (object.getCollider().intersectsLine(
                            getCollider().x - getCollider().left,
                            getCollider().y - getCollider().up,
                            getCollider().x + getCollider().right,
                            getCollider().y - getCollider().up))
                        top = true;
                    if (object.getCollider().intersectsLine(
                            getCollider().x - getCollider().left,
                            getCollider().y + getCollider().down,
                            getCollider().x + getCollider().right,
                            getCollider().y + getCollider().down))
                        bottom = true;

                    if (left || right) {
                        if (left)
                            setX(getX() - ((getCollider().x - getCollider().left) - (object.getCollider().x + object.getCollider().right)));
                        if (right)
                            setX(getX() + ((object.getCollider().x - object.getCollider().left) - (getCollider().x + getCollider().right)));
                        setSpeedX(-getSpeedX() / SharedData.FRICTION);
                    } else {
                        if (bottom)
                            setY(getY() + ((object.getCollider().y - object.getCollider().up) - (getCollider().y + getCollider().down)));
                        if (top)
                            setY(getY() - ((getCollider().y - getCollider().up) - (object.getCollider().y + object.getCollider().down)));
                        setSpeedY(-getSpeedY() / SharedData.FRICTION);
                    }

                    lastTouch = System.currentTimeMillis();
                }
            }
        }

        if (collisionTimer != 0) {
            if (System.currentTimeMillis() - collisionTimer < SharedData.BALL_COOLDOWN_MS) {
                if (frame <= SharedData.BALL_COOLDOWN_FLASH_CYCLE_FRAMES / 2) {
                    setVisible(false);
                } else {
                    setVisible(true);
                }
            } else {
                setVisible(true);
                setTouchEnabled(true);
                collisionTimer = 0;
            }
        }

        frame += 1;
        if (frame == SharedData.BALL_COOLDOWN_FLASH_CYCLE_FRAMES) frame -= SharedData.BALL_COOLDOWN_FLASH_CYCLE_FRAMES;

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

    public void startCollisionTimer() {
        collisionTimer = System.currentTimeMillis();
        frame = 0;
    }

    public boolean isTouchEnabled() {
        return touchEnabled;
    }

    public void setTouchEnabled(boolean touchEnabled) {
        this.touchEnabled = touchEnabled;
    }
}
