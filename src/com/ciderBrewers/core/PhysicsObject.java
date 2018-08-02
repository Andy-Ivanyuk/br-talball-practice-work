package com.ciderBrewers.core;

public class PhysicsObject extends GenericObject {
    private float speedX = 0;
    private float speedY = 0;
    private float weight = 1;

    PhysicsObject() {
    }

    void update(int delta) {
        speedY += SharedData.GRAVITY_ACCELERATION * weight * delta;
        setX(getX() + speedX * delta);
        setY(getY() + speedY * delta);
        boundsCheck();
    }

    private void boundsCheck() {
        if (getX() - getSprite().getWidth() * getScale() / 2 < 0) {
            setX(getSprite().getWidth() * getScale() / 2);
            speedX = -speedX / SharedData.FRICTION;
        }
        if (getX() + getSprite().getWidth() * getScale() / 2 > SharedData.SCREEN_WIDTH) {
            setX(SharedData.SCREEN_WIDTH - getSprite().getWidth() * getScale() / 2);
            speedX = -speedX / SharedData.FRICTION;
        }
        if (getY() + getSprite().getHeight() * getScale() / 2 > SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET) {
            setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET - getSprite().getHeight() * getScale() / 2);
            speedY = -speedY / SharedData.FRICTION;
            speedX /= SharedData.FRICTION;
        }
    }

    public float getSpeedX() {
        return speedX;
    }

    void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
