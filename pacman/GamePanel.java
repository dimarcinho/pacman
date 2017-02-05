
package pacman;

import gamestates.GameStateManager;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class GamePanel extends JPanel implements ActionListener, KeyListener {
    
    public Timer t;
    String background = "/img/maze.jpg";
    
    public GameStateManager gsm;
   
    private Image dbImage;
    private Graphics dbg;
    private int FPS;
    
    public GamePanel(){
        
        setFocusable(true);
        
        FPS = 45;
        
        gsm = new GameStateManager();
        
        addKeyListener(this);
        
        init();
                        
        t = new Timer(1, this);
        t.start();

    }
    
    public void init(){}    
    
    public void paint(Graphics g){        
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
    }
    
    public void paintComponent(Graphics g){        
        
        gsm.draw(g);
    }
    
    public Image getBackgroundImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(background));
        return i.getImage();        
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        
        
        try{
           
            long startTime, endTime;
            startTime = System.currentTimeMillis();        
            gsm.update();            
            endTime = System.currentTimeMillis();
            while(endTime - startTime < 1000/FPS ){
                endTime = System.currentTimeMillis();    
            }
            
            
        } catch(NullPointerException s) {
            
            //s.printStackTrace();
            throw new IllegalStateException("Game has a null property", s);
        }
        
        
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e);
    }
    
}
