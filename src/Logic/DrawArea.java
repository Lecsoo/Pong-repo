package Logic;

import Entity.Slab;

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

    public DrawArea() {
        setBackground(bg);
        gp = new Slab(Game.SCREEN_WIDTH/10*9, Game.SCREEN_HEIGHT/2 - Game.SLAB_HEIGHT/2, "Green.png");
        pp = new Slab(Game.SCREEN_WIDTH/10-Game.SLAB_WIDTH, Game.SCREEN_HEIGHT/2 - Game.SLAB_HEIGHT/2, "Pink.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        gp.draw(g2);
        pp.draw(g2);
    }


    void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'a') System.out.println("Pressed a");
        if(e.getKeyChar() == 'w') System.out.println("Pressed w");
        if(e.getKeyChar() == 's') System.out.println("Pressed s");
        if(e.getKeyChar() == 'd') System.out.println("Pressed d");
    }


    void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'a') System.out.println("Released a");
        if(e.getKeyChar() == 'w') System.out.println("Released w");
        if(e.getKeyChar() == 's') System.out.println("Released s");
        if(e.getKeyChar() == 'd') System.out.println("Released d");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
