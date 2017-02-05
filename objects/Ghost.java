
package objects;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import pacman.GlobalPosition;

public class Ghost extends Entity {
    
    //private int lastDir = 0;
    public int rndDir;
    
    public int tipo;
    private int x0,y0;
    
    private GlobalPosition target;
    
    //private int chase = 0;
    
    public int state; //0 - scatter, 1 - chase, 2 - frightened, 3 - dead
    private SpriteSheet ssFrightened;
    private SpriteSheet ssDead;
    private SpriteSheet tempSS;
    
    public int frightCounter;
    
    public Ghost(int x, int y, int tipo){
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        
        this.x0 = x;
        this.y0 = y;
        
        init();
    }
    
    @Override
    public void init(){
    
        state = 0;
        this.startFrame = 4;
        this.endFrame = startFrame + 1;
        this.frameNumber = startFrame;
        this.frameSpeed = 4; //quanto maior, mais lento
        
        try{
        
            target = new GlobalPosition(0,0); //variável que controla o canto do Scatter
            switch(tipo){
                default:
                    System.out.println("Erro no switch(tipo)");
                    break;
                case 0:
                    target.x = 465;
                    target.y = 18;
                    this.ss = new SpriteSheet(i.load("/img/gBlinky.png"));
                    break;
                case 1:
                    target.x = 0;
                    target.y = 0;
                    this.ss = new SpriteSheet(i.load("/img/gPinky.png"));
                    break;
                case 2:
                    target.x = 465;
                    target.y = 612;
                    this.ss = new SpriteSheet(i.load("/img/gInky.png"));
                    break;
                case 3:
                    target.x = 0;
                    target.y = 612;
                    this.ss = new SpriteSheet(i.load("/img/gClyde.png"));
                    break;
            }
            
        }catch (Exception e){System.out.println("Erro na inicialização dos fantasmas: "+e);}
        
        tempSS = ss;
        
        ssFrightened = new SpriteSheet(i.load("/img/gFrightened.png"));
        ssDead = new SpriteSheet(i.load("/img/gEyes.png"));
        
        this.velX = -speed;        
    }
    
    public void reset(){
        
        ss = tempSS;
        
        this.velX = 0;
        this.velY = 0;
        
        this.x = x0;
        this.y = y0;
        
        this.setState(0);
        this.startFrame = 4;
        this.endFrame = startFrame + 1;
        this.frameNumber = startFrame;
        this.frameSpeed = 4; //quanto maior, mais lento
        
        this.velX = -speed;
        
        
    }
    
    @Override
    public void update(){

        try{
            this.x += velX;
            this.y += velY;


            //passagem pelo túnel
            if(this.x < -35 && y > 290 && this.y < 310)
                this.x = 494;        
            if(this.x > 510 && y > 290 && this.y < 310)
                this.x = -19;

            this.Animation();
            
        } catch(NullPointerException s) {
            //s.printStackTrace();
            throw new IllegalStateException("Some Ghost has a null property", s);
        }
        
    }
    
    public void setState(int k){
        //int lastState = state;
        state = k;
        switch(state){
            default: System.out.println("Erro em setState() da Ghost.class");break;
            case 0:
                //scatter
                ss = tempSS;
                speed = 3;
                break;
            case 1:
                //chase
                ss = tempSS;
                speed = 3;
                break;
            case 2:
                //frightened
                frightCounter = 50;
                
                counterSS = 0;
                startFrame = 0;
                endFrame = startFrame + 1;
                frameNumber = startFrame;

                ss = ssFrightened;
                
                this.speed = 1;
                break;
            case 3:
                // dead/eated
                counterSS = 0;
                startFrame = 0;
                endFrame = startFrame + 1;
                frameNumber = startFrame;
                
                ss = ssDead;
                speed = 6;
                break;
        }
        //if(lastState == 2 && state != 2)
        //    speed++;
    }
    
