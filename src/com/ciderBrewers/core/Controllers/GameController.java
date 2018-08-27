package com.ciderBrewers.core.Controllers;

import com.ciderBrewers.core.Objects.Ball;
import com.ciderBrewers.core.Objects.Player;
import com.ciderBrewers.core.Shared.SharedData;
import com.ciderBrewers.core.Shared.SharedResources;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

public class GameController {
    private Ball ball;
    private Player player1;
    private Player player2;
    private int leftWalk;
    private int rightWalk;
    private int jump;

    private int goal = 10;

    private long lastPauseTime;
    private int lastScored = 0;

    private int gameState = SharedData.GAME_STATE_RUN;

    private Animation battleUI;
    private Animation lGoalUI;
    private Animation rGoalUI;
    private Animation pauseUI;

    public GameController(Player player1, Player player2, Ball ball) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;

        player1.setX(SharedData.SCREEN_WIDTH - SharedData.STARTING_OFFSET);
        player2.setX(SharedData.STARTING_OFFSET);

        ball.setX(SharedData.SCREEN_WIDTH / 2);
        ball.setY(200);

        battleUI = new Animation(SharedResources.getInstance().BATTLE_UI, 300);
        lGoalUI = new Animation(SharedResources.getInstance().L_GOAL_UI, 30);
        rGoalUI = new Animation(SharedResources.getInstance().R_GOAL_UI, 30);
        pauseUI = new Animation(SharedResources.getInstance().PAUSE_UI, 300);

        lGoalUI.stop();
        rGoalUI.stop();
        lGoalUI.setLooping(false);
        rGoalUI.setLooping(false);
    }

    public void update(GameContainer c, int delta) {
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

        if (gameState == SharedData.GAME_STATE_RUN) {
            if (input.isKeyDown(Input.KEY_LEFT)) player1.nextStep -= 1;
            if (input.isKeyDown(Input.KEY_RIGHT)) player1.nextStep += 1;
            if (input.isKeyDown(Input.KEY_UP)) player1.jump = true;

            if (input.isKeyDown(Input.KEY_A)) player2.nextStep -= 1;
            if (input.isKeyDown(Input.KEY_D)) player2.nextStep += 1;
            if (input.isKeyDown(Input.KEY_W)) player2.jump = true;

            if (ball.isGrounded()) {
                if (ball.getX() > SharedData.SCREEN_WIDTH / 2) {
                    playerScored(player1);
                    lastScored = 1;
                    lGoalUI.start();
                } else {
                    playerScored(player2);
                    lastScored = 2;
                    rGoalUI.start();
                }
            }
        } else if (gameState == SharedData.GAME_STATE_PAUSE_PHYSICS) {
            checkForScorePauseEnd();
        }
    }

    public void draw() {
        drawBattleUI();
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
            ball.setSpeedX(0);
            ball.setSpeedY(0);

            ball.setX((float) SharedData.SCREEN_WIDTH / 2);
            ball.setY(200);

            player1.setX(SharedData.SCREEN_WIDTH - SharedData.STARTING_OFFSET);
            player1.setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET);
            player2.setX(SharedData.STARTING_OFFSET);
            player2.setY(SharedData.SCREEN_HEIGHT - SharedData.GROUND_OFFSET);

            if (lastScored == 1) ball.setX((float) SharedData.SCREEN_WIDTH / 2 + 150);
            if (lastScored == 2) ball.setX((float) SharedData.SCREEN_WIDTH / 2 - 150);

            ball.setPhysicsEnabled(false);

            lGoalUI.restart();
            rGoalUI.restart();
            lGoalUI.stop();
            rGoalUI.stop();

            gameState = SharedData.GAME_STATE_RUN;
        }
    }

    void drawBattleUI() {
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
}
