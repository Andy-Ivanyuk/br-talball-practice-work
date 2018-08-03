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
    public SpriteSheet DEBUG_BACKGROUND;

    public SpriteSheet PLAYER_SPRITE;
    public SpriteSheet BALL_SPRITE;
    public SpriteSheet FAT_SPRITE;

    public SpriteSheet BG1_ANIM;

    private SpriteSheets() throws SlickException {
        DEBUG_SPRITE = new SpriteSheet("data/spr/debug.png", 1, 1);
        DEBUG_BACKGROUND = new SpriteSheet("data/spr/bg_debug.png", 800, 600);

        PLAYER_SPRITE = new SpriteSheet("data/spr/player1.png", 36, 123);
        BALL_SPRITE = new SpriteSheet("data/spr/ball.png", 32, 32);
        FAT_SPRITE = new SpriteSheet("data/spr/fat.png", 32, 32);

        BG1_ANIM = new SpriteSheet("data/spr/bg1_anim.png", 384, 256);
    }

    public static SpriteSheets getInstance() {
        return (INSTANCE);
    }
}
