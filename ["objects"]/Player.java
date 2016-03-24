
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
        startFrame = 8;
        endFrame = startFrame + 3;
        frameNumber = startFrame;
        frameSpeed = 4; //quanto maior, mais lento
        
        preVelX = 0;
        preVelY = 0;
        
        velX = -speed;
        preVelX = -speed;
        
        ss = new SpriteSheet(i.load("/img/pacman_spritesheet.png"));    
    }
    
    @Override
    public void update(){
        x += velX;
        y += velY;
        
        //passagem pelo túnel
        if(x < -35 && y > 290 && y < 310)
            x = 494;        
        if(x > 510 && y > 290 && y < 310)
            x = -19;        
        
        this.Animation();
    }

    
    @Override
    public void collisionGate(Gate gate){     
        
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
    
    @Override
    public void draw(Graphics g2d){
        g2d.drawImage(getEntityImage(), x, y, null);
        g2d.setColor(Color.red);
        //g2d.drawRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
        //g2d.drawRect(this.getCenterBounds().x, this.getCenterBounds().y, this.getCenterBounds().width, this.getCenterBounds().height);
        g2d.drawString("("+this.x+","+this.y+")", 100, 30);
    }

}
