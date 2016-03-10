
package objects;

import java.awt.Graphics;
import java.util.LinkedList;

public class DotController {
    
    public LinkedList<Dot> dotList;
    
    public DotController(){        
        dotList = new LinkedList<Dot>();
    }
    
    public void update(){
        
    }
    
    public void addDot(Dot dot){
        dotList.add(dot);
    }
    
    public void removeDot(Dot dot){
        dotList.remove(dot);
    }
    
    public void draw(Graphics g){
        for(int i=0; i< dotList.size(); i++){
            dotList.get(i).draw(g);
        }
    }
    
    public void eatDot(Player p){
        for(int i=0; i< dotList.size(); i++){
            if(p.getBounds().intersects(dotList.get(i).getBounds()))
                dotList.remove(dotList.get(i));
        }
    }
}
