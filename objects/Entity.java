
package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


public abstract class Entity {
    
    public ImageLoader i = new ImageLoader();
    public SpriteSheet ss;
    public int frame;
    public int frameSpeed, frameNumber, startFrame, endFrame;
    public int counterSS = 0;
    public Image frameSS;
    public int direction = 3;
    
    public Gate gatePos = null;
    
    public int centerX, centerY;
    
    public int x,y;
    public int velX, velY;
    public int preVelX, preVelY;
    
    public int speed = 3;
    
    public void init(){}
    public void update(){}
    public void draw(Graphics g){}
    public void collisionGate(Gate gate){}
    
    public Image getEntityImage(){
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
        } else {
            counterSS++;
        }
        
        if(velX == 0 && velY == 0)
            counterSS--;
    }
    
    public Rectangle getBounds(){
        Rectangle r = new Rectangle(this.x+8, this.y+8, 9, 9);
        return r;
    }
    
    public Rectangle getCenterBounds(){
        Rectangle r = new Rectangle(this.x+11, this.y+11, 3, 3);
        return r;
    }
    
    public Gate getGatePos(){
        return gatePos;
    }
    
    public void setGatePos(Gate gate){
        this.gatePos = gate;
    }
    
    public void changeDirection(){
        if(velX > 0){
            startFrame = 0;
            endFrame = startFrame + 3;
            frameNumber = startFrame;
            this.direction = 1;
        }
        if(velX < 0){
            startFrame = 8;
            endFrame = startFrame + 3;
            frameNumber = startFrame;
            this.direction = 3;
        }
        if(velY > 0){
            startFrame = 12;
            endFrame = startFrame + 3;
            frameNumber = startFrame;
            this.direction = 2;
        }
        if(velY < 0){
            startFrame = 4;
            endFrame = startFrame + 3;
            frameNumber = startFrame;
            this.direction = 0;
        }
    }

}
