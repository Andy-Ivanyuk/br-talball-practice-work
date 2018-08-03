package com.ciderBrewers.core.Objects;

import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class ParallaxBackground {
    private Animation sprite;

    public ParallaxBackground(SpriteSheet spriteSheet) {
        this.sprite = new Animation(spriteSheet, 10);
        this.sprite.setPingPong(true);
        this.sprite.setLooping(false);
    }

    public void draw(float[] parallaxOffset) {
        sprite.draw(
                -SharedData.PARALLAX_VALUE - parallaxOffset[0],
                -SharedData.PARALLAX_VALUE - parallaxOffset[1],
                SharedData.SCREEN_WIDTH + SharedData.PARALLAX_VALUE * 2,
                SharedData.SCREEN_HEIGHT + SharedData.PARALLAX_VALUE * 2
        );
    }

    Animation getSprite() {
        return sprite;
    }

    void setSprite(Animation sprite) {
        this.sprite = sprite;
    }
}
