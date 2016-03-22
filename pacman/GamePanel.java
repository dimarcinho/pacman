
package pacman;

import objects.ImageLoader;
import objects.Maze;
import objects.SpriteSheet;
import input.KeyInput;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import objects.DotController;
import objects.GateController;
import objects.Ghost;
import objects.PillController;
import objects.Player;


public class GamePanel extends JPanel implements ActionListener {
    
    public Timer t;
    String background = "/img/maze.jpg";
    
    private Image dbImage;
    private Graphics dbg;    
    
    public ImageLoader i = new ImageLoader();
    public SpriteSheet ss = new SpriteSheet(i.load("../img/maze_spritesheet2.png"));
    public Image imgTest;
    
    public Player p;    
    public Ghost Blinky, Pinky, Inky, Clyde;
    
    public Maze maze;
    public DotController dc;    
    public GateController gc;
    public PillController pc;
    
    public GamePanel(){
        
        setFocusable(true);
        
        t = new Timer(20, this);
        t.start();
        
        maze = new Maze();
        dc = new DotController();        
        gc = new GateController();
        pc = new PillController();
        maze.createDots(dc);
        maze.createPills(pc);        
        maze.createGates(gc);   
        
        
        p = new Player(240, 465);        
        Blinky = new Ghost(240, 250);//240,250
        
        addKeyListener(new KeyInput(p));
        
        init();
    }
    
    public void init(){}    
    
    public void paint(Graphics g){        
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
    }
    
    public void paintComponent(Graphics g){        
        maze.draw(g);
        dc.draw(g);
        pc.draw(g);
        p.draw(g);        
        Blinky.draw(g);
        gc.draw(g);
    }
    
    public Image getBackgroundImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(background));
        return i.getImage();        
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        p.update();        
        Blinky.update();
        maze.update();
        dc.eatDot(p);
        pc.eatPill(p);
        gc.collision(p);
        //Blinky.chase(p);
        gc.collision(Blinky);
        
        repaint();
    }
    
}
