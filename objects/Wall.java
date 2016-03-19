
package objects;

import java.awt.Rectangle;
import pacman.GlobalPosition;

public class Wall extends GlobalPosition {
    
    public Wall(int x, int y){
        super(x,y);
    }
    
    public void init(){}
    public void update(){}
    
    public Rectangle getBounds(){
        Rectangle r = new Rectangle(x, y, 15, 15);
        return r;
    }
}
