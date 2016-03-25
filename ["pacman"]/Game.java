/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import javax.swing.JFrame;

public class Game {
    
    public static final int WIDTH = 508;
    public static final int HEIGHT = 684;
    
    //usar tiles de 18 x 18

    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.pack();        
        f.setSize(WIDTH, HEIGHT);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GamePanel());
        f.setVisible(true);
        f.setTitle("PacMan");
        f.setBackground(Color.black);

    }
}
