package Logic;

import Entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Game {

    public static final int SCREEN_HEIGHT = 720;
    public static final int SCREEN_WIDTH = 1280;
    /*
    public static final int SCREEN_HEIGHT = 1080;
    public static final int SCREEN_WIDTH = 1920;
*/
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
        long startTime = System.currentTimeMillis();

        frame = new JFrame("Extreme Super Amazing Rogue Pong Plus Remastered");
        da = new DrawArea();

        Slab gp = da.getGp();
        Slab pp = da.getPp();
        Ball ball = da.getBall();

        frame.addKeyListener(new KeyChecker(da));
        da.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        JLabel php = new JLabel(String.valueOf(pp.getHp()));
        php.setForeground(Color.MAGENTA);
        php.setFont(new Font("Monospaced",Font.BOLD , SCREEN_HEIGHT/30));
        Dimension size = php.getPreferredSize();
        php.setBounds(SCREEN_WIDTH/2-size.width/2, SCREEN_HEIGHT/7, size.width, size.height);

        JLabel ghp = new JLabel(String.valueOf(gp.getHp()));
        ghp.setForeground(Color.GREEN);
        ghp.setFont(new Font("Monospaced",Font.BOLD , SCREEN_HEIGHT/30));
        ghp.setBounds(SCREEN_WIDTH/2-size.width/2, SCREEN_HEIGHT/10, size.width, size.height);

        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;

        JLabel time = new JLabel(String.valueOf(elapsedMinutes) + ":" + String.valueOf(secondsDisplay));
        time.setForeground(Color.WHITE);
        time.setFont(new Font("Monospaced",Font.BOLD , SCREEN_HEIGHT/30));
        Dimension tsize = time.getPreferredSize();
        time.setBounds(SCREEN_WIDTH/2-tsize.width/2, SCREEN_HEIGHT/18, tsize.width*2, tsize.height);

        da.setLayout(null);
        da.add(ghp);
        da.add(php);
        da.add(time);

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
                gp.move();
                pp.move();
                ball.move();
                long elapsedTime = System.currentTimeMillis() - startTime;
                long elapsedSeconds = elapsedTime / 1000;
                long secondsDisplay = elapsedSeconds % 60;
                long elapsedMinutes = elapsedSeconds / 60;

                time.setText(String.valueOf(elapsedMinutes) + ":" + String.valueOf(secondsDisplay));



                if(gp.getX() - ball.getX() <= BALL_WIDTH && gp.getX() - ball.getX() >= BALL_WIDTH/1.5 && ball.getY() > gp.getY()-BALL_WIDTH/2 && ball.getY() < gp.getY()+BALL_WIDTH/2+SLAB_HEIGHT)
                {
                    ball.invertX();
                }
                else if( ball.getX() - (pp.getX() + SLAB_WIDTH) <= 0 && ball.getX() - (pp.getX() + SLAB_WIDTH) >= BALL_WIDTH/-0.5 && ball.getY() > pp.getY()-BALL_WIDTH/2 && ball.getY() < pp.getY()+BALL_WIDTH/2+SLAB_HEIGHT)
                {
                    ball.invertX();
                }

                if(ball.getX()<0) ball.setX(0);
                else if(ball.getX() > Game.SCREEN_WIDTH - BALL_WIDTH) ball.setX(Game.SCREEN_WIDTH - BALL_WIDTH);
                if (ball.getX() == 0 || ball.getX() == Game.SCREEN_WIDTH-BALL_WIDTH) {
                    ball.invertX();
                    if (ball.getX() == 0) {
                        pp.hurt(1);
                        php.setText(String.valueOf(pp.getHp()));
                    }
                    else {
                        gp.hurt(1);
                        ghp.setText(String.valueOf(gp.getHp()));

                    }
                }

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
