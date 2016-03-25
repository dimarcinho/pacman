
package objects;

import java.awt.Graphics;
import java.util.LinkedList;


public class GhostController {
    
    public LinkedList<Ghost> ghostList;
    
    public GhostController(){        
        ghostList = new LinkedList<Ghost>();
    }
    
    public void update(){
    
        for(int i = 0; i < ghostList.size(); i++){
            ghostList.get(i).update();
        }
    
    }
    
    public void addGhost(Ghost ghost){
        ghostList.add(ghost);
    }
    
    public void removeGhost(Ghost ghost){
        ghostList.remove(ghost);
    }
    
    public void draw(Graphics g){
        
        for(int i = 0; i < ghostList.size(); i++){
            ghostList.get(i).draw(g);
        }
        
    }
    
    public void collision(Player p, Lifes l){        
        
        for(int i = 0; i < ghostList.size(); i++){
            if(p.getCenterBounds().intersects(ghostList.get(i).getCenterBounds())){
                p.loseLife(l);
            }
        }        
    }
}
