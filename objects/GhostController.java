
package objects;

import java.awt.Graphics;
import java.util.LinkedList;


public class GhostController {
    
    public LinkedList<Ghost> ghostList;
    public Sound sound = new Sound();
    
    public int deadGhosts = 1;
    
    public GhostController(){        
        ghostList = new LinkedList<Ghost>();
    }
    
    public void update(){
        
        for(Ghost g : ghostList){
            g.update();
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
    
    public void collision(Player p){  
        
        for(Ghost g : ghostList){
            if(p.getBounds().intersects(g.getBounds())){
                if(g.state == 0 || g.state == 1){
                    p.loseLife();
                } else if(g.state == 2){
                    //fantasma morre
                    g.setState(3);
                    p.score.addPoints(deadGhosts*400);
                    sound.eatGhost.play();
                    //deadGhosts++;
                    //acertar esta parte
                    //if(deadGhosts == 4)
                    //    deadGhosts = 1;
                }
            }
        }
    }
}
