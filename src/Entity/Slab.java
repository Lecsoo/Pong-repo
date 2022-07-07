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
    private Image image;// = Toolkit.getDefaultToolkit().getImage("Green.png");

    public Slab(int x, int y, String image) {
        this.x = x;
        this.y = y;
        this.image = Toolkit.getDefaultToolkit().getImage(image);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }


}
