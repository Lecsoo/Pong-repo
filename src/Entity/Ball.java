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
    private double dir = 0;
    private int speed = 0;
    private double movex = 0;
    private double movey = 0;
    Random rand = new Random();

    public Ball() {
        x = Game.SCREEN_WIDTH/2 - width/2;
        y = Game.SCREEN_HEIGHT/2 - width/2;
        dir = 0;//rand.nextInt(360);
    }

    public void move() {
        movex = speed * Math.cos(Math.toRadians(dir));
        x += movex;
        /*
        if(x<0) x=0;
        else if(x>Game.SCREEN_WIDTH - width) x=Game.SCREEN_WIDTH - width;
        if (x == 0 || x == Game.SCREEN_WIDTH-width) dir = 180 - dir;*/

        movey = speed * Math.sin(Math.toRadians(dir));
        y += movey;
        if(y<0) y=0;
        else if(y>Game.SCREEN_HEIGHT - width) y=Game.SCREEN_HEIGHT - width;

        if (y == 0 || y == Game.SCREEN_HEIGHT-width) dir = 360 - dir;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, width, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void invertY() {
        dir = 360 - dir;
    }
    public void invertX() {
        dir = 180 - dir;
    }

    public void start() {
        speed = Game.SCREEN_HEIGHT/50;
    }

    public void setDir(double dir) {
        this.dir = dir;
    }
}
