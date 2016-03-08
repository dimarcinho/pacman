/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import pacman.Game;
import pacman.GlobalPosition;


public class Monstro extends GlobalPosition {
    
    private String fileName = "/img/monstro_teste.png";
    
    int velX = 3, velY = 0;
    
    public Monstro(int x, int y){
        super(x,y);
    }
    
    public void update(){
        x += velX;
        y += velY;
        
        //Basic collision with walls        
        if(x <= 0){
            velX = -velX;
        }        
        if(y <= 0){
            velY = -velY;
        }        
        if(x > Game.WIDTH - 32){
            velX = -velX;
        }        
        if(y > Game.HEIGHT - 32){
            velY = -velY;
        }
    }
    
    public void draw(Graphics g2d){
        g2d.drawImage(getMonstroImage(), x, y, null);
    }
    
    public Image getMonstroImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(fileName));
        return i.getImage();
        
    }
}
