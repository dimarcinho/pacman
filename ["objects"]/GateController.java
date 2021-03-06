
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
            g.fillRect( gateList.get(i).getBounds().x,
                        gateList.get(i).getBounds().y,
                        gateList.get(i).getBounds().width,
                        gateList.get(i).getBounds().height);
        }
    }
    
    public void collision(Entity e){        
        
        for(int i = 0; i < gateList.size(); i++){
            if(e.getCenterBounds().intersects(gateList.get(i).getBounds())){
                e.collisionGate(gateList.get(i));                
            }
        }        
    }

}
