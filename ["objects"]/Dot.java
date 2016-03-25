
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import pacman.GlobalPosition;

public class Dot extends GlobalPosition {
    
    public Dot(int x, int y){
        super(x,y);

    }    
    public void init(){}
    public void update(){}
    
    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(this.x+7, this.y+7, 4, 4);
    }
    
    public Rectangle getBounds(){
        Rectangle r = new Rectangle(this.x+7, this.y+7, 4, 4);
        return r;
    }
}
