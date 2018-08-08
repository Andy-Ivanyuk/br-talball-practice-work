package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;

import java.awt.*;

public class GenericObject {
    private float x = 0;
    private float y = 0;
    private float rotation = 0;
    private boolean reversed = false;
    private Animation sprite;
    private float originX = 0;
    private float originY = 0;
    private float scale = 1f;
    private boolean isSolid = false;
    private Rectangle collider;

    public GenericObject() {
        //SharedData.getInstance().genericObjects.add(this);
    }

    // Sync collider coordinates and clamp rotation angle
    public void update(GameContainer gameContainer, int delta) {
        collider.x = new Float(x - originX * scale).intValue();
        collider.y = new Float(y - originY * scale).intValue();

        if (getRotation() < 0) setRotation(getRotation() + 360);
        if (getRotation() >= 360) setRotation(getRotation() - 360);

        if (sprite != null) sprite.update(delta);
    }

    // Draw object's sprite
    public void draw(float[] parallaxOffset) {
        // Debug collider drawing
        /*
        SharedResources.getInstance().DEBUG_SPRITE.draw(
                collider.x - parallaxOffset[0],
                collider.y - parallaxOffset[1],
                collider.width,
                collider.height);
        */

        if (getSprite() != null) {
            int side = reversed ? -1 : 1;

            if (reversed) getSprite().getCurrentFrame().setCenterOfRotation(-originX * scale, originY * scale);
            else getSprite().getCurrentFrame().setCenterOfRotation(originX * scale, originY * scale);
            getSprite().getCurrentFrame().setRotation(rotation);

            getSprite().draw(
                    getX() - getOriginX() * scale * side - parallaxOffset[0],
                    getY() - getOriginY() * scale - parallaxOffset[1],
                    getSprite().getWidth() * scale * side,
                    getSprite().getHeight() * scale);
        }
    }

    public float[] getParallaxOffset() {
        float[] parallaxOffset = {0, 0};
        parallaxOffset[0] = (x - (float) SharedData.SCREEN_WIDTH / 2) / ((float) SharedData.SCREEN_WIDTH / 2) * SharedData.PARALLAX_VALUE;
        parallaxOffset[1] = (y - (float) SharedData.SCREEN_HEIGHT / 2) / ((float) SharedData.SCREEN_HEIGHT / 2) * SharedData.PARALLAX_VALUE;
        return parallaxOffset;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    float getRotation() {
        return rotation;
    }

    void setRotation(float rotation) {
        this.rotation = rotation;
    }

    boolean isReversed() {
        return reversed;
    }

    void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    Animation getSprite() {
        return sprite;
    }

    void setSprite(Animation sprite) {
        this.sprite = sprite;
    }

    public float getOriginX() {
        return originX;
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public float getOriginY() {
        return originY;
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    float getScale() {
        return scale;
    }

    void setScale(float scale) {
        this.scale = scale;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }

    boolean isSolid() {
        return isSolid;
    }

    void setSolid(boolean solid) {
        isSolid = solid;
    }
}
