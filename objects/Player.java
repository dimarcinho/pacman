
package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import pacman.Game;
import pacman.GlobalPosition;


public class Player extends GlobalPosition {
    
    private String playerimage = "/img/pacman_piskel.gif";
    
    private int centerX, centerY;
    
    private int velX, velY;
    private int speed = 3;
    
    public Player(int x, int y){
        super(x,y);
        
        this.centerX = x+9-2;
        this.centerY = y+9-2;
    }
    
    public void update(){
        x += velX;
        y += velY;
        
        //Basic collision with walls        
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
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP){
            velY = -speed;
            velX = 0;
        } else if(key == KeyEvent.VK_DOWN) {
            velY = +speed;
            velX = 0;
        } else if(key == KeyEvent.VK_LEFT) {            
            velX = -speed;
            velY = 0;
        } else if(key == KeyEvent.VK_RIGHT){
            velX = +speed;
            velY = 0;
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
    }
    
    public Image getPlayerImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(playerimage));
        return i.getImage();
        
    }
    
    public Rectangle getBounds(){
        Rectangle r = new Rectangle(this.x-3, this.y, 32, 32);
        return r;
    }
    
    public Rectangle getCenterBounds(){
        Rectangle r = new Rectangle(centerX, centerY, 4, 4);
        return r;
    }
}
