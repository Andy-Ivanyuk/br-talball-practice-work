package com.ciderBrewers.core.Shared;

import com.ciderBrewers.core.Objects.*;

import java.util.ArrayList;
import java.util.List;

public class SharedData {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int PARALLAX_VALUE = 80;

    public static final int GROUND_OFFSET = 50;
    public static final int STARTING_OFFSET = 70;

    public static final float PLAYER_SCALE = 2f;
    public static final float BALL_SCALE = 2f;

    public static final float GRAVITY_ACCELERATION = 0.001f;
    public static final float MAX_SPEED = 0.5f;
    public static final float MAX_PLAYER_SPEED = 0.7f;
    public static final float FRICTION = 1.1f;
    public static final float BOUNCE = 0.9f;

    public static final float PLAYER_FRICTION = 1f;
    public static final float PLAYER_BOUNCE = 0f;
    public static final float PLAYER_WEIGTH = 1.3f;
    public static final float PLAYER_HORIZONTAL_FORCE = 0.05f;
    public static final float PLAYER_VERTICAL_FORCE = 0.05f;
    public static final float PLAYER_WALK_SPEED = 0.2f;
    public static final float PLAYER_JUMP_SPEED = 2f;

    public static final int GAME_STATE_RUN = 0;
    public static final int GAME_STATE_PAUSE = 1;
    public static final int GAME_STATE_PAUSE_PHYSICS = 2;

    public static final int PAUSE_TIME = 3;

    private static SharedData INSTANCE = new SharedData();

    public GenericObject parallaxTarget;

    public List<ParallaxBackground> backgrounds = new ArrayList<>();
    public List<GenericObject> genericObjects = new ArrayList<>();
    public List<PhysicsObject> physicsObjects = new ArrayList<>();
    public List<Player> players = new ArrayList<>();
    public List<Ball> balls = new ArrayList<>();

    private SharedData() {
    }

    public static SharedData getInstance() {
        return (INSTANCE);
    }
}
