
package gamestates;

import input.KeyInput;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import objects.DotController;
import objects.GateController;
import objects.Ghost;
import objects.GhostController;
import objects.ImageLoader;
import objects.Lifes;
import objects.Maze;
import objects.PillController;
import objects.Player;
import objects.SpriteSheet;

public class Leve1State extends GameState {

    private Image dbImage;
    private Graphics dbg;    
    
    public ImageLoader i = new ImageLoader();
    public SpriteSheet ss = new SpriteSheet(i.load("../img/maze_spritesheet2.png"));
    public Image imgTest;
    
    public Player p;    
    public Ghost Blinky, Pinky, Inky, Clyde;
    
    public Lifes lifes;
    
    public Maze maze;
    public DotController dc;    
    public GateController gc;
    public PillController pc;
    public GhostController ghosts;
    
    public Leve1State(GameStateManager gsm){
        super(gsm);
        
        maze = new Maze();
        dc = new DotController();        
        gc = new GateController();
        pc = new PillController();
        ghosts = new GhostController();
        maze.createDots(dc);
        maze.createPills(pc);        
        maze.createGates(gc);
        
        
        p = new Player(240, 465);
        lifes = new Lifes();        
        
        Blinky = new Ghost(240, 250, 0);
        Pinky = new Ghost(105, 69, 1);
        Inky = new Ghost(465, 573, 2);
        Clyde = new Ghost(321, 357, 3);
        
        ghosts.addGhost(Blinky);
        ghosts.addGhost(Pinky);
        ghosts.addGhost(Inky);
        ghosts.addGhost(Clyde);
        
        //addKeyListener();
    }
    
    @Override
    public void init() {
        
    }

    @Override
    public void update() {
            p.update();   

            ghosts.update();

            maze.update();
            dc.eatDot(p);
            pc.eatPill(p);
            gc.collision(p);

            gc.collision(Blinky);
            gc.collision(Pinky);
            gc.collision(Inky);
            gc.collision(Clyde);
            
            ghosts.collision(p, lifes);
    }

    @Override
    public void draw(Graphics g) {
        maze.draw(g);
        dc.draw(g);
        pc.draw(g);
        
        p.draw(g);
        lifes.draw(g);
        
        ghosts.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
