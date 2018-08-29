package com.ciderBrewers.core.States;

import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Shared.SharedResources;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitleState extends BasicGameState {

    Animation intro = new Animation(SharedResources.getInstance().CINEMATIC_INTRO, 53);
    Music music = SharedResources.getInstance().MENU_MUSIC;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        intro.setLooping(false);
    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        music.loop();
    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) {
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        intro.draw(0, 0, SharedData.SCREEN_WIDTH, SharedData.SCREEN_HEIGHT);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) {
        if (intro.isStopped()) {
            stateBasedGame.enterState(1);
        }
    }
}
