
package objects;

import java.awt.Rectangle;
import pacman.GlobalPosition;

public class Gate extends GlobalPosition {
    
    public boolean up, down, left, right;
    
     public Gate(int x, int y){
        super(x,y);
        up = true;
        down = true;
        left = true;
        right = true;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
    
     
     
    public void init(){}
    public void update(){}    
    
    public Rectangle getBounds(){
        Rectangle r = new Rectangle(x+7, y+7, 3, 3);
        return r;
    }   
}
