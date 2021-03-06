package com.ciderBrewers.core;

import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VolleyballGame extends BasicGame {

//    private SpriteSheet npc;
//    private Animation npcAnimation;
//    double x, y;
    private Controller con1, con2;


    public VolleyballGame(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer c) throws SlickException {
        con1 = new Controller(new Player(new SpriteSheet("data/spr/NPCp.png", 44, 123), 100, 0));
        con2 = new Controller(new Player(new SpriteSheet("data/spr/NPCp.png", 44, 123), 800, 1));
//        npc = new SpriteSheet("data/spr/NPCp.png", 44, 123);
//        npcAnimation = new Animation(npc, 300);
//        npcAnimation.setPingPong(true);
//        npcAnimation.stop();
    }

    @Override
    public void update(GameContainer c, int delta) throws SlickException {
        con1.update(c, delta);
        con2.update(c, delta);
//        npcAnimation.update(delta);
//        Input walkInput = c.getInput();
//        if (walkInput.isKeyDown(Input.KEY_LEFT)) {
//            npcAnimation.start();
//        }
//        else if (walkInput.isKeyDown(Input.KEY_RIGHT)) {
//            npcAnimation.start();
//        }
//        else  {
//            npcAnimation.stop();
//            npcAnimation.setCurrentFrame(0);
//        }
//        else if (!walkInput.isKeyDown(Input.KEY_RIGHT)) {
//            npcAnimation.stop();
//            npcAnimation.setCurrentFrame(0);
//        }
    }

    @Override
    public void render(GameContainer c, Graphics g) throws SlickException {
//        npcAnimation.draw(200, 200, -44, 123);
//        npcAnimation.draw(300, 200, 44, 123);
        con1.draw();
        con2.draw();
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer app;
            app = new AppGameContainer(new VolleyballGame("Simple Slick Game"));
            app.setDisplayMode(900, 600, false);
            app.start();
            app.setAlwaysRender(true);
        }
        catch (SlickException ex)
        {
            Logger.getLogger(VolleyballGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