    public void chaseBlinky(Entity e, Ghost b){
        
        Gate gate = this.gatePos;
        
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
        
        //Calcula a distância dos gates proximas em relação ao target
        int[] vX = new int[4];
        vX[0] = gate.x;
        vX[1] = gate.x+10;
        vX[2] = gate.x;
        vX[3] = gate.x-10;
        
        int[] vY = new int[4];
        vY[0] = gate.y - 10;
        vY[1] = gate.y;
        vY[2] = gate.y + 10;
        vY[3] = gate.y;
        
        double[] h = new double[4];
        double[] hCopy = new double[4];
        for(int i = 0; i < h.length; i++){
            h[i] = Math.sqrt((vX[i]-e.x)*(vX[i]-e.x)+(vY[i]-e.y)*(vY[i]-e.y));            
            hCopy[i] = h[i];
        }       
        
        Arrays.sort(h);
        
        int[] sortH = new int[4]; //vetor para escolhar o gate mais curto;
        
        for(int i = 0; i < h.length; i++){
            for(int j = 0; j < hCopy.length;j++){
                if(h[i] == hCopy[j]){
                    sortH[3-i] = j;
                }
            }
        }
        
        

        for(int i=0; i < sortH.length;i++){
            if(g[sortH[i]] == true){
                setVel(sortH[i]);
            }
        }
    }
    
    public void chaseClyde(Entity e, Ghost b){
        
        Gate gate = this.gatePos;
        
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
        
        //Calcula a distância dos gates proximas em relação ao target
        int[] vX = new int[4];
        vX[0] = gate.x;
        vX[1] = gate.x+10;
        vX[2] = gate.x;
        vX[3] = gate.x-10;
        
        int[] vY = new int[4];
        vY[0] = gate.y - 10;
        vY[1] = gate.y;
        vY[2] = gate.y + 10;
        vY[3] = gate.y;
        
        double d = 0;
        d = Math.sqrt((this.x-e.x)*(this.x-e.x)+(this.y-e.y)*(this.y-e.y));
        
        double[] h = new double[4];
        double[] hCopy = new double[4];
        for(int i = 0; i < h.length; i++){
            if(d > 144){
                h[i] = Math.sqrt((vX[i]-e.x)*(vX[i]-e.x)+
                        (vY[i]-e.y)*(vY[i]-e.y));
            } else {
                h[i] = Math.sqrt((vX[i]-target.x)*(vX[i]-target.x)+
                    (vY[i]-target.y)*(vY[i]-target.y));
            }            
            hCopy[i] = h[i];
        }       
        
        Arrays.sort(h);
        
        int[] sortH = new int[4]; //vetor para escolhar o gate mais curto;
        
        for(int i = 0; i < h.length; i++){
            for(int j = 0; j < hCopy.length;j++){
                if(h[i] == hCopy[j]){
                    sortH[3-i] = j;
                }
            }
        }
        
        

        for(int i=0; i < sortH.length;i++){
            if(g[sortH[i]] == true){
                setVel(sortH[i]);
            }
        }
    }
    
    public void chasePinky(Entity e, Ghost b){
        
        Gate gate = this.gatePos;
        
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
        
        //Calcula a distância dos gates proximas em relação ao target
        int[] vX = new int[4];
        vX[0] = gate.x;
        vX[1] = gate.x+10;
        vX[2] = gate.x;
        vX[3] = gate.x-10;
        
        int[] vY = new int[4];
        vY[0] = gate.y - 10;
        vY[1] = gate.y;
        vY[2] = gate.y + 10;
        vY[3] = gate.y;
        
        int[] pDir = new int[2];
        pDir[0]=0;//eixo x
        pDir[1]=0;//eixo y
        
        if(e.x > 0){
            pDir[0] = 72; //4 tiles de 18x18 à frente do pacman;
        } else if(e.velX < 0) {
            pDir[0] = -72;
        }
        if(e.velY > 0){
            pDir[1] = 72; //4 tiles de 18x18 à frente do pacman;
        } else if(e.velY < 0){
            pDir[1] = -72;
        }
        
        switch(e.direction){
            default:
                System.out.println("Erro no chasePinky");
                break;
            case 0: //up
                pDir[0] = 0;
                pDir[1] = -72;
                break;
            case 1: //right
                pDir[0] = 72;
                pDir[1] = 0;
                break;
            case 2: //down
                pDir[0] = 0;
                pDir[1] = 72;
                break;
            case 3: //left
                pDir[0] = -72;
                pDir[1] = 0;
                break;
                
        }
        
        double[] h = new double[4];
        double[] hCopy = new double[4];
        for(int i = 0; i < h.length; i++){
            h[i] = Math.sqrt((vX[i]-e.x-pDir[0])*(vX[i]-e.x-pDir[0])+
                    (vY[i]-e.y-pDir[1])*(vY[i]-e.y-pDir[1]));
            
            hCopy[i] = h[i];
        }       
        
        Arrays.sort(h);
        
        int[] sortH = new int[4]; //vetor para escolhar o gate mais curto;
        
        for(int i = 0; i < h.length; i++){
            for(int j = 0; j < hCopy.length;j++){
                if(h[i] == hCopy[j]){
                    sortH[3-i] = j;
                }
            }
        }
        
        

        for(int i=0; i < sortH.length;i++){
            if(g[sortH[i]] == true){
                setVel(sortH[i]);
            }
        }
    }
    
