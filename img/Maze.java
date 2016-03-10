
package img;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import objects.Dot;
import objects.DotController;


public class Maze {
    
    private ImageLoader i = new ImageLoader();
    private SpriteSheet ss = new SpriteSheet(i.load("maze_spritesheet2.png"));
    
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
    
    public void update(){

    }
    
    public void createObjects(DotController dc){
        
        for(int k=0; k < maze.size(); k++){

        tempLine = maze.get(k);

            for(int j = 0; j < 28; j++){

                if(tempLine.substring(2*j, 2*j+2).equals("dd")){
                    //g2d.drawImage(ss.crop(6, 0, 18, 18), j*18, (k+3)*18, null);
                    dc.addDot(new Dot(j*18, (k+3)*18));
                } else if(tempLine.substring(2*j, 2*j+2).equals("up")){
                    //g2d.drawImage(ss.crop(6, 1, 18, 18), j*18, (k+3)*18, null);
                } else if(tempLine.substring(2*j, 2*j+2).equals("xx")){
                    //g2d.drawImage(ss.crop(7, 0, 18, 18), j*18, (k+3)*18, null);
                } else {
                    //xSS = Integer.parseInt(tempLine.substring(2*j, 2*j+1));
                    //ySS = Integer.parseInt(tempLine.substring(2*j+1, 2*j+2));                    
                    //g2d.drawImage(ss.crop(xSS, ySS, 18, 18), j*18, (k+3)*18, null);
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
                    g2d.drawImage(ss.crop(6, 1, 18, 18), j*18, (k+3)*18, null); //6,1
                } else if(tempLine.substring(2*j, 2*j+2).equals("xx")){
                    g2d.drawImage(ss.crop(7, 0, 18, 18), j*18, (k+3)*18, null); //7,0
                } else {
                    xSS = Integer.parseInt(tempLine.substring(2*j, 2*j+1));
                    ySS = Integer.parseInt(tempLine.substring(2*j+1, 2*j+2));                    
                    g2d.drawImage(ss.crop(xSS, ySS, 18, 18), j*18, (k+3)*18, null);
                }

            }            
        }

    }
    
}
