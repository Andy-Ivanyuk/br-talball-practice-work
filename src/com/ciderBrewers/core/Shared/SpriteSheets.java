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

    SpriteSheet DEBUG_SPRITE;
    public SpriteSheet PLAYER_SPRITE;
    SpriteSheet BALL_SPRITE;
    public SpriteSheet FAT_SPRITE;

    public SpriteSheet BK1_ANIM;

    private SpriteSheets() throws SlickException {
        DEBUG_SPRITE = new SpriteSheet("data/spr/debug.png", 1, 1);
        PLAYER_SPRITE = new SpriteSheet("data/spr/player1.png", 36, 123);
        BALL_SPRITE = new SpriteSheet("data/spr/ball.png", 32, 32);
        FAT_SPRITE = new SpriteSheet("data/spr/fat.png", 32, 32);

        BK1_ANIM = new SpriteSheet("data/spr/bk1_anim.png", 384, 256);
    }

    public static SpriteSheets getInstance() {
        return (INSTANCE);
    }
}
