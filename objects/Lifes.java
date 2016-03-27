
package objects;

import java.awt.Graphics;
import java.awt.Image;


public class Lifes {
    
    private int x, y;
    public int lifes;
    private ImageLoader i = new ImageLoader();
    private SpriteSheet ss;
    private Image imgLife;
    
    public Lifes(){
        
        this.lifes = 2;
        
        this.ss = new SpriteSheet(i.load("/img/pacman_spritesheet.png"));
        imgLife = ss.crop2(25*10, 0, 25, 25);
        
    }
    public void init(){}
    public void update(){}
    
    public void draw(Graphics g){
        
        if(lifes > 0){
            for(int i=0; i < lifes; i++){
                g.drawImage(imgLife, (15 + 25*i), 612, null);
            }
        }
    }
    
    public void addLife(){
        lifes++;
    }
    
    public void removeLife(){        
        
        if(lifes <= 1)
            lifes = 0;
        else
            lifes--;
    }

}
