package Logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game {
    public static final int SCREEN_HEIGHT = 720;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SLAB_WIDTH = SCREEN_WIDTH/42;
    public static final int SLAB_HEIGHT = SCREEN_HEIGHT/5;

    private JFrame frame;
    private DrawArea da;

    public Game() {
        runGame();
    }

    public void newGame() {
        frame.dispose();
        runGame();
    }

    private void runGame() {
        frame = new JFrame("Extreme Super Amazing Rogue Pong Plus Remastered");
        da = new DrawArea();
        frame.addKeyListener(new KeyChecker(da));
        da.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        frame.getContentPane().add(BorderLayout.CENTER, da);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.repaint();


    }

    //frame = new JFrame();
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
