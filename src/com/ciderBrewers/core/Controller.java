package com.ciderBrewers.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

class Controller {
    private Player player;
    private int leftWalk;
    private int rightWalk;
    private int jump;

    Controller(Player player, int inputMap) {
        this.player = player;
        if (inputMap == 0) {
            player.setX(SharedData.SCREEN_WIDTH - SharedData.STARTING_OFFSET);
            this.leftWalk = Input.KEY_LEFT;
            this.rightWalk = Input.KEY_RIGHT;
            this.jump = Input.KEY_UP;
        } else {
            player.setX(SharedData.STARTING_OFFSET);
            this.leftWalk = Input.KEY_A;
            this.rightWalk = Input.KEY_D;
            this.jump = Input.KEY_W;
        }
    }

    void update(GameContainer c, int delta) {
        Input walkInput = c.getInput();
        if (walkInput.isKeyDown(leftWalk)) player.nextStep -= 1;
        if (walkInput.isKeyDown(rightWalk)) player.nextStep += 1;
    }
}
