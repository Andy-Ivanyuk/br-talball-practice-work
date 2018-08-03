package com.ciderBrewers.core.States;

import com.ciderBrewers.core.Objects.*;
import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Shared.SpriteSheets;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {


    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        SharedData.getInstance().background = new ParallaxBackground(SpriteSheets.getInstance().DEBUG_BACKGROUND);

        Ball ball = new Ball(SpriteSheets.getInstance().FAT_SPRITE);

        Player player1 = new Player(SpriteSheets.getInstance().PLAYER_SPRITE, ball);
        Player player2 = new Player(SpriteSheets.getInstance().PLAYER_SPRITE, ball);

        Controller con1 = new Controller(player1, 0);
        Controller con2 = new Controller(player2, 1);

        SharedData.getInstance().controllers.add(con1);
        SharedData.getInstance().controllers.add(con2);
        SharedData.getInstance().players.add(player1);
        SharedData.getInstance().players.add(player2);
        SharedData.getInstance().balls.add(ball);

        SharedData.getInstance().parallaxTarget = ball;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        float[] parallaxOffset = SharedData.getInstance().parallaxTarget.getParallaxOffset();

        SharedData.getInstance().background.draw(parallaxOffset);

        for (GenericObject object : SharedData.getInstance().genericObjects) object.draw(parallaxOffset);
        for (PhysicsObject object : SharedData.getInstance().physicsObjects) object.draw(parallaxOffset);
        for (Player object : SharedData.getInstance().players) object.draw(parallaxOffset);
        for (Ball object : SharedData.getInstance().balls) object.draw(parallaxOffset);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) {
        for (Controller controller : SharedData.getInstance().controllers) controller.update(gameContainer, delta);
        for (PhysicsObject object : SharedData.getInstance().physicsObjects) object.update(gameContainer, delta);
        for (Player object : SharedData.getInstance().players) object.update(gameContainer, delta);
        for (Ball object : SharedData.getInstance().balls) object.update(gameContainer, delta);
    }
}
