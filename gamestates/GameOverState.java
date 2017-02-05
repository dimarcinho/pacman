
package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import pacman.Game;

public class GameOverState extends GameState {
    
    private int counter = 360;

    public GameOverState(GameStateManager gsm){
        super(gsm);
    }
    
    @Override
    public void init() {
        
    }

    @Override
    public void reset() {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void update() {
        if(counter == 0)
            gsm.states.push(new MenuState(gsm));
        else
            counter--;        
    }

    @Override
    public void draw(Graphics g) {
    
        //desenha o fundo
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        
        //desenha o menu
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.ITALIC, 48));
        g.drawString("GameOver", Game.WIDTH/2 - 120, Game.HEIGHT/2);
        
        //desenha o loading
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial", Font.ITALIC, 16));
        g.drawString("Restarting...", Game.WIDTH/2 - 35, Game.HEIGHT/2 + 80);
        g.fillArc(Game.WIDTH/2 - 32, Game.HEIGHT/2 + 100, 75, 75, 0, counter);
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
