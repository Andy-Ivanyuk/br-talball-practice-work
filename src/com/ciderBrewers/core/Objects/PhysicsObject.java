package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.GameContainer;

public class PhysicsObject extends GenericObject {
    private float speedX = 0;
    private float speedY = 0;
    private float maxSpeed = 0.5f;
    private float momentum = 0;
    private float weight = 1;

    PhysicsObject() {
    }

    // Overriding update to add move, gravity and rotation support.
    public void update(GameContainer c, int delta) {
        speedY += SharedData.GRAVITY_ACCELERATION * weight * delta;
        setX(getX() + speedX * delta);
        setY(getY() + speedY * delta);

        clampSpeed();

        boundsCheck();

        setRotation(getRotation() + momentum * delta);

        // Continue update (sync collider coordinates and clamp rotation)
        super.update();
    }

    // Clamp object speed to avoid infinite acceleration
    private void clampSpeed() {
        if (speedX > maxSpeed) speedX = maxSpeed;
        if (speedX < -maxSpeed) speedX = -maxSpeed;
        if (speedY > maxSpeed) speedY = maxSpeed;
        if (speedY < -maxSpeed) speedY = -maxSpeed;
    }

    // Check if object is in bounds. If not - SNAP it BACK TO REALITY. OH, THERE GOES GRAVITY!
    private void boundsCheck() {
        if (getX() - getCollider().width / 2 < 0) {
            setX((float) getCollider().width / 2);
            speedX = -speedX / SharedData.FRICTION;
        }
        if (getX() + getCollider().width / 2 > SharedData.SCREEN_WIDTH) {
            setX(SharedData.SCREEN_WIDTH - (float) getCollider().width / 2);
            speedX = -speedX / SharedData.FRICTION;
        }
        if (getY() + getCollider().height / 2 > SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET) {
            setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET - getCollider().height / 2);
            speedY = -speedY / SharedData.FRICTION;
            speedX /= SharedData.FRICTION;
        }
    }

    float getSpeedX() {
        return speedX;
    }

    void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    float getSpeedY() {
        return speedY;
    }

    void setSpeedY(float speedY) {
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
}
