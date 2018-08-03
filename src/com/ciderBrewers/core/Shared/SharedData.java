package com.ciderBrewers.core.Shared;

import com.ciderBrewers.core.Objects.*;

import java.util.ArrayList;
import java.util.List;

public class SharedData {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final float PLAYER_SCALE = 2f;
    public static final int STARTING_OFFSET = 70;
    public static final int GROUND_OFFSET = 50;

    public static final int PARALLAX_VALUE = 80;

    public static final float BALL_SCALE = 2f;

    public static final float GRAVITY_ACCELERATION = 0.001f;
    public static final float FRICTION = 1.1f;


    private static SharedData INSTANCE = new SharedData();

    public ParallaxBackground background;
    public GenericObject parallaxTarget;

    public List<Controller> controllers = new ArrayList<>();
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