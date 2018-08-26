package com.ciderBrewers.core.Shared;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class SharedResources {

    private static SharedResources INSTANCE;

    static {
        try {
            INSTANCE = new SharedResources();
        } catch (SlickException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    // COMMON
    public SpriteSheet SHADOW_SPRITE;

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

    // SILVER
    public SpriteSheet SILVER_IDLE;
    public SpriteSheet SILVER_WALK;
    public SpriteSheet SILVER_JUMP;

    // UI
    public SpriteSheet BATTLE_UI;
    public SpriteSheet L_GOAL_UI;
    public SpriteSheet R_GOAL_UI;

    // FONTS
    public TrueTypeFont BATTLE_UI_NAME;
    public TrueTypeFont BATTLE_UI_GOAL;
    public TrueTypeFont BATTLE_UI_SCORE;

    // MUSIC
    public Music MENU_MUSIC;

    private SharedResources() throws SlickException, IOException, FontFormatException {
        // Throws an exception but still loads picture.
        // Todo: Fix it.
        // DEBUG
        DEBUG_BACKGROUND_DYNAMIC_1 = new SpriteSheet("data/bg/bg_debug_1.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_2 = new SpriteSheet("data/bg/bg_debug_2.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_3 = new SpriteSheet("data/bg/bg_debug_3.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_4 = new SpriteSheet("data/bg/bg_debug_4.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_5 = new SpriteSheet("data/bg/bg_debug_5.png", 800, 600);

        DEBUG_SPRITE = new SpriteSheet("data/spr/common/debug.png", 1, 1);

        // COMMON
        SHADOW_SPRITE = new SpriteSheet("data/spr/common/shadow.png", 86, 37);

        // BALLS
        BALL_SPRITE = new SpriteSheet("data/spr/ball/ball.png", 32, 32);
        FAT_SPRITE = new SpriteSheet("data/spr/ball/fat.png", 32, 32);

        // VALIK
        VALIK_IDLE = new SpriteSheet("data/spr/valik/idle.png", 36, 98);
        VALIK_WALK = new SpriteSheet("data/spr/valik/walk.png", 36, 98);
        VALIK_JUMP = new SpriteSheet("data/spr/valik/jump.png", 36, 98);

        // MCKIDDO
        MCKIDDO_IDLE = new SpriteSheet("data/spr/mckiddo/idle.png", 36, 98);
        MCKIDDO_WALK = new SpriteSheet("data/spr/mckiddo/walk.png", 36, 98);
        MCKIDDO_JUMP = new SpriteSheet("data/spr/mckiddo/jump.png", 36, 98);

        // SILVER
        //SILVER_IDLE = new SpriteSheet("data/spr/silver/idle.png", 100, 100);
        SILVER_WALK = new SpriteSheet("data/spr/silver/walk.png", 100, 100);
        SILVER_JUMP = new SpriteSheet("data/spr/silver/jump.png", 100, 100);

        // UI
        BATTLE_UI = new SpriteSheet("data/ui/battleUI.png", 800, 600);
        L_GOAL_UI = new SpriteSheet("data/ui/lGoal.png", 800, 600);
        R_GOAL_UI = new SpriteSheet("data/ui/rGoal.png", 800, 600);

        // FONTS
        Font font = new Font("Bahnschrift", Font.BOLD, 36).deriveFont(AffineTransform.getScaleInstance(.65, 1d));
        BATTLE_UI_NAME = new TrueTypeFont(font, true);

        font = new Font("Bahnschrift", Font.BOLD, 26).deriveFont(AffineTransform.getScaleInstance(.65, 1d));
        BATTLE_UI_GOAL = new TrueTypeFont(font, true);

        font = new Font("Bahnschrift", Font.BOLD, 42);
        BATTLE_UI_SCORE = new TrueTypeFont(font, true);

        // MUSIC
        //MENU_MUSIC = new Music("data/music/menu.mp3");
    }

    public static SharedResources getInstance() {
        return (INSTANCE);
    }
}
