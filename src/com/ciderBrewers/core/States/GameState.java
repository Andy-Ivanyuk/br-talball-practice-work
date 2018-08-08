package com.ciderBrewers.core.States;

import com.ciderBrewers.core.Controllers.GameController;
import com.ciderBrewers.core.Objects.*;
import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Shared.SharedResources;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

public class GameState extends BasicGameState {

    GameController gameController;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        new ParallaxBackground(SharedResources.getInstance().DEBUG_BACKGROUND_DYNAMIC_1, 4);
        new ParallaxBackground(SharedResources.getInstance().DEBUG_BACKGROUND_DYNAMIC_2, 5);
        new ParallaxBackground(SharedResources.getInstance().DEBUG_BACKGROUND_DYNAMIC_5, 1.1f);
        new ParallaxBackground(SharedResources.getInstance().DEBUG_BACKGROUND_DYNAMIC_4, 1);
        new ParallaxBackground(SharedResources.getInstance().DEBUG_BACKGROUND_DYNAMIC_3, 0.9f);

        Ball ball = new Ball(SharedResources.getInstance().FAT_SPRITE);

        Player player1 = new Player(SharedResources.getInstance().VALIK_IDLE, SharedResources.getInstance().VALIK_WALK, SharedResources.getInstance().VALIK_JUMP, ball);
        Player player2 = new Player(SharedResources.getInstance().MCKIDDO_IDLE, SharedResources.getInstance().MCKIDDO_WALK, SharedResources.getInstance().MCKIDDO_JUMP, ball);

        gameController = new GameController(player1, player2, ball);

        // Divider
        GenericObject divider = new GenericObject();
        divider.setX((float) SharedData.SCREEN_WIDTH / 2);
        divider.setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET);
        divider.setOriginX(5);
        divider.setOriginY(220);
        divider.setCollider(new Rectangle(0, 0, 10, 220));
        SharedData.getInstance().genericObjects.add(divider);

        SharedData.getInstance().parallaxTarget = ball;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        float[] parallaxOffset = SharedData.getInstance().parallaxTarget.getParallaxOffset();

        for (ParallaxBackground object : SharedData.getInstance().backgrounds) object.draw(parallaxOffset);
        for (GenericObject object : SharedData.getInstance().genericObjects) object.draw(parallaxOffset);
        for (PhysicsObject object : SharedData.getInstance().physicsObjects) object.draw(parallaxOffset);
        for (Player object : SharedData.getInstance().players) object.draw(parallaxOffset);
        for (Ball object : SharedData.getInstance().balls) object.draw(parallaxOffset);

        gameController.draw();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) {
        gameController.update(gameContainer, delta);

        if (gameController.getGameState() == SharedData.GAME_STATE_RUN) {
            for (GenericObject object : SharedData.getInstance().genericObjects) object.update(gameContainer, delta);
            for (PhysicsObject object : SharedData.getInstance().physicsObjects) object.update(gameContainer, delta);
            for (Player object : SharedData.getInstance().players) object.update(gameContainer, delta);
            for (Ball object : SharedData.getInstance().balls) object.update(gameContainer, delta);
        }
    }
}