    public void chaseInky(Entity e, Ghost b){
        
        Gate gate = this.gatePos;
        
        int bx = b.x;
        int by = b.y;
                
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
        
        //Calcula a distância dos gates proximas em relação ao target
        int[] vX = new int[4];
        vX[0] = gate.x;
        vX[1] = gate.x+10;
        vX[2] = gate.x;
        vX[3] = gate.x-10;
        
        int[] vY = new int[4];
        vY[0] = gate.y - 10;
        vY[1] = gate.y;
        vY[2] = gate.y + 10;
        vY[3] = gate.y;
        
        int[] pDir = new int[2];
        pDir[0]=0;//eixo x
        pDir[1]=0;//eixo y
        
        
        switch(e.direction){
            default:
                System.out.println("Erro no chaseInky");
                break;
            case 0: //up
                pDir[0] = 0;
                pDir[1] = -36;
                break;
            case 1: //right
                pDir[0] = 36;
                pDir[1] = 0;
                break;
            case 2: //down
                pDir[0] = 0;
                pDir[1] = 36;
                break;
            case 3: //left
                pDir[0] = -36;
                pDir[1] = 0;
                break;                
        }
        //calcula as distâncias absolutas entre pacman e Blinky
        
        int px, py;
        px = e.x + pDir[0];
        py = e.y + pDir[1];
        /*
        int dx, dy;
        dx = px - b.x;
        if(dx<0)
            dx *= -1;
        dy = py - b.y;
        if(dy<0)
            dy *= -1;
        */
        
        bx = bx + 2*(px-bx);
        by = by + 2*(py-by);
        
        double[] h = new double[4];
        double[] hCopy = new double[4];
        for(int i = 0; i < h.length; i++){
            h[i] = Math.sqrt((vX[i]-bx)*(vX[i]-bx)+
                    (vY[i]-by)*(vY[i]-by));
            
            hCopy[i] = h[i];
        }       
        
        Arrays.sort(h);
        
        int[] sortH = new int[4]; //vetor para escolhar o gate mais curto;
        
        for(int i = 0; i < h.length; i++){
            for(int j = 0; j < hCopy.length;j++){
                if(h[i] == hCopy[j]){
                    sortH[3-i] = j;
                }
            }
        }
        
        

        for(int i=0; i < sortH.length;i++){
            if(g[sortH[i]] == true){
                setVel(sortH[i]);
            }
        }
    }
    
