
package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

public class Ghost extends Entity {
    
    private int lastDir = 0;
    public int rndDir;
    
    public int tipo;
    
    private int chase = 0;
    
    //private int state; //0 - scatter, 1 - chase, 2 - frightened
    
    public Ghost(int x, int y, int tipo){
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        
        init();
    }
    
    @Override
    public void init(){
    
        //state = 0;
        startFrame = 4;
        endFrame = startFrame + 1;
        frameNumber = startFrame;
        frameSpeed = 4; //quanto maior, mais lento
        
        try{
        
            if(this.tipo == 0){
                ss = new SpriteSheet(i.load("/img/gBlinky.png"));
                System.out.println("Criado Blinky!"); 
            } else if(this.tipo == 1){
                ss = new SpriteSheet(i.load("/img/gPinky.png"));
                System.out.println("Criado Pinky!");
            }else if(this.tipo == 2){
                ss = new SpriteSheet(i.load("/img/gInky.png"));
                System.out.println("Criado Inky!");
            }else if(this.tipo == 3){
                ss = new SpriteSheet(i.load("/img/gClyde.png"));
                System.out.println("Criado Clyde!");
            }
            
        }catch (Exception e){System.out.println("Erro na inicialização dos fantasmas: "+e);}
        
        

        velX = -speed;
        //preVelX = -speed;
    }
    
    @Override
    public void update(){
    
        x += velX;
        y += velY;
        
        
        //passagem pelo túnel
        if(x < -35 && y > 290 && y < 310)
            x = 494;        
        if(x > 510 && y > 290 && y < 310)
            x = -19;
        
        this.Animation();
        
    }
    
    public void chase(Entity e){
        
        Random rndGen = new Random();
        chase = rndGen.nextInt(1000);
        
        if(chase % 2 == 0){
            if(e.x > this.x){
                velX = speed;
            } else {
                velX = -speed;
            }
            preVelY = 0;
        
        } else {
           if(e.y > this.y){
                velY = speed;
            } else {
                velY = -speed;
            }
            velX = 0;        
        }

    }
    
    @Override
    public void collisionGate(Gate gate){
        
        boolean[] g = new boolean[4];
        g[0] = gate.up;
        g[1] = gate.right;
        g[2] = gate.down;
        g[3] = gate.left;
        
        if(velX != 0){
                if(velX > 0)
                    g[3] = false;
                else
                    g[1] = false;
            }

            if(velY != 0){
               if(velY > 0)
                    g[0] = false;
                else
                    g[2] = false; 
        }
        
        int saidas = 0;
        for(int i = 0; i < g.length; i++){
            if(g[i] == true){
                saidas++;
            }
        }
        
        
        if((velX > 0 && gate.right == false) ||
            (velX < 0 && gate.left == false) ||
            (velY > 0 && gate.down == false) ||
            (velY < 0 && gate.up == false) ||
                saidas > 1){

            Random rndGen = new Random();
            do{
                rndDir = rndGen.nextInt(4);            
            } while(g[rndDir] == false);

            setVel(rndDir);
        }

    }
    
    public void setVel(int k){
        switch(k){
            default: System.out.println("Erro em setVel()");
                break;
            case 0:
                velY = -speed;
                velX = 0;
                break;
            case 1:
                velX = speed;
                velY = 0;
                break;
            case 2:
                velY = speed;
                velX = 0;
                break;
            case 3:
                velX = -speed;
                velY = 0;
                break;
        }
        changeDirection();
    }

    
    @Override
    public void draw(Graphics g){
        g.drawImage(getEntityImage(), x, y, null);        
    }

    public void changeDirection(){
        if(velX > 0){
            startFrame = 0;
            endFrame = startFrame + 1;
            frameNumber = startFrame;    
        }
        if(velX < 0){
            startFrame = 4;
            endFrame = startFrame + 1;
            frameNumber = startFrame;    
        }
        if(velY > 0){
            startFrame = 2;
            endFrame = startFrame + 1;
            frameNumber = startFrame;    
        }
        if(velY < 0){
            startFrame = 6;
            endFrame = startFrame + 1;
            frameNumber = startFrame;    
        }
    }

}
