
package objects;

import java.awt.Image;
import pacman.GlobalPosition;

public class Ghost extends GlobalPosition {
    
    private ImageLoader i = new ImageLoader();
    private SpriteSheet ss;
    private int frame;
    int frameSpeed, frameNumber, startFrame, endFrame;
    private int counterSS = 0;
    private Image frameSS;
    
    public Ghost(int x, int y){
        super(x,y);
    }
}
