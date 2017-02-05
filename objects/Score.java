
package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {

    public static long points = 0;
    
    public Score(){
        //points = 0;
    }
    
    public void init(){}
    public void update(){}
    
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score", 50, 30);
        g.drawString(""+points, 50, 50);
    }
    
    public void addPoints(int k){
        points += k;
    }
    
    public long getPoints() {
        return points;
    }

}
