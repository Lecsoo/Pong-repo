package Entity;

import Logic.Game;

import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Ball {
    private final int width = Game.BALL_WIDTH;
    private int x;
    private int y;
    private Image image = Toolkit.getDefaultToolkit().getImage("Yellow.png");
    private double xd = 0;
    private int yd = 0;
    private int speed = 8;
    private double movex = 0;
    private double movey = 0;
    Random rand = new Random();

    public Ball() {
        x = Game.SCREEN_WIDTH/2 + width/2;
        y = Game.SCREEN_HEIGHT/2 + width/2;
        xd = Math.PI;//rand.nextInt(360);
        /*xd = rand.nextInt(39) - 19;
        yd = rand.nextInt(39) - 19;*/
    }

    public void move() {
        //x += xd/2;
        movex = speed * Math.cos(xd);
        x += movex;
        if(x<0) x=0;
        else if(x>Game.SCREEN_WIDTH - width) x=Game.SCREEN_WIDTH - width;
        if (x == 0 || x == Game.SCREEN_WIDTH-width) xd = 180 - xd;

        //y += yd/2;
        movey = speed * Math.sin(xd);
        y += movey;
        if(y<0) y=0;
        else if(y>Game.SCREEN_HEIGHT - width) y=Game.SCREEN_HEIGHT - width;

        if (y == 0 || y == Game.SCREEN_HEIGHT-width) xd = 360 - xd;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, width, null);
    }
}
