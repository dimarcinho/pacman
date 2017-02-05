
package gamestates;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import objects.DotController;
import objects.GateController;
import objects.Ghost;
import objects.GhostController;
import objects.ImageLoader;
import objects.Maze;
import objects.PillController;
import objects.Player;
import objects.Sound;
import objects.SpriteSheet;

public class Leve1State extends GameState {

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
    public GhostController ghosts;
    
    private int counter = 501;
    //private int state = 1;
    
    private boolean paused = false;
    private boolean reset = false;
    
    public Sound sounds = new Sound();   
    
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
        
        Blinky = new Ghost(240, 250, 0); //240, 250 no jogo de fato
        Pinky = new Ghost(170, 250, 1);
        Inky = new Ghost(270, 250, 2);
        Clyde = new Ghost(320, 250, 3);
        
        ghosts.addGhost(Blinky);
        ghosts.addGhost(Pinky);
        ghosts.addGhost(Inky);
        ghosts.addGhost(Clyde);                
        
    }
    
    @Override
    public void init() {
        
    }
    
    @Override
    public void reset() {
        
        reset = true;
        
        Blinky.x = 240;
        Blinky.y = 250;
        Pinky.x = 170;
        Pinky.y = 250;
        Inky.x = 270;
        Inky.y = 250;
        Clyde.x = 320;
        Clyde.y = 250;
        p.reset();
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }
    
    public void waitSeconds(long s){
        long startTime, endTime;
        startTime = System.currentTimeMillis();
        endTime = startTime + s*1000;
        while(System.currentTimeMillis() < endTime){
            //just do nothing for wait...
        }
    }

    @Override
    public void update() {
        
        sounds.siren.loop();
        
        if(reset){
            this.waitSeconds(1);
            reset = false;
        }
        
        checkLifes();

        //this.waitSeconds(2);
        
        p.update();
        ghosts.update();

        maze.update();
        
        dc.eatDot(p);
        pc.eatPill(p, ghosts);
        if(dc.dotList.size() == 0 && pc.pillList.size() == 0){
            this.newLevel();
        }
        gc.collision(p);

        gc.collision(Blinky);
        gc.collision(Pinky);
        gc.collision(Inky);
        gc.collision(Clyde);

        if(counter % 250 == 0){
            
            counter = 501;
            for(Ghost ghost : ghosts.ghostList){
                if(ghost.state == 0){
                    ghost.state = 1;
                } else if(ghost.state == 1){
                    ghost.state = 0;
                }
            }
        }
        counter++;

        gc.GhostMove(Blinky, p, Blinky);
        gc.GhostMove(Pinky, p, Blinky);
        gc.GhostMove(Inky, p, Blinky);
        gc.GhostMove(Clyde, p, Blinky);

        ghosts.collision(p);
        
        //System.out.println(ghosts.deadGhosts);
            
    }
    
    public void checkLifes(){
        if(p.lostLife == true){
            sounds.siren.stop();
            sounds.diePacman.play();
            this.waitSeconds(3);
            p.reset();
            for(Ghost g : ghosts.ghostList){
                g.reset();
            }
            p.lostLife = false;
            counter = 501;
            //state = 1;
            reset = true;
        }
        
        if(p.lifes.lifes == 0)
            gsm.states.push(new GameOverState(gsm));
            
    }
    
    public void newLevel(){        
        gsm.states.push(new Leve1State(gsm));
    }

    @Override
    public void draw(Graphics g) {
        maze.draw(g);
        dc.draw(g);
        pc.draw(g);
        
        p.draw(g);        
        ghosts.draw(g);
        //gc.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        p.keyPressed(e);
        
        int k = e.getKeyCode();
        
        if(k == KeyEvent.VK_P){            
            gsm.addState(new PausedState(this.gsm));
        }
        
        if(k == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        p.keyReleased(e);
    }
    
}
