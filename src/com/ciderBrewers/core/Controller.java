package com.ciderBrewers.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Controller {
    private Player player;
    private int leftWalk;
    private int rightWalk;
    private int jump;

    public Controller(Player player) {
        this.player = player;
        if (player.getPosition() == 0) {
            this.leftWalk = Input.KEY_A;
            this.rightWalk = Input.KEY_D;
            this.jump = Input.KEY_W;
        }
        else {
            this.leftWalk = Input.KEY_LEFT;
            this.rightWalk = Input.KEY_RIGHT;
            this.jump = Input.KEY_UP;
        }
    }

    public void update(GameContainer c, int delta) {
        player.playerAnimation.update(delta);

        Input walkInput = c.getInput();
        if (walkInput.isKeyDown(leftWalk)) {
            player.playerAnimation.start();
            player.setXDir(-1);
        }
        else if (walkInput.isKeyDown(rightWalk)) {
            player.playerAnimation.start();
            player.setXDir(1);
        }
        else if (walkInput.isKeyDown(jump)) {
            player.setYDir(1);
        }
        else  {
            player.playerAnimation.stop();
            player.playerAnimation.setCurrentFrame(0);
        }
    }
    public void draw() {
        if (player.getPosition() == 0)
            player.playerAnimation.draw((float) player.getXDir(), (float) player.getXDir());
        else
            player.playerAnimation.draw((float) player.getXDir(), (float) player.getXDir());
    }

}
