package com.ciderBrewers.core.Controllers;

import com.ciderBrewers.core.Objects.Ball;
import com.ciderBrewers.core.Objects.Player;
import com.ciderBrewers.core.Shared.SharedData;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class GameController {
    private Ball ball;
    private Player player1;
    private Player player2;
    private int leftWalk;
    private int rightWalk;
    private int jump;

    private long lastPauseTime;

    private int gameState = SharedData.GAME_STATE_RUN;

    public GameController(Player player1, Player player2, Ball ball) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;

        player1.setX(SharedData.SCREEN_WIDTH - SharedData.STARTING_OFFSET);
        player2.setX(SharedData.STARTING_OFFSET);
    }

    public void update(GameContainer c, int delta) {
        if (gameState == SharedData.GAME_STATE_RUN) {
            Input input = c.getInput();

            if (input.isKeyDown(Input.KEY_LEFT)) player1.nextStep -= 1;
            if (input.isKeyDown(Input.KEY_RIGHT)) player1.nextStep += 1;
            if (input.isKeyDown(Input.KEY_UP)) player1.jump = true;

            if (input.isKeyDown(Input.KEY_A)) player2.nextStep -= 1;
            if (input.isKeyDown(Input.KEY_D)) player2.nextStep += 1;
            if (input.isKeyDown(Input.KEY_W)) player2.jump = true;

            if (ball.isGrounded()) {
                if (ball.getX() > SharedData.SCREEN_WIDTH / 2) {
                    playerScored(player1);
                } else {
                    playerScored(player2);
                }
            }
        } else if (gameState == SharedData.GAME_STATE_PAUSE_PHYSICS) {
            checkForScorePauseEnd();
        }
    }

    void playerScored(Player player) {
        gameState = SharedData.GAME_STATE_PAUSE_PHYSICS;

        player.setScore(player.getScore() + 1);

        lastPauseTime = System.currentTimeMillis();
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void checkForScorePauseEnd() {
        if (System.currentTimeMillis() - lastPauseTime > SharedData.PAUSE_TIME * 1000) {
            ball.setX((float) SharedData.SCREEN_WIDTH / 2);
            ball.setY(100);
            ball.setSpeedX(0);
            ball.setSpeedY(0);

            gameState = SharedData.GAME_STATE_RUN;
        }
    }
}
