package com.ciderBrewers.core.Utils;

import org.newdawn.slick.SpriteSheet;

public class PlayerSet {
    public String name;
    public SpriteSheet idle;
    public SpriteSheet walk;
    public SpriteSheet jump;

    public PlayerSet(String name, SpriteSheet idleSheet, SpriteSheet walkSheet, SpriteSheet jumpSheet) {
        this.name = name;
        idle = idleSheet;
        walk = walkSheet;
        jump = jumpSheet;
    }
}