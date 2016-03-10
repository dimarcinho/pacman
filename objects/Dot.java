
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import pacman.GlobalPosition;

public class Dot extends GlobalPosition {
    
    private int centerX, centerY;
    
    public Dot(int x, int y){
        super(x,y);
        this.centerX = x+9-2;
        this.centerY = y+9-2;
    }    
    public void init(){}
    public void update(){}
    
    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(this.x+9, this.y+6, 4, 4);
    }
    
    public Rectangle getBounds(){
        Rectangle r = new Rectangle(centerX, centerY, 4, 4);
        return r;
    }
}
