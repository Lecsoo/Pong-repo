package Logic;

import Entity.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;


class DrawArea extends JPanel implements ActionListener {


    //private Image image = Toolkit.getDefaultToolkit().getImage("stairs.png");
    private Color bg = Color.BLACK;
    private Slab gp;
    private Slab pp;
    private Ball ball;

    public DrawArea() {
        setBackground(bg);
        gp = new Slab(Game.SCREEN_WIDTH/10*9, Game.SCREEN_HEIGHT/2 - Game.SLAB_HEIGHT/2, "Green.png");
        pp = new Slab(Game.SCREEN_WIDTH/10-Game.SLAB_WIDTH, Game.SCREEN_HEIGHT/2 - Game.SLAB_HEIGHT/2, "Pink.png");
        ball = new Ball();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        gp.draw(g2);
        pp.draw(g2);
        ball.draw(g2);
    }


    void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 87) pp.setDirection(1);
        if(e.getKeyCode() == 83) pp.setDirection(2);
        if(e.getKeyCode() == 38) gp.setDirection(1);
        if(e.getKeyCode() == 40) gp.setDirection(2);
    }


    void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 87 && pp.getDirection() == 1) pp.setDirection(0);
        if(e.getKeyCode() == 83 && pp.getDirection() == 2) pp.setDirection(0);
        if(e.getKeyCode() == 38 && gp.getDirection() == 1) gp.setDirection(0);
        if(e.getKeyCode() == 40 && gp.getDirection() == 2) gp.setDirection(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public Slab getGp() {
        return gp;
    }

    public Slab getPp() {
        return pp;
    }

    public Ball getBall() {
        return ball;
    }
}
