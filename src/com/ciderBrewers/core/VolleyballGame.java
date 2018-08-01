package com.ciderBrewers.core;

import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VolleyballGame extends BasicGame {

    private Controller con1, con2;

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
    public void init(GameContainer c) throws SlickException {
        con1 = new Controller(new Player(new SpriteSheet("data/spr/NPCp.png", SharedData.PLAYER_WIDTH, SharedData.PLAYER_HEIGHT)), 0);
        con2 = new Controller(new Player(new SpriteSheet("data/spr/NPCp.png", SharedData.PLAYER_WIDTH, SharedData.PLAYER_HEIGHT)), 1);
    }

    @Override
    public void update(GameContainer c, int delta) {
        con1.update(c, delta);
        con2.update(c, delta);
    }

    @Override
    public void render(GameContainer c, Graphics g) {
        con1.draw();
        con2.draw();
    }
}
