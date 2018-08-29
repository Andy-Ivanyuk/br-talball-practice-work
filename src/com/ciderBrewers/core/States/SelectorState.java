package com.ciderBrewers.core.States;

import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Shared.SharedResources;
import com.ciderBrewers.core.Utils.PlayerAnimationSet;
import com.ciderBrewers.core.Utils.PlayerSet;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectorState extends BasicGameState {

    private Music music = SharedResources.getInstance().MENU_MUSIC;

    private Sound ui = SharedResources.getInstance().BALL_WALL_SOUNDS.get(0);

    private int player1 = 0;
    private int player2 = 2;

    private Animation player1Sprite;
    private Animation player2Sprite;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        player1Sprite = new PlayerAnimationSet(getPlayer1Set()).idle;
        player2Sprite = new PlayerAnimationSet(getPlayer2Set()).idle;

        //music.loop();
    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        music.stop();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        SharedResources.getInstance().SELECTOR_UI.draw();

        // Draw characters
        player1Sprite.draw(-120, 20, 450, 450);
        player2Sprite.draw(SharedData.SCREEN_WIDTH + 120, 20, -450, 450);

        // Draw arrows
        SharedResources.getInstance().ARROW_R.draw(264 + 70 * player1, 430);
        SharedResources.getInstance().ARROW_B.draw(300 + 70 * player2, 430);

        // Draw text
        TrueTypeFont nameFont = SharedResources.getInstance().SELECTOR_UI_NAME;

        PlayerSet player1Set = getPlayerSet(player1);
        PlayerSet player2Set = getPlayerSet(player2);

        nameFont.drawString(20.0f, 495f, player1Set.name, SharedData.COLOR_WHITESMOKE);
        float textWidth = nameFont.getWidth(player2Set.name);
        nameFont.drawString(SharedData.SCREEN_WIDTH - 20.0f - textWidth, 495f, player2Set.name, SharedData.COLOR_WHITESMOKE);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) {
        Input input = gameContainer.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            music.stop();

            stateBasedGame.enterState(0);
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            SharedData.getInstance().player1Set = getPlayer2Set();
            SharedData.getInstance().player2Set = getPlayer1Set();

            stateBasedGame.enterState(2);
        }

        if (input.isKeyPressed(Input.KEY_A)) {
            player1 -= 1;
            ui.play();
        }
        if (input.isKeyPressed(Input.KEY_D)) {
            player1 += 1;
            ui.play();
        }

        if (input.isKeyPressed(Input.KEY_LEFT)) {
            player2 -= 1;
            ui.play();
        }
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            player2 += 1;
            ui.play();
        }

        if (player1 < 0) player1 = 0;
        if (player1 > 2) player1 = 2;

        if (player2 < 0) player2 = 0;
        if (player2 > 2) player2 = 2;

        player1Sprite = new PlayerAnimationSet(getPlayer1Set()).idle;
        player2Sprite = new PlayerAnimationSet(getPlayer2Set()).idle;
    }

    public PlayerSet getPlayer1Set() {
        return getPlayerSet(player1);
    }

    public PlayerSet getPlayer2Set() {
        return getPlayerSet(player2);
    }

    private PlayerSet getPlayerSet(int index) {
        switch (index) {
            case 0:
                return SharedResources.getInstance().GYPSY_SET;
            case 1:
                return SharedResources.getInstance().VALIK_SET;
            case 2:
                return SharedResources.getInstance().SILVER_SET;
            case 3:
                return SharedResources.getInstance().MCKIDDO_SET;
        }
        return SharedResources.getInstance().GYPSY_SET;
    }
}
