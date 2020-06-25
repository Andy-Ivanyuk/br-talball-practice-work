package com.ciderBrewers.core.Controllers;

import com.ciderBrewers.core.Objects.Ball;
import com.ciderBrewers.core.Objects.Player;
import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Shared.SharedResources;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class GameController {
    private Ball ball;
    private Player player1;
    private Player player2;

    private int goal = 10;

    private long lastPauseTime;
    private int lastScored = 0;

    private int gameState = SharedData.GAME_STATE_RUN;

    private Animation battleUI;
    private Animation lGoalUI;
    private Animation rGoalUI;
    private Animation pauseUI;

    private Sound ambience = SharedResources.getInstance().GAME_AMBIENCE;
    private Music music = SharedResources.getInstance().GAME_MUSIC;

    private Sound whistle = SharedResources.getInstance().GAME_WHISTLE;

    public GameController(Player player1, Player player2, Ball ball) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;

        player1.setX(SharedData.SCREEN_WIDTH - SharedData.STARTING_OFFSET);
        player2.setX(SharedData.STARTING_OFFSET);

        ball.setX((float) SharedData.SCREEN_WIDTH / 2);
        ball.setY(200);

        battleUI = new Animation(SharedResources.getInstance().BATTLE_UI, 300);
        lGoalUI = new Animation(SharedResources.getInstance().L_GOAL_UI, 30);
        rGoalUI = new Animation(SharedResources.getInstance().R_GOAL_UI, 30);
        pauseUI = new Animation(SharedResources.getInstance().PAUSE_UI, 300);

        lGoalUI.stop();
        rGoalUI.stop();
        lGoalUI.setLooping(false);
        rGoalUI.setLooping(false);

        ambience.loop();
        music.loop();
        whistle.play();
    }

    public void update(GameContainer c, StateBasedGame stateBasedGame, int delta) {
        Input input = c.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            if (gameState == SharedData.GAME_STATE_RUN) {
                gameState = SharedData.GAME_STATE_PAUSE;
                player1.getSprite().stop();
                player2.getSprite().stop();
            } else if (gameState == SharedData.GAME_STATE_PAUSE) {
                gameState = SharedData.GAME_STATE_RUN;
                player1.getSprite().start();
                player2.getSprite().start();
            }
        }

        if (gameState == SharedData.GAME_STATE_RUN || gameState == SharedData.GAME_STATE_PAUSE_GOAL) {
            if (input.isKeyDown(Input.KEY_LEFT)) player1.nextStep -= 1;
            if (input.isKeyDown(Input.KEY_RIGHT)) player1.nextStep += 1;
            if (input.isKeyDown(Input.KEY_UP)) player1.jump = true;

            if (input.isKeyDown(Input.KEY_A)) player2.nextStep -= 1;
            if (input.isKeyDown(Input.KEY_D)) player2.nextStep += 1;
            if (input.isKeyDown(Input.KEY_W)) player2.jump = true;

            if (gameState != SharedData.GAME_STATE_PAUSE_GOAL && ball.isGrounded()) {
                if (ball.getX() > SharedData.SCREEN_WIDTH / 2) {
                    playerScored(player1, player2);
                    lastScored = 1;
                    lGoalUI.start();
                } else {
                    playerScored(player2, player1);
                    lastScored = 2;
                    rGoalUI.start();
                }
                whistle.play();
            }
        }
        if (gameState != SharedData.GAME_STATE_RUN) checkForPauseEnd();
    }

    public void draw() {
        battleUI.draw();
        lGoalUI.draw();
        rGoalUI.draw();

        TrueTypeFont nameFont = SharedResources.getInstance().BATTLE_UI_NAME;
        TrueTypeFont goalFont = SharedResources.getInstance().BATTLE_UI_GOAL;
        TrueTypeFont scoreFont = SharedResources.getInstance().BATTLE_UI_SCORE;

        nameFont.drawString(40.0f, 16.0f, player2.getName(), SharedData.COLOR_WHITESMOKE);
        float textWidth = nameFont.getWidth(player1.getName());
        nameFont.drawString(SharedData.SCREEN_WIDTH - 40.0f - textWidth, 16.0f, player1.getName(), SharedData.COLOR_WHITESMOKE);

        textWidth = goalFont.getWidth(Integer.toString(goal));
        goalFont.drawString((float) SharedData.SCREEN_WIDTH / 2 - textWidth / 2, 7f, Integer.toString(goal), SharedData.COLOR_BLACK);

        textWidth = goalFont.getWidth(Integer.toString(player1.getScore()));
        scoreFont.drawString((float) SharedData.SCREEN_WIDTH / 2 - textWidth / 2 - 34, 31f, Integer.toString(player1.getScore()), SharedData.COLOR_WHITESMOKE);

        textWidth = goalFont.getWidth(Integer.toString(player2.getScore()));
        scoreFont.drawString((float) SharedData.SCREEN_WIDTH / 2 - textWidth / 2 + 18, 31f, Integer.toString(player2.getScore()), SharedData.COLOR_WHITESMOKE);

        if (gameState == SharedData.GAME_STATE_PAUSE) {
            pauseUI.draw();
        }
    }

    void playerScored(Player winnerPlayer, Player loserPlayer) {
        winnerPlayer.setScore(winnerPlayer.getScore() + 1);

        ball.setTouchEnabled(false);

        if (winnerPlayer.getScore() >= goal) gameState = SharedData.GAME_STATE_PAUSE_GAME_END;
        else gameState = SharedData.GAME_STATE_PAUSE_GOAL;

        lastPauseTime = System.currentTimeMillis();
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void checkForPauseEnd() {
        switch (gameState) {
            case SharedData.GAME_STATE_PAUSE:
                break;
            case SharedData.GAME_STATE_PAUSE_PHYSICS:
                break;
            case SharedData.GAME_STATE_PAUSE_GOAL: {
                if (System.currentTimeMillis() - lastPauseTime > SharedData.PAUSE_TIME * 1000) {
                    ball.setSpeedX(0);
                    ball.setSpeedY(0);

                    ball.setX((float) SharedData.SCREEN_WIDTH / 2);
                    ball.setY(200);

                    player1.getSprite().start();
                    player2.getSprite().start();

                    player1.setX(SharedData.SCREEN_WIDTH - SharedData.STARTING_OFFSET);
                    player1.setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET);
                    player1.setSpeedX(0);
                    player1.setSpeedY(0);
                    player1.nextStep = 0;
                    player1.jump = false;

                    player2.setX(SharedData.STARTING_OFFSET);
                    player2.setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET);
                    player2.setSpeedX(0);
                    player2.setSpeedY(0);
                    player2.nextStep = 0;
                    player2.jump = false;

                    if (lastScored == 1) ball.setX((float) SharedData.SCREEN_WIDTH / 2 + 150);
                    if (lastScored == 2) ball.setX((float) SharedData.SCREEN_WIDTH / 2 - 150);

                    ball.setPhysicsEnabled(false);
                    ball.startCollisionTimer();

                    lGoalUI.restart();
                    rGoalUI.restart();
                    lGoalUI.stop();
                    rGoalUI.stop();

                    gameState = SharedData.GAME_STATE_RUN;
                }
            }
            case SharedData.GAME_STATE_PAUSE_GAME_END:
                break;
        }
    }
}
