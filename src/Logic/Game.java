package Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Game {
    public static final int SCREEN_HEIGHT = 720;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SLAB_WIDTH = SCREEN_WIDTH/42;
    public static final int SLAB_HEIGHT = SCREEN_HEIGHT/5;
    public static final int BALL_WIDTH = SCREEN_WIDTH/30;

    private JFrame frame;
    private DrawArea da;
    private Timer timer;

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
        da.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        frame.getContentPane().add(BorderLayout.CENTER, da);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.repaint();

        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                da.getGp().move();
                da.getPp().move();
                da.getBall().move();
                frame.repaint();
            }
        }
        );
        timer.start();
        frame.pack();
        frame.setVisible(true);
    }

    //frame = new JFrame();
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
