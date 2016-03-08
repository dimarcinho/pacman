
package img;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Maze {
    
    private ImageLoader i = new ImageLoader();
    private SpriteSheet ss = new SpriteSheet(i.load("maze_spritesheet2.png"));
    
    public void Maze(){
        
    //BufferedReader txt = new BufferedReader(new FileReader("maze.txt"));    
    
    
    //String line;
    
        
    }
    
    public void update(){
        
    }
    
    public void draw(Graphics g2d){
        
        //rotina para desenhar o labirinto usando spritesheet
        BufferedReader br = null;        
        String tempLine;
        
        int xSS, ySS;
        int k = 3;
        
        try {
            br = new BufferedReader(new FileReader("C:/Users/Marcio/Documents/NetBeansProjects/PacMan/src/img/maze.txt"));

            tempLine = br.readLine();
            
            do  {

                for(int j = 0; j < 28; j++){
                    
                    if(tempLine.substring(2*j, 2*j+2).equals("dd")){
                        g2d.drawImage(ss.crop(6, 0, 18, 18), j*18, k*18, null); //6,0
                    } else if(tempLine.substring(2*j, 2*j+2).equals("up")){
                        g2d.drawImage(ss.crop(6, 1, 18, 18), j*18, k*18, null); //6,1
                    } else if(tempLine.substring(2*j, 2*j+2).equals("xx")){
                        g2d.drawImage(ss.crop(7, 0, 18, 18), j*18, k*18, null); //7,0
                    } else {
                        xSS = Integer.parseInt(tempLine.substring(2*j, 2*j+1));
                        ySS = Integer.parseInt(tempLine.substring(2*j+1, 2*j+2));                    
                        g2d.drawImage(ss.crop(xSS, ySS, 18, 18), j*18, k*18, null);
                    }                    
                    
                }
                //tempLine = "";
                k++; //descendo uma linha                

            } while ((tempLine = br.readLine()) != null && !tempLine.equals("") );

            } catch (IOException e) {
                    e.printStackTrace();
            } 
        finally {
            try {
                    if (br != null)br.close();
            } catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
    
}
