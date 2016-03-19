
package objects;

import java.util.LinkedList;


public class WallController {
    
    public LinkedList<Wall> wallList;
    
    public WallController(){        
        wallList = new LinkedList<Wall>();
    }
    
    public void update(){}
    
    public void addWall(Wall wall){
        wallList.add(wall);
    }
    
    public void removeWall(Wall wall){
        wallList.remove(wall);
    }
    
    public void collision(Player p){        
        
        for(int i = 0; i < wallList.size(); i++){
            if(p.getBounds().intersects(wallList.get(i).getBounds())){
                p.collisionWall();                
            }
        }
        
    }
}
