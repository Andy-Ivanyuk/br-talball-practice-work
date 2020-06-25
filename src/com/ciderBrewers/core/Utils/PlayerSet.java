package com.ciderBrewers.core.Utils;

import org.newdawn.slick.SpriteSheet;

public class PlayerSet {
    public String name;
    public SpriteSheet idle;
    public SpriteSheet walk;
    public SpriteSheet walkBack;
    public SpriteSheet jump;

    public PlayerSet(String name, SpriteSheet idleSheet, SpriteSheet walkSheet, SpriteSheet walkBackSheet, SpriteSheet jumpSheet) {
        this.name = name;
        idle = idleSheet;
        walk = walkSheet;
        walkBack = walkBackSheet;
        jump = jumpSheet;
    }
}