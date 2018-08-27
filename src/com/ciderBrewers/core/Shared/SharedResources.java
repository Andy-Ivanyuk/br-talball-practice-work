package com.ciderBrewers.core.Shared;

import com.ciderBrewers.core.Utils.PlayerSet;
import org.newdawn.slick.*;

import java.awt.Font;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;

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

    // Background
    public SpriteSheet BACKGROUND_STADIUM_1;
    public SpriteSheet BACKGROUND_STADIUM_2;
    public SpriteSheet BACKGROUND_STADIUM_3;
    public SpriteSheet BACKGROUND_STADIUM_4;
    public SpriteSheet BACKGROUND_STADIUM_5;
    public SpriteSheet BACKGROUND_STADIUM_6;

    // BALLS
    public SpriteSheet BALL_SPRITE;
    public SpriteSheet FAT_SPRITE;

    // Sprite sets
    public PlayerSet VALIK_SET;
    public PlayerSet MCKIDDO_SET;
    public PlayerSet SILVER_SET;

    // UI
    public SpriteSheet BATTLE_UI;
    public SpriteSheet L_GOAL_UI;
    public SpriteSheet R_GOAL_UI;
    public SpriteSheet PAUSE_UI;

    // Fonts
    public TrueTypeFont BATTLE_UI_NAME;
    public TrueTypeFont BATTLE_UI_GOAL;
    public TrueTypeFont BATTLE_UI_SCORE;

    // Music
    public Music MENU_MUSIC;

    // Sound lists
    public ArrayList<Sound> BALL_PUNCH_SOUNDS = new ArrayList<>();
    public ArrayList<Sound> BALL_WALL_SOUNDS = new ArrayList<>();

    private SharedResources() throws SlickException, IOException, FontFormatException {
        // Throws an exception but still loads picture.
        // Todo: Fix it.
        // DEBUG
        DEBUG_BACKGROUND_DYNAMIC_1 = new SpriteSheet("data/background/bg_debug_1.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_2 = new SpriteSheet("data/background/bg_debug_2.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_3 = new SpriteSheet("data/background/bg_debug_3.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_4 = new SpriteSheet("data/background/bg_debug_4.png", 800, 600);
        DEBUG_BACKGROUND_DYNAMIC_5 = new SpriteSheet("data/background/bg_debug_5.png", 800, 600);

        DEBUG_SPRITE = new SpriteSheet("data/spr/common/debug.png", 1, 1);

        // COMMON
        SHADOW_SPRITE = new SpriteSheet("data/spr/common/shadow.png", 86, 37);

        // Backgrounds
        BACKGROUND_STADIUM_1 = new SpriteSheet("data/background/stadium/1.png", 800, 600);
        BACKGROUND_STADIUM_2 = new SpriteSheet("data/background/stadium/2.png", 800, 600);
        BACKGROUND_STADIUM_3 = new SpriteSheet("data/background/stadium/3.png", 800, 600);
        BACKGROUND_STADIUM_4 = new SpriteSheet("data/background/stadium/4.png", 800, 600);
        BACKGROUND_STADIUM_5 = new SpriteSheet("data/background/stadium/5.png", 800, 600);
        BACKGROUND_STADIUM_6 = new SpriteSheet("data/background/stadium/6.png", 800, 600);

        // BALLS
        BALL_SPRITE = new SpriteSheet("data/spr/ball/ball.png", 32, 32);
        FAT_SPRITE = new SpriteSheet("data/spr/ball/fat.png", 32, 32);

        // Sprite sets
        VALIK_SET = new PlayerSet("Valik",
                new SpriteSheet("data/spr/valik/idle.png", 36, 98),
                new SpriteSheet("data/spr/valik/walk.png", 36, 98),
                new SpriteSheet("data/spr/valik/walk.png", 36, 98),
                new SpriteSheet("data/spr/valik/jump.png", 36, 98));

        MCKIDDO_SET = new PlayerSet("McKiddo",
                new SpriteSheet("data/spr/mckiddo/idle.png", 36, 98),
                new SpriteSheet("data/spr/mckiddo/walk.png", 36, 98),
                new SpriteSheet("data/spr/mckiddo/walk.png", 36, 98),
                new SpriteSheet("data/spr/mckiddo/jump.png", 36, 98));

        SILVER_SET = new PlayerSet("Silver",
                new SpriteSheet("data/spr/silver/idle.png", 100, 100),
                new SpriteSheet("data/spr/silver/walk.png", 100, 100),
                new SpriteSheet("data/spr/silver/walk_back.png", 100, 100),
                new SpriteSheet("data/spr/silver/jump.png", 100, 100));

        // UI
        BATTLE_UI = new SpriteSheet("data/ui/battleUI.png", 800, 600);
        L_GOAL_UI = new SpriteSheet("data/ui/lGoal.png", 800, 600);
        R_GOAL_UI = new SpriteSheet("data/ui/rGoal.png", 800, 600);
        PAUSE_UI = new SpriteSheet("data/ui/pauseUI.png", 800, 600);

        // FONTS
        Font font = new Font("Bahnschrift", Font.BOLD, 36).deriveFont(AffineTransform.getScaleInstance(.65, 1d));
        BATTLE_UI_NAME = new TrueTypeFont(font, true);

        font = new Font("Bahnschrift", Font.BOLD, 26).deriveFont(AffineTransform.getScaleInstance(.65, 1d));
        BATTLE_UI_GOAL = new TrueTypeFont(font, true);

        font = new Font("Bahnschrift", Font.BOLD, 42);
        BATTLE_UI_SCORE = new TrueTypeFont(font, true);

        // Music
        //MENU_MUSIC = new Music("data/music/menu.mp3");

        // Sounds
        BALL_PUNCH_SOUNDS.add(new Sound("data/sounds/ball/punch/punch1.wav"));
        BALL_PUNCH_SOUNDS.add(new Sound("data/sounds/ball/punch/punch2.wav"));
        BALL_PUNCH_SOUNDS.add(new Sound("data/sounds/ball/punch/punch3.wav"));
        BALL_PUNCH_SOUNDS.add(new Sound("data/sounds/ball/punch/punch4.wav"));
        BALL_PUNCH_SOUNDS.add(new Sound("data/sounds/ball/punch/punch5.wav"));
        BALL_PUNCH_SOUNDS.add(new Sound("data/sounds/ball/punch/punch6.wav"));

        BALL_WALL_SOUNDS.add(new Sound("data/sounds/ball/wall/wall1.wav"));
        BALL_WALL_SOUNDS.add(new Sound("data/sounds/ball/wall/wall2.wav"));
        BALL_WALL_SOUNDS.add(new Sound("data/sounds/ball/wall/wall3.wav"));
    }

    public static SharedResources getInstance() {
        return (INSTANCE);
    }
}
