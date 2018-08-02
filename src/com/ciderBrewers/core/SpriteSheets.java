package com.ciderBrewers.core;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

class SpriteSheets {

    private static SpriteSheets INSTANCE;

    static {
        try {
            INSTANCE = new SpriteSheets();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    SpriteSheet PLAYER_SPRITE;
    SpriteSheet BALL_SPRITE;

    private SpriteSheets() throws SlickException {
        PLAYER_SPRITE = new SpriteSheet("data/spr/NPC.png", 44, 123);
        BALL_SPRITE = new SpriteSheet("data/spr/ball.png", 32, 32);
    }

    static SpriteSheets getInstance() {
        return (INSTANCE);
    }
}
