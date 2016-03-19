
package pacman;

import img.ImageLoader;
import img.Maze;
import img.SpriteSheet;
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
import objects.Monstro;
import objects.Player;
import objects.WallController;

public class GamePanel extends JPanel implements ActionListener {
    
    public Timer t;
    String background = "/img/maze.jpg";
    
    private Image dbImage;
    private Graphics dbg;    
    
    public ImageLoader i = new ImageLoader();
    public SpriteSheet ss = new SpriteSheet(i.load("maze_spritesheet2.png"));
    public Image imgTest;
    
    public Player p;
    public Monstro m;
    
    public Maze maze;
    public DotController dc;
    public WallController wc;
    public GateController gc;
    
    public GamePanel(){
        
        setFocusable(true);
        
        t = new Timer(20, this);
        t.start();
        
        p = new Player(240, 465);
        m = new Monstro(100, 140);
        
        maze = new Maze();
        dc = new DotController();
        wc = new WallController();
        gc = new GateController();
        maze.createObjects(dc);
        //maze.createWalls(wc);
        maze.createGates(gc);
        
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
        //super.paintComponent(g);        
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(getBackgroundImage(),0,0,this);
        maze.draw(g2d);
        dc.draw(g2d);
        p.draw(g2d);
        m.draw(g2d);
        //gc.draw(g2d);
    }
    
    public Image getBackgroundImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(background));
        return i.getImage();        
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        p.update();
        m.update();
        maze.update();
        dc.eatDot(p);
        //wc.collision(p);
        
        gc.collision(p);
        
        repaint();
    }
    
}
