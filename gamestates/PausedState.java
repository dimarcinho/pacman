/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import objects.ImageLoader;
import objects.SpriteSheet;
import pacman.Game;

/**
 *
 * @author Marcio
 */
public class PausedState extends GameState {
    
    private ImageLoader i = new ImageLoader();
    
    private SpriteSheet ss1 = new SpriteSheet(i.load("../img/gBlinky.png"));
    private SpriteSheet ss2 = new SpriteSheet(i.load("../img/gPinky.png"));
    private SpriteSheet ss3 = new SpriteSheet(i.load("../img/gInky.png"));
    private SpriteSheet ss4 = new SpriteSheet(i.load("../img/gClyde.png"));
    private int frame;
    private int frameSpeed, frameNumber, startFrame, endFrame;
    private int counterSS = 0;
    
    private Image Blinky, Pinky, Inky, Clyde;
    
    public PausedState(GameStateManager gsm){
        super(gsm);

        startFrame = 2;
        endFrame = startFrame + 1;
        frameNumber = startFrame;
        frameSpeed = 3;  //quanto maior, mais lento        
    }

    @Override
    public void init() {}
    @Override
    public void reset() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}

    @Override
    public void update() {
        this.Animation();        
    }

    @Override
    public void draw(Graphics g) {
        //desenha o fundo
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        
        //desenha os fantasmas
        g.drawImage(Blinky, 150, 200, null);
        g.drawImage(Pinky, 200, 200, null);
        g.drawImage(Inky, 250, 200, null);
        g.drawImage(Clyde, 300, 200, null);
        
        //desenha o menu
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.ITALIC, 48));
        g.drawString("Pacman", Game.WIDTH/2 - 100, 100);
        g.setFont(new Font("Arial", Font.ITALIC, 16));
        g.drawString("clone", Game.WIDTH/2 - 30, 125);
        
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial", Font.ITALIC, 36));        
        g.drawString("Jogo Pausado", Game.WIDTH/2 - 130, 380);
        
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.ITALIC, 12));
        g.drawString("Pressione 'P' para voltar", Game.WIDTH/2 - 80, 420);
        
    }
    
        public void Animation(){
        
        
        Blinky = ss1.crop2(25*frameNumber, 0, 25, 25);
        Pinky = ss2.crop2(25*frameNumber, 0, 25, 25);
        Inky = ss3.crop2(25*frameNumber, 0, 25, 25);
        Clyde = ss4.crop2(25*frameNumber, 0, 25, 25);        
        
        if(counterSS % frameSpeed == 0){
            if(frameNumber < endFrame){
                frameNumber++;
            } else {
                frameNumber = startFrame;
            }   
        }
        
        if(counterSS > 20*frameSpeed){
            counterSS = 0;
        } else {
            counterSS++;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_P){
            gsm.states.pop();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
