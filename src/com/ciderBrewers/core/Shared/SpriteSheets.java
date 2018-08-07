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

    // DEBUG
    public SpriteSheet DEBUG_BACKGROUND_DYNAMIC_1;
    public SpriteSheet DEBUG_BACKGROUND_DYNAMIC_2;
    public SpriteSheet DEBUG_BACKGROUND_DYNAMIC_3;
    public SpriteSheet DEBUG_BACKGROUND_DYNAMIC_4;
    public SpriteSheet DEBUG_BACKGROUND_DYNAMIC_5;

    public SpriteSheet DEBUG_SPRITE;

    // BALLS
    public SpriteSheet BALL_SPRITE;
    public SpriteSheet FAT_SPRITE;

    // VALIK
    public SpriteSheet VALIK_IDLE;
    public SpriteSheet VALIK_WALK;
    public SpriteSheet VALIK_JUMP;

    // MCKIDDO
    public SpriteSheet MCKIDDO_IDLE;
    public SpriteSheet MCKIDDO_WALK;
    public SpriteSheet MCKIDDO_JUMP;

    private SpriteSheets() throws SlickException {
        // Throws an exception but still loads picture.
        //Todo: Fix it.
        // DEBUG
        DEBUG_BACKGROUND_DYNAMIC_1 = new SpriteSheet("data/bg/bg_debug_1.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_2 = new SpriteSheet("data/bg/bg_debug_2.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_3 = new SpriteSheet("data/bg/bg_debug_3.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_4 = new SpriteSheet("data/bg/bg_debug_4.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_5 = new SpriteSheet("data/bg/bg_debug_5.png", 800, 600);

        DEBUG_SPRITE = new SpriteSheet("data/spr/debug.png", 1, 1);

        // BALLS
        BALL_SPRITE = new SpriteSheet("data/spr/ball.png", 32, 32);
        FAT_SPRITE = new SpriteSheet("data/spr/fat.png", 32, 32);

        // VALIK
        VALIK_IDLE = new SpriteSheet("data/spr/valik/idle.png", 36, 98);
        VALIK_WALK = new SpriteSheet("data/spr/valik/walk.png", 36, 98);
        VALIK_JUMP = new SpriteSheet("data/spr/valik/jump.png", 36, 98);

        // MCKIDDO
        MCKIDDO_IDLE = new SpriteSheet("data/spr/mckiddo/idle.png", 36, 98);
        MCKIDDO_WALK = new SpriteSheet("data/spr/mckiddo/walk.png", 36, 98);
        MCKIDDO_JUMP = new SpriteSheet("data/spr/mckiddo/jump.png", 36, 98);
    }

    public static SpriteSheets getInstance() {
        return (INSTANCE);
    }
}
