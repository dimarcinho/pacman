/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Graphics;
import java.util.LinkedList;

public class PillController {

    public LinkedList<Pill> pillList;
    
    public PillController(){        
        pillList = new LinkedList<Pill>();
    }
    
    public void update(){
        
    }
    
    public void addPill(Pill pill){
        pillList.add(pill);
    }
    
    public void removePill(Pill pill){
        pillList.remove(pill);
    }
    
    public void draw(Graphics g){
        for(int i=0; i< pillList.size(); i++){
            pillList.get(i).draw(g);
        }
    }
    
    public void eatPill(Player p){
        for(int i=0; i< pillList.size(); i++){
            if(p.getBounds().intersects(pillList.get(i).getBounds()))
                pillList.remove(pillList.get(i));
        }
    }    
    
}
