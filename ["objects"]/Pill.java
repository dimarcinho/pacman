
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import pacman.GlobalPosition;

public class Pill extends GlobalPosition {

    private int centerX, centerY;
    
    private int counter;
    
    public Pill(int x, int y){
        super(x,y);
        this.centerX = x+9-2;
        this.centerY = y+9-2;
    }    
    public void init(){}
    public void update(){}
    
    public void draw(Graphics g){
        g.setColor(Color.yellow);
        
        if(counter % 9 == 0){
            g.fillArc(x, y, 16, 16, 0, 360);
        }
        
        counter++;
        
        if(counter > 170){
            counter = 0;
            g.fillArc(x+4, y+4, 12, 12, 0, 360);
        }
    }
    
    public Rectangle getBounds(){
        Rectangle r = new Rectangle(centerX, centerY, 4, 4);
        return r;
    }
    
}
