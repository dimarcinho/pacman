
package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
    
    public MenuState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    public void init() {
        
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, 700, 700);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", 0, 36));
        g.drawString("Press Enter to begin", 50, 300);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gsm.states.push(new Leve1State(gsm));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
