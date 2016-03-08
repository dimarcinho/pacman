
package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import pacman.Game;
import pacman.GlobalPosition;


public class Player extends GlobalPosition {
    
    private String playerimage = "/img/pacman_teste.png";
    
    int velX, velY;
    
    public Player(int x, int y){
        super(x,y);
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
        if(x > Game.WIDTH-32){
            x = Game.WIDTH-32;
        }        
        if(y > Game.HEIGHT-2*32){
            y = Game.HEIGHT-2*32;
        }
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP){
            velY = -3;
        } else if(key == KeyEvent.VK_DOWN) {
            velY = +3;
        } else if(key == KeyEvent.VK_LEFT) {
            velX = -3;
        } else if(key == KeyEvent.VK_RIGHT){
            velX = +3;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP){
            velY = 0;
        } else if(key == KeyEvent.VK_DOWN) {
            velY = 0;
        } else if(key == KeyEvent.VK_LEFT) {
            velX = 0;
        } else if(key == KeyEvent.VK_RIGHT){
            velX = 0;
        }   
    }
    
    public void draw(Graphics g2d){
        g2d.drawImage(getPlayerImage(), x, y, null);
    }
    
    public Image getPlayerImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(playerimage));
        return i.getImage();
        
    }
}
