package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Utils.Collider;
import com.ciderBrewers.core.Utils.PlayerAnimationSet;
import com.ciderBrewers.core.Utils.PlayerSet;
import org.newdawn.slick.GameContainer;

public class Player extends PhysicsObject {
    public int nextStep = 0;
    public boolean jump = false;
    private int score = 0;

    private GenericObject target;

    private PlayerAnimationSet animationSet;

    private String name;

    private int side = 0;

    public Player(PlayerSet playerSet, GenericObject target, int side) {
        this.name = playerSet.name;

        this.animationSet = new PlayerAnimationSet(playerSet);

        setSprite(animationSet.idle);

        setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET);

        setOriginX((float) getSprite().getWidth() / 2);
        setOriginY(getSprite().getHeight());

        setScale(SharedData.PLAYER_SCALE);
        setWeight(SharedData.PLAYER_WEIGHT);

        setMaxSpeed(SharedData.MAX_PLAYER_SPEED);

        setCollider(new Collider(
                getX(),
                getY(),
                15 * getScale(),
                95 * getScale(),
                15 * getScale(),
                0 * getScale()));

        setFriction(SharedData.PLAYER_FRICTION);
        setBounce(SharedData.PLAYER_BOUNCE);

        this.target = target;

        this.side = side;

        SharedData.getInstance().players.add(this);
    }

    // Overriding update to process controller commands and calculate sprite facing
    public void update(GameContainer c, int delta) {
        if (isGrounded() && jump) {
            setSpeedY(-SharedData.PLAYER_JUMP_SPEED);
            animationSet.jump.restart();
        }

        if (nextStep != 0) {
            float deltaX = nextStep * SharedData.PLAYER_WALK_SPEED * delta;
            setX(getX() + deltaX);
        }

        setAnimation();

        nextStep = 0;
        jump = false;

        if (getX() - target.getX() < 0) setReversed(false);
        else setReversed(true);

        checkExtendedBounds();

        super.update(c, delta);
    }

    public void checkExtendedBounds() {
        if (side == 1) {
            if (getX() + getCollider().right > SharedData.SCREEN_WIDTH / 2) {
                setX(SharedData.SCREEN_WIDTH / 2 - getCollider().right);
                setSpeedX(getSpeedX() / -getFriction());
            }
        }
        if (side == -1) {
            if (getX() - getCollider().left < SharedData.SCREEN_WIDTH / 2) {
                setX(SharedData.SCREEN_WIDTH / 2 + getCollider().left);
                setSpeedX(getSpeedX() / -getFriction());
            }
        }
    }

    public void setAnimation() {
        if (isGrounded()) {
            if (nextStep == 0) {
                setSprite(animationSet.idle);
            } else {
                int modifier = 1;
                if (isReversed()) modifier = -1;
                if (nextStep * modifier > 0) setSprite(animationSet.walk);
                else setSprite(animationSet.walkBack);
            }
        } else setSprite(animationSet.jump);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
