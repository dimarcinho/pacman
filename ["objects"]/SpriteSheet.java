/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.image.BufferedImage;

public class SpriteSheet {
 
    private BufferedImage sheet;
    
    public SpriteSheet(BufferedImage img){
        this.sheet = img;
    }
    
    public BufferedImage crop(int col, int row, int w, int h){
        return sheet.getSubimage(18*col, 18*row, w, h);
    }
    public BufferedImage crop2(int col, int row, int w, int h){
        return sheet.getSubimage(col, row, w, h);
    }
}
