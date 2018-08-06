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
        ParallaxBackground bg1 = new ParallaxBackground(SpriteSheets.getInstance().DEBUG_BACKGROUND_DYNAMIC_1, 4);
        ParallaxBackground bg2 = new ParallaxBackground(SpriteSheets.getInstance().DEBUG_BACKGROUND_DYNAMIC_2, 3);
        ParallaxBackground bg3 = new ParallaxBackground(SpriteSheets.getInstance().DEBUG_BACKGROUND_DYNAMIC_5, 1.1f);
        ParallaxBackground bg4 = new ParallaxBackground(SpriteSheets.getInstance().DEBUG_BACKGROUND_DYNAMIC_4, 1);
        ParallaxBackground bg5 = new ParallaxBackground(SpriteSheets.getInstance().DEBUG_BACKGROUND_DYNAMIC_3, 0.9f);

        Ball ball = new Ball(SpriteSheets.getInstance().FAT_SPRITE);

        Player player1 = new Player(SpriteSheets.getInstance().VALIK_IDLE, SpriteSheets.getInstance().VALIK_WALK, SpriteSheets.getInstance().VALIK_JUMP, ball);
        Player player2 = new Player(SpriteSheets.getInstance().MCKIDDO_IDLE, SpriteSheets.getInstance().MCKIDDO_WALK, SpriteSheets.getInstance().MCKIDDO_JUMP, ball);

        Controller con1 = new Controller(player1, 0);
        Controller con2 = new Controller(player2, 1);

        SharedData.getInstance().backgrounds.add(bg1);
        SharedData.getInstance().backgrounds.add(bg2);
        SharedData.getInstance().backgrounds.add(bg3);
        SharedData.getInstance().backgrounds.add(bg4);
        SharedData.getInstance().backgrounds.add(bg5);

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

        for (ParallaxBackground object : SharedData.getInstance().backgrounds)
            if (object.getDepth() >= 1) object.draw(parallaxOffset);

        for (GenericObject object : SharedData.getInstance().genericObjects) object.draw(parallaxOffset);
        for (PhysicsObject object : SharedData.getInstance().physicsObjects) object.draw(parallaxOffset);
        for (Player object : SharedData.getInstance().players) object.draw(parallaxOffset);
        for (Ball object : SharedData.getInstance().balls) object.draw(parallaxOffset);

        for (ParallaxBackground object : SharedData.getInstance().backgrounds)
            if (object.getDepth() < 1) object.draw(parallaxOffset);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) {
        for (Controller controller : SharedData.getInstance().controllers) controller.update(gameContainer, delta);
        for (PhysicsObject object : SharedData.getInstance().physicsObjects) object.update(gameContainer, delta);
        for (Player object : SharedData.getInstance().players) object.update(gameContainer, delta);
        for (Ball object : SharedData.getInstance().balls) object.update(gameContainer, delta);
    }
}
