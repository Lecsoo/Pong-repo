package Entity;

import Logic.Game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Slab {
    private final int width = Game.SLAB_WIDTH;
    private final int height = Game.SLAB_HEIGHT;
    private int x = 0;
    private int y = 0;
    private Image image;
    private int direction = 0;

    public Slab(int x, int y, String image) {
        this.x = x;
        this.y = y;
        this.image = Toolkit.getDefaultToolkit().getImage(image);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void move() {
        switch (direction){
            case 1:
                y -= 12;
                if(y<0) y=0;
                break;
            case 2:
                y +=12;
                if(y>Game.SCREEN_HEIGHT - Game.SLAB_HEIGHT) y=Game.SCREEN_HEIGHT - Game.SLAB_HEIGHT;
                break;
            default:
                break;
        }
    }
}
