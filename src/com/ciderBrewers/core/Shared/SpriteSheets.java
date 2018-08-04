package com.ciderBrewers.core.Shared;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SpriteSheets {

    private static SpriteSheets INSTANCE;

    static {
        try {
            INSTANCE = new SpriteSheets();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public SpriteSheet DEBUG_SPRITE;
    public SpriteSheet DEBUG_BACKGROUND_STATIC;
    public SpriteSheet DEBUG_BACKGROUND_DYNAMIC_1;
    public SpriteSheet DEBUG_BACKGROUND_DYNAMIC_2;
    public SpriteSheet DEBUG_BACKGROUND_DYNAMIC_3;

    public SpriteSheet PLAYER_SPRITE;
    public SpriteSheet BALL_SPRITE;
    public SpriteSheet FAT_SPRITE;

    public SpriteSheet BG1_ANIM;

    private SpriteSheets() throws SlickException {
        DEBUG_SPRITE = new SpriteSheet("data/spr/debug.png", 1, 1);

        // Throws an exception but still loads picture.
        //Todo: Fix it.
        DEBUG_BACKGROUND_STATIC = new SpriteSheet("data/spr/bg_debug.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_1 = new SpriteSheet("data/spr/bg_debug_1.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_2 = new SpriteSheet("data/spr/bg_debug_2.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_3 = new SpriteSheet("data/spr/bg_debug_3.png", 800, 600);

        PLAYER_SPRITE = new SpriteSheet("data/spr/player1.png", 36, 123);
        BALL_SPRITE = new SpriteSheet("data/spr/ball.png", 32, 32);
        FAT_SPRITE = new SpriteSheet("data/spr/fat.png", 32, 32);

        BG1_ANIM = new SpriteSheet("data/spr/bg1_anim.png", 384, 256);
    }

    public static SpriteSheets getInstance() {
        return (INSTANCE);
    }
}
