
package objects;

import img.ImageLoader;
import img.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import pacman.Game;
import pacman.GlobalPosition;


public class Player extends GlobalPosition {
    
    private ImageLoader i = new ImageLoader();
    private SpriteSheet ss;
    private int frame;
    int frameSpeed, frameNumber, startFrame, endFrame;
    private int counterSS = 0;
    private Image frameSS;
    
    private int centerX, centerY;
    
    private int velX, velY;
    private int preVelX, preVelY;
    
    private int speed = 3;
    
    public Player(int x, int y){
        super(x,y);
        
        this.centerX = x+9-2;
        this.centerY = y+9-2;
        
        
        startFrame = 8;
        endFrame = startFrame + 3;
        frameNumber = startFrame;
        frameSpeed = 4;
        
        preVelX = 0;
        preVelY = 0;
        
        velX = -speed;
        preVelX = -speed;
        
        ss = new SpriteSheet(i.load("/img/pacman_spritesheet.png"));
    }        
    
    public void update(){
        x += velX;
        y += velY;
        
        //Basic collision with walls
        /*
        if(x <= 0){
            x = 0;
        }        
        if(y <= 0){
            y = 0;
        }        
        if(x > Game.WIDTH-37){
            x = Game.WIDTH-37;
        }        
        if(y > Game.HEIGHT-2*32-3){
            y = Game.HEIGHT-2*32-3;
        }
         * 
         */
        
        this.Animation();
    }
    
    public void collisionWall(){
        
        int tempVelX = velX;
        int tempVelY = velY;
        
        if(velX != 0){
            velX = 0;
            if(tempVelX > 0)
                x -= 4;
            else
                x += 4;
            
        } else if(velY != 0){
            velY = 0;
            if(tempVelY > 0)
                    y -= 4;
                else
                    y += 4;
        }
        
    }
    
    public void collisionGate(Gate gate){
        
        velX = preVelX;
        velY = preVelY;      
        
        if(gate.up == false && velY < 0){
            velY = 0;
        }
        
        if(gate.down == false && velY > 0){
            velY = 0;
        }
        
        if(gate.left == false && velX < 0){
            velX = 0;
        }
        
        if(gate.right == false && velX > 0){
            velX = 0;
        }
        
          
        /*
        System.out.println("Up: "+gate.up);
        System.out.println("Left: "+gate.left);
        System.out.println("Right: "+gate.right);
        System.out.println("Down: "+gate.down);
        System.out.println("");
         * 
         */
        
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP){
            
            //velY = -speed;
            preVelY = -speed;
            //velX = 0;
            preVelX = 0;
            
            startFrame = 4;
            endFrame = startFrame + 3;
            frameNumber = startFrame;
            
        } else if(key == KeyEvent.VK_DOWN) {
            
            //velY = +speed;
            preVelY = +speed;
            //velX = 0;
            preVelX = 0;
            
            startFrame = 12;
            endFrame = startFrame + 3;
            frameNumber = startFrame;
            
        } else if(key == KeyEvent.VK_LEFT) { 
            
            //velX = -speed;
            preVelX = -speed;
            //velY = 0;
            preVelY = 0;
            
            startFrame = 8;
            endFrame = startFrame + 3;
            frameNumber = startFrame;
            
        } else if(key == KeyEvent.VK_RIGHT){
            //velX = +speed;
            preVelX = +speed;
            //velY = 0;
            preVelY = 0;
            
            startFrame = 0;
            endFrame = startFrame + 3;
            frameNumber = startFrame;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP){
            //velY = 0;            
        } else if(key == KeyEvent.VK_DOWN) {
            //velY = 0;            
        } else if(key == KeyEvent.VK_LEFT) {
            //velX = 0;            
        } else if(key == KeyEvent.VK_RIGHT){
            //velX = 0;            
        }   
    }
    
    public void draw(Graphics g2d){
        g2d.drawImage(getPlayerImage(), x, y, null);
        g2d.setColor(Color.red);
        //g2d.drawRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
        //g2d.drawRect(this.getCenterBounds().x, this.getCenterBounds().y, this.getCenterBounds().width, this.getCenterBounds().height);
    }
    
    public Image getPlayerImage(){
        return frameSS;        
    }
    
    public void Animation(){
        
        frameSS = ss.crop2(25*frameNumber, 0, 25, 25);
        //System.out.println(counterSS);
        
        if(counterSS % frameSpeed == 0){
            if(frameNumber < endFrame){
                frameNumber++;
            } else {
                frameNumber = startFrame;
            }   
        }
        
        if(counterSS > 20*frameSpeed){
            counterSS = 0;
        }else {
            counterSS++;
        }
    }
    
    public Rectangle getBounds(){
        Rectangle r = new Rectangle(this.x+3, this.y+3, 15, 15);
        return r;
    }
    
    public Rectangle getCenterBounds(){
        Rectangle r = new Rectangle(this.x+7, this.y+7, 5, 5);
        return r;
    }
}
