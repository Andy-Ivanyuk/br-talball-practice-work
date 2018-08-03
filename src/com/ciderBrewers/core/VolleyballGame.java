package com.ciderBrewers.core;

import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.States.GameState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.StateBasedGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VolleyballGame extends StateBasedGame {

    ParticleSystem particleSystem;

    private VolleyballGame(String title) {
        super(title);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app;
            app = new AppGameContainer(new VolleyballGame("Totally Wicked Volleyball"));
            app.setIcon("data/spr/fat.png");
            app.setDisplayMode(SharedData.SCREEN_WIDTH, SharedData.SCREEN_HEIGHT, false);
            app.setAlwaysRender(true);
            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(VolleyballGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initStatesList(GameContainer gameContainer) {
        addState(new GameState());
    }
}
