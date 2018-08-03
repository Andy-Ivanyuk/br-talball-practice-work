package com.ciderBrewers.core;

import java.util.ArrayList;
import java.util.List;

class SharedData {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final float PLAYER_SCALE = 2f;
    static final int STARTING_OFFSET = 70;
    static final int GROUND_OFFSET = 50;

    static final int PARALLAX_VALUE = 30;

    static final int BALL_SIZE = 50;
    static final float BALL_SCALE = 2f;

    static final float GRAVITY_ACCELERATION = 0.001f;
    static final float FRICTION = 1.1f;


    private static SharedData INSTANCE = new SharedData();

    ParallaxBackground background;
    GenericObject parallaxTarget;

    List<Controller> controllers = new ArrayList<>();
    List<GenericObject> genericObjects = new ArrayList<>();
    List<PhysicsObject> physicsObjects = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    List<Ball> balls = new ArrayList<>();

    private SharedData() {
    }

    static SharedData getInstance() {
        return (INSTANCE);
    }
}
