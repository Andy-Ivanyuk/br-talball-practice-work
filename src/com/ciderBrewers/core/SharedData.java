package com.ciderBrewers.core;

public class SharedData {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int PLAYER_WIDTH = 44;
    static final int PLAYER_HEIGHT = 123;
    static final float PLAYER_SCALE = 2f;
    static final int STARTING_OFFSET = 70;
    private static SharedData INSTANCE = new SharedData();

    private SharedData() {
    }

    public static SharedData getInstance() {
        return (INSTANCE);
    }
}
