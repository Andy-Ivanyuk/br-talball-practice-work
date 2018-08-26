package com.ciderBrewers.core.Utils;

import org.newdawn.slick.Animation;

public class PlayerAnimationSet {
    public Animation idle;
    public Animation walk;
    public Animation jump;

    public PlayerAnimationSet(PlayerSet spriteSet) {
        idle = new Animation(spriteSet.idle, 100);
        walk = new Animation(spriteSet.walk, 100);
        jump = new Animation(spriteSet.jump, 100);
        jump.setLooping(false);
    }
}
