
package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import objects.ImageLoader;
import objects.SpriteSheet;
import pacman.Game;

public class MenuState extends GameState {
    
    private String[] option = {"Start", "Quit"};
    private int select = 0;
    
    public ImageLoader i = new ImageLoader();
    
    public SpriteSheet ss1 = new SpriteSheet(i.load("../img/gBlinky.png"));
    public SpriteSheet ss2 = new SpriteSheet(i.load("../img/gPinky.png"));
    public SpriteSheet ss3 = new SpriteSheet(i.load("../img/gInky.png"));
    public SpriteSheet ss4 = new SpriteSheet(i.load("../img/gClyde.png"));
    public int frame;
    public int frameSpeed, frameNumber, startFrame, endFrame;
    public int counterSS = 0;
    
    public Image Blinky, Pinky, Inky, Clyde;
    
    public MenuState(GameStateManager gsm){
        super(gsm);
        init();
    }

    
    public void init() {
        
        startFrame = 2;
        endFrame = startFrame + 1;
        frameNumber = startFrame;
        frameSpeed = 3;  //quanto maior, mais lento
        
    }
    
    @Override
    public void reset() {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

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
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.ITALIC, 36));
        
        for(int i = 0; i < option.length; i++){
            if(select == i)
                g.setColor(Color.red);
            else
                g.setColor(Color.white);
            
            g.drawString(option[i], 200, 450 + 60*i);
        }
    }
    
    public void Animation(){
        
        
        Blinky = ss1.crop2(25*frameNumber, 0, 25, 25);
        Pinky = ss2.crop2(25*frameNumber, 0, 25, 25);
        Inky = ss3.crop2(25*frameNumber, 0, 25, 25);
        Clyde = ss4.crop2(25*frameNumber, 0, 25, 25);
        
        //System.out.println(counterSS);
        //System.out.println(frameNumber);
        
        
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
        int k = e.getKeyCode();
        
        if(k == KeyEvent.VK_ENTER){
            if(select == 0)
                gsm.states.push(new Leve1State(gsm));
            else
                System.exit(0);
        }
        
        if(k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN){
            if(select == 0)
                select = 1;
            else
                select = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