    public void Scatter(){
        
        Gate gate = getGatePos();
        
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
                
        //Calcula a distância dos gates proximas em relação ao target
        int[] vX = new int[4];
        vX[0] = gate.x;
        vX[1] = gate.x+10;
        vX[2] = gate.x;
        vX[3] = gate.x-10;
        
        int[] vY = new int[4];
        vY[0] = gate.y - 10;
        vY[1] = gate.y;
        vY[2] = gate.y + 10;
        vY[3] = gate.y;
        
        double[] h = new double[4];
        double[] hCopy = new double[4];
        for(int i = 0; i < h.length; i++){            
            h[i] = Math.sqrt((vX[i]-target.x)*(vX[i]-target.x)+
                    (vY[i]-target.y)*(vY[i]-target.y));//fórmula para o Scatter
            hCopy[i] = h[i];
        }       
        
        Arrays.sort(h);
        
        int[] sortH = new int[4]; //vetor para escolhar o gate mais curto;
        
        for(int i = 0; i < h.length; i++){
            for(int j = 0; j < hCopy.length;j++){
                if(h[i] == hCopy[j]){
                    sortH[3-i] = j;
                }
            }            
        }        
        

        for(int i=0; i < sortH.length;i++){
            if(g[sortH[i]] == true){
                setVel(sortH[i]);
            }
        }
        
        

    }
    
    public void Frightened(){
        
        Gate gate = this.gatePos;
        
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
        
        frightCounter--;
        if(frightCounter == 15){
            endFrame = 3;
        }
        if(frightCounter == 0)
            this.setState(1);
    }
    
    public void Dead(){
        
        //temporário
        x0 = 240;
        y0 = 250;
        
        if(x == x0 && y == y0){
            this.setState(0);            
        }
        
        Gate gate = this.gatePos;
        
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
        
        //Calcula a distância dos gates proximas em relação ao target
        int[] vX = new int[4];
        vX[0] = gate.x;
        vX[1] = gate.x+10;
        vX[2] = gate.x;
        vX[3] = gate.x-10;
        
        int[] vY = new int[4];
        vY[0] = gate.y - 10;
        vY[1] = gate.y;
        vY[2] = gate.y + 10;
        vY[3] = gate.y;
        
        double[] h = new double[4];
        double[] hCopy = new double[4];
        for(int i = 0; i < h.length; i++){
            h[i] = Math.sqrt((vX[i]-x0)*(vX[i]-x0)+
                    (vY[i]-y0)*(vY[i]-y0));            
            hCopy[i] = h[i];
        }       
        
        Arrays.sort(h);
        
        int[] sortH = new int[4]; //vetor para escolhar o gate mais curto;
        
        for(int i = 0; i < h.length; i++){
            for(int j = 0; j < hCopy.length;j++){
                if(h[i] == hCopy[j]){
                    sortH[3-i] = j;
                }
            }
        }

        for(int i=0; i < sortH.length;i++){
            if(g[sortH[i]] == true){
                setVel(sortH[i]);
            }
        }
                
        if(x >= 220 && x <= 260 && y >= 230 && y <= 250){
            this.setState(0);            
        }
    }
    
    public void aStar(Entity e, Gate gate){
        
        LinkedList<Gate> openList = new LinkedList<Gate>();
        LinkedList<Gate> closedList = new LinkedList<Gate>();
        
        boolean[] g = new boolean[4];
        g[0] = gate.up;
        g[1] = gate.right;
        g[2] = gate.down;
        g[3] = gate.left;
    }
    
    @Override
    public void collisionGate(Gate gate){}
    
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
        /*
        g.setColor(Color.yellow);
        g.drawRect( this.getCenterBounds().x,
                    this.getCenterBounds().y, 
                    this.getCenterBounds().width, 
                    this.getCenterBounds().height);
        */
    }

    @Override
    public void changeDirection(){
        if(state == 2){
            startFrame = 0;
            endFrame = startFrame + 1;
            frameNumber = startFrame;    
        } else if(state == 3) {
            if(velX > 0){
                startFrame = 0;
                endFrame = startFrame;
                frameNumber = startFrame;    
            }
            if(velX < 0){
                startFrame = 2;
                endFrame = startFrame;
                frameNumber = startFrame;    
            }
            if(velY > 0){
                startFrame = 1;
                endFrame = startFrame;
                frameNumber = startFrame;    
            }
            if(velY < 0){
                startFrame = 3;
                endFrame = startFrame;
                frameNumber = startFrame;    
            }
        } else {    
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

}
