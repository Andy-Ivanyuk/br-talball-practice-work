package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SpriteSheet;

public class Player extends PhysicsObject {
    public int nextStep = 0;
    public boolean jump = false;
    private float stepMultiplier = SharedData.PLAYER_WALK_SPEED;
    private float jumpSpeed = SharedData.PLAYER_JUMP_SPEED;
    private int score = 0;

    private GenericObject target;

    private Animation animIdle;
    private Animation animWalk;
    private Animation animJump;

    public Player(SpriteSheet idleSheet, SpriteSheet walkSheet, SpriteSheet jumpSheet, GenericObject target) {
        this.animIdle = new Animation(idleSheet, 300);
        this.animWalk = new Animation(walkSheet, 300);
        this.animJump = new Animation(jumpSheet, 300);
        this.animJump.setLooping(false);

        setSprite(animIdle);

        setY(SharedData.SCREEN_HEIGHT - SharedData.STARTING_OFFSET);

        setOriginX((float) getSprite().getWidth() / 2);
        setOriginY(getSprite().getHeight());

        setScale(SharedData.PLAYER_SCALE);
        setWeight(SharedData.PLAYER_WEIGTH);

        setMaxSpeed(SharedData.MAX_PLAYER_SPEED);

        setCollider(new Collider(
                getX(),
                getY(),
                15 * getScale(),
                95 * getScale(),
                15 * getScale(),
                0 * getScale()));
        setSolid(true);

        setFriction(SharedData.PLAYER_FRICTION);
        setBounce(SharedData.PLAYER_BOUNCE);

        this.target = target;

        SharedData.getInstance().players.add(this);
    }

    // Overriding update to process controller commands and calculate sprite facing
    public void update(GameContainer c, int delta) {
        if (isGrounded() && jump) {
            setSpeedY(-jumpSpeed);
            animJump.restart();
        }

        if (nextStep != 0) {
            float deltaX = nextStep * stepMultiplier * delta;
            setX(getX() + deltaX);
        }

        setAnimation();

        nextStep = 0;
        jump = false;

        if (getX() - target.getX() < 0) setReversed(false);
        else setReversed(true);

        super.update(c, delta);
    }

    public void setAnimation() {
        if (isGrounded()) {
            if (nextStep == 0) setSprite(animIdle);
            else setSprite(animWalk);
        } else setSprite(animJump);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
