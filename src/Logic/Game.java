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

    //declarations
    private JFrame frame;
    private DrawArea da;
    private Timer timer;
    double dirChange;


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


                //collisions inside walls
                if(gp.getX() - ball.getX() <= BALL_WIDTH && gp.getX() - ball.getX() >= BALL_WIDTH-SLAB_WIDTH/2 && ball.getY() > gp.getY()-BALL_WIDTH && ball.getY() < gp.getY()/*+BALL_WIDTH*/+SLAB_HEIGHT)
                {
                    //ball.invertX();
                    dirChange = (double)(ball.getY() - gp.getY() + BALL_WIDTH*0.5) / (SLAB_HEIGHT);
                    if (dirChange <= 0.5) {
                        if (dirChange < 0) dirChange = 0;
                        ball.setDir(240 - dirChange*120 );
                    }
                    else {
                        if (dirChange > 1) dirChange = 1;
                        ball.setDir(180 - (dirChange-0.5)*120);
                    }
                    ball.setColor("green");
                }
                else if( ball.getX() - (pp.getX() + SLAB_WIDTH) <= 0 && ball.getX() - (pp.getX() + SLAB_WIDTH) >= -SLAB_WIDTH/2 && ball.getY() > pp.getY()-BALL_WIDTH && ball.getY() < pp.getY()/*+BALL_WIDTH*/+SLAB_HEIGHT)
                {
                    //ball.invertX();

                    dirChange = (double)(ball.getY() - pp.getY() + BALL_WIDTH*0.5) / (SLAB_HEIGHT);
                    if (dirChange <= 0.5) {
                        if (dirChange < 0) dirChange = 0;
                        ball.setDir(dirChange*120 + 300);
                    }
                    else {
                        if (dirChange > 1) dirChange = 1;
                        ball.setDir((dirChange-0.5)*120);
                    }
                    ball.setColor("pink");
                }
                //collisions outer walls
                else if(pp.getX() - ball.getX() <= BALL_WIDTH && pp.getX() - ball.getX() >= BALL_WIDTH-SLAB_WIDTH/2 && ball.getY() > pp.getY()-BALL_WIDTH && ball.getY() < pp.getY()/*+BALL_WIDTH*/+SLAB_HEIGHT)
                {
                    //ball.invertX();
                    dirChange = (double)(ball.getY() - pp.getY() + BALL_WIDTH*0.5) / (SLAB_HEIGHT);
                    if (dirChange <= 0.5) {
                        if (dirChange < 0) dirChange = 0;
                        ball.setDir(240 - dirChange*120 );
                    }
                    else {
                        if (dirChange > 1) dirChange = 1;
                        ball.setDir(180 - (dirChange-0.5)*120);
                    }
                    ball.setColor("pink");
                }
                else if( ball.getX() - (gp.getX() + SLAB_WIDTH) <= 0 && ball.getX() - (gp.getX() + SLAB_WIDTH) >= -SLAB_WIDTH/2 && ball.getY() > gp.getY()-BALL_WIDTH && ball.getY() < gp.getY()/*+BALL_WIDTH*/+SLAB_HEIGHT)
                {
                    //ball.invertX();

                    dirChange = (double)(ball.getY() - gp.getY() + BALL_WIDTH*0.5) / (SLAB_HEIGHT);
                    if (dirChange <= 0.5) {
                        if (dirChange < 0) dirChange = 0;
                        ball.setDir(dirChange*120 + 300);
                    }
                    else {
                        if (dirChange > 1) dirChange = 1;
                        ball.setDir((dirChange-0.5)*120);
                    }
                    ball.setColor("green");
                }
                //upper wall collisions
                else if(gp.getX() + SLAB_WIDTH - ball.getX() > 0 && gp.getX() - (ball.getX() + BALL_WIDTH) < 0
                        && ball.getY() + BALL_WIDTH < gp.getY()+BALL_WIDTH  && ball.getY() + BALL_WIDTH > gp.getY() )
                {
                    if(ball.getY()+BALL_WIDTH > gp.getY()) ball.setY(gp.getY()-BALL_WIDTH);
                    ball.setColor("green");
                }
                else if(pp.getX() + SLAB_WIDTH - ball.getX() > 0 && pp.getX() - (ball.getX() + BALL_WIDTH) < 0
                        && ball.getY() + BALL_WIDTH < pp.getY()+BALL_WIDTH  && ball.getY() + BALL_WIDTH > pp.getY() )
                {
                    if(ball.getY()+BALL_WIDTH > pp.getY()) ball.setY(pp.getY()-BALL_WIDTH);
                    ball.setColor("pink");
                }
                //bottom wall collisions
                else if(gp.getX() + SLAB_WIDTH - ball.getX() > 0 && gp.getX() - (ball.getX() + BALL_WIDTH) < 0
                        && ball.getY() > gp.getY()+ SLAB_HEIGHT - BALL_WIDTH  && ball.getY() < gp.getY() + SLAB_HEIGHT )
                {
                    if(ball.getY() < gp.getY()+SLAB_HEIGHT) ball.setY(gp.getY()+SLAB_HEIGHT);
                    ball.setColor("green");
                }
                else if(pp.getX() + SLAB_WIDTH - ball.getX() > 0 && pp.getX() - (ball.getX() + BALL_WIDTH) < 0
                        && ball.getY() > pp.getY()+ SLAB_HEIGHT - BALL_WIDTH  && ball.getY() < pp.getY() + SLAB_HEIGHT )
                {
                    if(ball.getY() < pp.getY()+SLAB_HEIGHT) ball.setY(pp.getY()+SLAB_HEIGHT);
                    ball.setColor("pink");
                }




                //hurt
                if(ball.getX()<0) ball.setX(0);
                else if(ball.getX() > Game.SCREEN_WIDTH - BALL_WIDTH) ball.setX(Game.SCREEN_WIDTH - BALL_WIDTH);
                if (ball.getX() == 0 || ball.getX() == Game.SCREEN_WIDTH-BALL_WIDTH) {
                    ball.invertX();
                    if (ball.getX() == 0) {
                        if (ball.getBc() != Ball.ballColor.pink)
                        pp.hurt(1);
                        php.setText(String.valueOf(pp.getHp()));
                    }
                    else {
                        if (ball.getBc() != Ball.ballColor.green)
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
