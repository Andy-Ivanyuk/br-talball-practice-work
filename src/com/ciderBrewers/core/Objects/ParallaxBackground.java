package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class ParallaxBackground {
    private Animation sprite;
    private float depth;

    public ParallaxBackground(SpriteSheet spriteSheet, float depth) {
        this.sprite = new Animation(spriteSheet, 300);

        this.depth = depth;

        SharedData.getInstance().backgrounds.add(this);
    }

    public void draw(float[] parallaxOffset) {
        if (parallaxOffset != null) {
            sprite.draw(
                    -SharedData.PARALLAX_VALUE - parallaxOffset[0] / depth,
                    -SharedData.PARALLAX_VALUE - parallaxOffset[1] / depth,
                    SharedData.SCREEN_WIDTH + SharedData.PARALLAX_VALUE * 2,
                    SharedData.SCREEN_HEIGHT + SharedData.PARALLAX_VALUE * 2
            );
        } else {
            sprite.draw(0, 0, SharedData.SCREEN_WIDTH, SharedData.SCREEN_HEIGHT);
        }
    }

    public Animation getSprite() {
        return sprite;
    }

    public void setSprite(Animation sprite) {
        this.sprite = sprite;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }
}
