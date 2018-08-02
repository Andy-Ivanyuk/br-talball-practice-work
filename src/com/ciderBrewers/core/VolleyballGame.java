package com.ciderBrewers.core;

import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VolleyballGame extends BasicGame {

    private VolleyballGame(String title) {
        super(title);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app;
            app = new AppGameContainer(new VolleyballGame("Simple Slick Game"));
            app.setDisplayMode(SharedData.SCREEN_WIDTH, SharedData.SCREEN_HEIGHT, false);
            app.start();
            app.setAlwaysRender(true);
        } catch (SlickException ex) {
            Logger.getLogger(VolleyballGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(GameContainer c) {
        Player player1 = new Player(SpriteSheets.getInstance().PLAYER_SPRITE);
        Player player2 = new Player(SpriteSheets.getInstance().PLAYER_SPRITE);

        Controller con1 = new Controller(player1, 0);
        Controller con2 = new Controller(player2, 1);
        Ball ball = new Ball(SpriteSheets.getInstance().BALL_SPRITE);

        SharedData.getInstance().controllers.add(con1);
        SharedData.getInstance().controllers.add(con2);
        SharedData.getInstance().players.add(player1);
        SharedData.getInstance().players.add(player2);
        SharedData.getInstance().balls.add(ball);
    }

    @Override
    public void update(GameContainer c, int delta) {
        for (Controller controller : SharedData.getInstance().controllers) controller.update(c, delta);
        for (PhysicsObject object : SharedData.getInstance().physicsObjects) object.update(c, delta);
        for (Player object : SharedData.getInstance().players) object.update(c, delta);
        for (Ball object : SharedData.getInstance().balls) object.update(c, delta);
    }

    @Override
    public void render(GameContainer c, Graphics g) {
        for (GenericObject object : SharedData.getInstance().genericObjects) object.draw();
        for (PhysicsObject object : SharedData.getInstance().physicsObjects) object.draw();
        for (Player object : SharedData.getInstance().players) object.draw();
        for (Ball object : SharedData.getInstance().balls) object.draw();
    }
}
