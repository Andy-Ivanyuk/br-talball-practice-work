package com.ciderBrewers.core.States;

import com.ciderBrewers.core.Controllers.GameController;
import com.ciderBrewers.core.Objects.*;
import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Shared.SharedResources;
import com.ciderBrewers.core.Utils.Collider;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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

        Ball ball = new Ball(0, 0, SharedResources.getInstance().FAT_SPRITE);

        SharedData.getInstance().player1Set = SharedResources.getInstance().VALIK_SET;
        SharedData.getInstance().player2Set = SharedResources.getInstance().SILVER_SET;

        Player player1 = new Player(SharedData.getInstance().player1Set, ball, -1);
        Player player2 = new Player(SharedData.getInstance().player2Set, ball, 1);

        gameController = new GameController(player1, player2, ball);

        // Divider
        GenericObject divider = new GenericObject();
        divider.setX((float) SharedData.SCREEN_WIDTH / 2);
        divider.setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET);
        divider.setOriginX(5);
        divider.setOriginY(220);
        divider.setCollider(new Collider(0, 0, 5, 220, 5, 0));
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
