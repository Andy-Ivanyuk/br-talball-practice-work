package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;

public class Player extends PhysicsObject {
    int nextStep = 0;
    boolean jump = false;
    private float stepMultiplier = SharedData.PLAYER_WALK_SPEED;
    private float jumpSpeed = SharedData.PLAYER_JUMP_SPEED;
    private int score = 0;

    private GenericObject target;

    public Player(SpriteSheet spriteSheet, GenericObject target) {
        this.setSprite(new Animation(spriteSheet, 300));
        setY(SharedData.SCREEN_HEIGHT - SharedData.STARTING_OFFSET);
        setOriginX((float) getSprite().getWidth() / 2);
        setOriginY(getSprite().getHeight());
        setScale(SharedData.PLAYER_SCALE);
        setWeight(SharedData.PLAYER_WEIGTH);
        setCollider(new Rectangle(
                new Float(getX() - getOriginX() * getScale()).intValue(),
                new Float(getY() - getOriginY() * getScale()).intValue(),
                new Float(getSprite().getWidth() * getScale()).intValue(),
                new Float(getSprite().getHeight() * getScale()).intValue()));
        setSolid(true);
        setFriction(SharedData.PLAYER_FRICTION);
        setBounce(SharedData.PLAYER_BOUNCE);
        this.target = target;
    }

    // Overriding update to process controller commands and calculate sprite facing
    public void update(GameContainer c, int delta) {
        if (isGrounded() && jump) {
            setSpeedY(-jumpSpeed);
        }

        if (nextStep != 0) {
            if (isGrounded()) {
                getSprite().start();
                getSprite().update(delta);
            } else {
                getSprite().stop();
            }
            float deltaX = nextStep * stepMultiplier * delta;
            setX(getX() + deltaX);

        } else {
            getSprite().stop();
        }

        nextStep = 0;
        jump = false;

        if (getX() - target.getX() < 0) setReversed(true);
        else setReversed(false);

        super.update(c, delta);
    }
}
