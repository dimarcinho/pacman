
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class GateController {
   
    public LinkedList<Gate> gateList;
    
    public GateController(){        
        gateList = new LinkedList<Gate>();
    }
    
    public void update(){}
    
    public void addGate(Gate gate){
        gateList.add(gate);
    }
    
    public void removeGate(Gate gate){
        gateList.remove(gate);
    }
    
    public void draw(Graphics g){
        
        for(int i = 0; i < gateList.size(); i++){
            g.setColor(Color.red);
            g.fillRect(gateList.get(i).x+7, gateList.get(i).y+7, 3, 3);
        }
    }
    
    public void collision(Player p){        
        
        for(int i = 0; i < gateList.size(); i++){
            if(p.getCenterBounds().intersects(gateList.get(i).getBounds())){
                p.collisionGate(gateList.get(i));                
            }
        }
        
    }
    
}
