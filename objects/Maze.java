
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import objects.Dot;
import objects.DotController;
import objects.Gate;
import objects.GateController;
import objects.Pill;
import objects.PillController;



public class Maze {
    
    private ImageLoader i = new ImageLoader();
    private SpriteSheet ss = new SpriteSheet(i.load("../img/maze_spritesheet2.png"));
    
    private LinkedList <String> maze = new LinkedList<String>();
    private String tempLine;
        
    private int xSS, ySS;
    
    public Maze(){
                
        //Lê o arquivo e armazena os dados num vetor
        BufferedReader br = null;
        int k = 3; //deixa 3 tiles em branco para o "menu" do jogo
        
        try {
            
            br = new BufferedReader(new FileReader("C:/Users/Marcio/Documents/NetBeansProjects/PacMan/src/img/maze.txt"));

            tempLine = br.readLine();
            
            do  {
                
                maze.add(tempLine);
                
                k++; //descendo uma linha                

            } while ((tempLine = br.readLine()) != null && !tempLine.equals("") );
            
            br.close();

            } catch (IOException e) {
                    e.printStackTrace();
            } 
        finally {
            try {
                    if (br != null)
                        br.close();
            } catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
    
    public void update(){}
    
    public void createDots(DotController dc){
        
        for(int k=0; k < maze.size(); k++){

        tempLine = maze.get(k);

            for(int j = 0; j < 28; j++){

                if(tempLine.substring(2*j, 2*j+2).equals("dd")){                
                    dc.addDot(new Dot(j*18, (k+3)*18));
                }

            }            
        }    
    }
    
    public void createPills(PillController pc){
        
        for(int k=0; k < maze.size(); k++){

        tempLine = maze.get(k);

            for(int j = 0; j < 28; j++){

                if(tempLine.substring(2*j, 2*j+2).equals("up")){                
                    pc.addPill(new Pill(j*18, (k+3)*18));
                }

            }            
        }     
    }
    
    public void createGates(GateController gc){
        
        String lineAbove, lineBelow;
        
        for(int k=1; k < maze.size()-1; k++){

            lineAbove = maze.get(k-1);
            tempLine = maze.get(k);
            lineBelow = maze.get(k+1);

            for(int j = 1; j < 27; j++){

                if(tempLine.substring(2*j, 2*j+2).equals("dd") || 
                        tempLine.substring(2*j, 2*j+2).equals("xx") ||
                        tempLine.substring(2*j, 2*j+2).equals("up")){
                    
                    gc.addGate(new Gate(j*18, (k+3)*18));
                    
                    if(!lineAbove.substring(2*j,2*j+2).equals("dd") && 
                            !lineAbove.substring(2*j,2*j+2).equals("xx") && 
                            !lineAbove.substring(2*j, 2*j+2).equals("up")){
                                                
                        gc.gateList.getLast().setUp(false);
                    }
                    
                    if(!lineBelow.substring(2*j,2*j+2).equals("dd") && 
                            !lineBelow.substring(2*j,2*j+2).equals("xx") && 
                            !lineBelow.substring(2*j, 2*j+2).equals("up")){
                                                
                        gc.gateList.getLast().setDown(false);
                    }
                    
                    if(!tempLine.substring(2*(j+1), 2*(j+1)+2).equals("dd") && 
                        !tempLine.substring(2*(j+1), 2*(j+1)+2).equals("xx") &&
                        !tempLine.substring(2*(j+1), 2*(j+1)+2).equals("up")){
                        
                        gc.gateList.getLast().setRight(false);
                    }
                    
                    if(!tempLine.substring(2*(j-1), 2*(j-1)+2).equals("dd") && 
                        !tempLine.substring(2*(j-1), 2*(j-1)+2).equals("xx") &&
                        !tempLine.substring(2*(j-1), 2*(j-1)+2).equals("up")){
                        
                        gc.gateList.getLast().setLeft(false);
                    }
                    /*
                    System.out.println("   "+lineAbove.substring(2*j,2*j+2));
                    System.out.println(tempLine.substring(2*(j-1),2*(j-1)+2) + " "+tempLine.substring(2*j,2*j+2)+ " "+tempLine.substring(2*(j+1),2*(j+1)+2));
                    System.out.println("   "+lineBelow.substring(2*j,2*j+2));
                    System.out.println("");                            

                    System.out.println(gc.gateList.getLast().hashCode());
                    System.out.println("Up: "+gc.gateList.getLast().up);
                    System.out.println("Left: "+gc.gateList.getLast().left);
                    System.out.println("Right: "+gc.gateList.getLast().right);
                    System.out.println("Down: "+gc.gateList.getLast().down);
                    System.out.println("");
                     * 
                     */
                }
            }            
        }    
    }
    
    public void draw(Graphics g2d){

        for(int k=0; k < maze.size(); k++){
            
            tempLine = maze.get(k);
            
            for(int j = 0; j < 28; j++){
                                
                if(tempLine.substring(2*j, 2*j+2).equals("dd")){
                    //g2d.drawImage(ss.crop(6, 0, 18, 18), j*18, (k+3)*18, null); //6,0
                } else if(tempLine.substring(2*j, 2*j+2).equals("up")){
                    //g2d.drawImage(ss.crop(6, 1, 18, 18), j*18, (k+3)*18, null); //6,1
                } else if(tempLine.substring(2*j, 2*j+2).equals("xx")){
                    g2d.drawImage(ss.crop(7, 0, 18, 18), j*18, (k+3)*18, null); //7,0
                } else {
                    xSS = Integer.parseInt(tempLine.substring(2*j, 2*j+1));
                    ySS = Integer.parseInt(tempLine.substring(2*j+1, 2*j+2));                    
                    g2d.drawImage(ss.crop(xSS, ySS, 18, 18), j*18, (k+3)*18, null);
                    //g2d.setColor(Color.yellow);
                    //g2d.drawRect(j*18, (k+3)*18, 17, 17);
                }

            }            
        }

    }
    
}
