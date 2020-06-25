package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.GameContainer;

public class PhysicsObject extends GenericObject {
    private float speedX = 0;
    private float speedY = 0;
    private float maxSpeed = SharedData.MAX_SPEED;
    private float momentum = 0;
    private float weight = 1;
    private float friction = SharedData.FRICTION;
    private float bounce = SharedData.BOUNCE;
    private boolean physicsEnabled = true;
    private boolean touchedWall = false;

    PhysicsObject() {
        //SharedData.getInstance().physicsObjects.add(this);
    }

    // Overriding update to add move, gravity and rotation support.
    public void update(GameContainer c, int delta) {
        touchedWall = false;

        if (physicsEnabled) {
            speedY += SharedData.GRAVITY_ACCELERATION * weight * delta;
            setX(getX() + speedX * delta);
            setY(getY() + speedY * delta);

            clampSpeed();

            boundsCheck();

            setRotation(getRotation() + momentum * delta);
        }

        // Continue update (sync collider coordinates and clamp rotation)
        super.update(c, delta);
    }

    // Clamp object speed to avoid infinite acceleration
    private void clampSpeed() {
        if (speedX > maxSpeed) speedX = maxSpeed;
        if (speedX < -maxSpeed) speedX = -maxSpeed;
        if (speedY > maxSpeed) speedY = maxSpeed;
        if (speedY < -maxSpeed) speedY = -maxSpeed;
    }

    // Snap to bounds
    private void boundsCheck() {
        if (getX() - getCollider().left < 0) {
            setX(getCollider().left);
            speedX /= -friction;
            touchedWall = true;
        }
        if (getX() + getCollider().right > SharedData.SCREEN_WIDTH) {
            setX(SharedData.SCREEN_WIDTH - getCollider().right);
            speedX /= -friction;
            touchedWall = true;
        }
        if (getY() + getCollider().down > SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET) {
            setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET - getCollider().down);
            speedY = -speedY * bounce;
            speedX /= friction;
            touchedWall = true;
        }
    }

    public boolean isGrounded() {
        return (getY() + getCollider().down >= SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET);
    }

    float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    float getMomentum() {
        return momentum;
    }

    void setMomentum(float momentum) {
        this.momentum = momentum;
    }

    float getWeight() {
        return weight;
    }

    void setWeight(float weight) {
        this.weight = weight;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public float getBounce() {
        return bounce;
    }

    public void setBounce(float bounce) {
        this.bounce = bounce;
    }

    public boolean isPhysicsEnabled() {
        return physicsEnabled;
    }

    public void setPhysicsEnabled(boolean physicsEnabled) {
        this.physicsEnabled = physicsEnabled;
    }

    public boolean didTouchWall() {
        return touchedWall;
    }
}
