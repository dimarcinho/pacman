
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;



public class Player extends Entity {
    
    public Player(int x, int y){
        
       this.x = x;
       this.y = y;
       
       init();
        
    }
    
    @Override
    public void init(){
        this.startFrame = 8;
        this.endFrame = startFrame + 3;
        this.frameNumber = startFrame;
        this.frameSpeed = 4; //quanto maior, mais lento
        
        this.preVelX = 0;
        this.preVelY = 0;
        
        this.velX = -speed;
        this.preVelX = -speed;
        
        this.ss = new SpriteSheet(i.load("/img/pacman_spritesheet.png"));    
    }
    
    @Override
    public void update(){
        this.x += this.velX;
        this.y += this.velY;
        
        //passagem pelo túnel
        if(this.x < -35 && y > 290 && this.y < 310)
            this.x = 494;        
        if(this.x > 510 && y > 290 && this.y < 310)
            this.x = -19;        
        
        this.Animation();
    }

    
    @Override
    public void collisionGate(Gate gate){     
        
        int tempVelX, tempVelY;
        tempVelX = velX;
        tempVelY = velY;
        
        if(gate.up == false && velY < 0){
            velY = 0;
        }
        
        if(gate.up == true && preVelY < 0){
            velY = preVelY;
            velX = 0;
        }
        
        if(gate.down == false && velY > 0){
            velY = 0;
        }
        
        if(gate.down == true && preVelY > 0){
            velY = preVelY;
            velX = 0;
        }
        
        if(gate.left == false && velX < 0){
            velX = 0;
        }
        
        if(gate.left == true  && preVelX < 0){
            velX = preVelX;
            velY = 0;
        }
        
        if(gate.right == false && velX > 0){
            velX = 0;            
        }
        
        if(gate.right == true  && preVelX > 0){
            velX = preVelX;
            velY = 0;
        }
        
        if(tempVelX != velX || tempVelY != velY)
            this.changeDirection();
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP){
            
            preVelY = -speed;
            preVelX = 0;
            
        } else if(key == KeyEvent.VK_DOWN) {
            
            preVelY = +speed;
            preVelX = 0;
            
        } else if(key == KeyEvent.VK_LEFT) { 
            
            preVelX = -speed;
            preVelY = 0;

        } else if(key == KeyEvent.VK_RIGHT){

            preVelX = +speed;
            preVelY = 0;

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
    
    @Override
    public void draw(Graphics g2d){
        g2d.drawImage(getEntityImage(), x, y, null);
        g2d.setColor(Color.red);
        //g2d.drawRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
        //g2d.drawRect(this.getCenterBounds().x, this.getCenterBounds().y, this.getCenterBounds().width, this.getCenterBounds().height);
        g2d.drawString("("+this.x+","+this.y+")", 100, 30);
    }
    
    public void changeDirection(){
        if(velX > 0){
            startFrame = 0;
            endFrame = startFrame + 3;
            frameNumber = startFrame;    
        }
        if(velX < 0){
            startFrame = 8;
            endFrame = startFrame + 3;
            frameNumber = startFrame;    
        }
        if(velY > 0){
            startFrame = 12;
            endFrame = startFrame + 3;
            frameNumber = startFrame;    
        }
        if(velY < 0){
            startFrame = 4;
            endFrame = startFrame + 3;
            frameNumber = startFrame;    
        }
    }

}
