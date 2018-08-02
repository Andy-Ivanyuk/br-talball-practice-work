package com.ciderBrewers.core;

import org.newdawn.slick.Animation;

class GenericObject {
    private float x = 0;
    private float y = 0;
    private Animation sprite;
    private float originX = 0;
    private float originY = 0;
    private float scale = 1f;

    GenericObject() {
    }

    void draw(boolean reversed) {
        int side = reversed ? 1 : -1;
        getSprite().draw(
                getX() - getOriginX() * scale * side,
                getY() - getOriginY() * scale,
                getSprite().getWidth() * scale * side,
                getSprite().getHeight() * scale);
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

    void setY(float y) {
        this.y = y;
    }

    Animation getSprite() {
        return sprite;
    }

    void setSprite(Animation sprite) {
        this.sprite = sprite;
    }

    private float getOriginX() {
        return originX;
    }

    void setOriginX(float originX) {
        this.originX = originX;
    }

    private float getOriginY() {
        return originY;
    }

    void setOriginY(float originY) {
        this.originY = originY;
    }

    float getScale() {
        return scale;
    }

    void setScale(float scale) {
        this.scale = scale;
    }
}
