package com.ciderBrewers.core;

public class GenericObject {
    private float x = 0;
    private float y = SharedData.SCREEN_HEIGHT;

    GenericObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    GenericObject() {
    }

    float getX() {
        return x;
    }

    void setX(float x) {
        this.x = x;
    }

    float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
