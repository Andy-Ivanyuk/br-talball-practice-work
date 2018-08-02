package com.ciderBrewers.core;

public class SharedData {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final float PLAYER_SCALE = 2f;
    static final int STARTING_OFFSET = 70;
    static final int GROUND_OFFSET = 50;

    static final int BALL_SIZE = 50;
    static final float BALL_SCALE = 2f;

    static final float GRAVITY_ACCELERATION = 0.001f;
    static final float FRICTION = 1.1f;


    private static SharedData INSTANCE = new SharedData();

    private SharedData() {
    }

    public static SharedData getInstance() {
        return (INSTANCE);
    }
}
