package mapGen.ui;
import java.awt.*;
import javax.swing.*;
import mapGen.domain.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JMap extends JFrame {
  MapPanel mp = new MapPanel();
  private static final long serialVersionUID = 100999;
  public JMap(){
    this.setLayout(new BorderLayout());
    this.configurarJFrame();
    this.add(mp,BorderLayout.CENTER);
    this.newAL();
    this.setVisible(true);
  }
  public static void main(String[] args) {
    new JMap();
  }

  private void configurarJFrame(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Map Gen v0.5");
    this.setSize(960,640);
    //this.setMinimumSize(new Dimension(800,550));
    this.setLocationRelativeTo(null);
    //this.setResizable(false);
  }

  private void newAL(){
    /*
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent evt) {
        JPanel c = (JPanel) evt.getSource();
        //new java.awt.FlowLayout(FlowLayout.CENTER, 0, c.getSize().height / 2 - bd.height / 2));
        mb.setX(JMap.this.getSize().width /2);
        mb.setY(JMap.this.getSize().height -20);
      }
    });*/
    this.addKeyListener(new KeyAdapter(){
      @Override
      public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
          mapGen.domain.Tile.movX = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
          mapGen.domain.Tile.movX = 0;
        }
        if (key == KeyEvent.VK_UP) {
          mapGen.domain.Tile.movY = 0;
          mapGen.domain.Tile.move();
          mp.repaint();
        }
        if (key == KeyEvent.VK_DOWN) {
          mapGen.domain.Tile.movY = 0;
          mapGen.domain.Tile.move();
          mp.repaint();
        }
      }
      @Override
      public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
          mapGen.domain.Tile.movX = 10;
          mapGen.domain.Tile.move();
          mp.repaint();
        }
        if (key == KeyEvent.VK_RIGHT) {
          mapGen.domain.Tile.movX = -10;
          mapGen.domain.Tile.move();
          mp.repaint();
        }
        if (key == KeyEvent.VK_UP) {
          mapGen.domain.Tile.movY = 10;
          mapGen.domain.Tile.move();
          mp.repaint();
        }
        if (key == KeyEvent.VK_DOWN) {
          mapGen.domain.Tile.movY = -10;
          mapGen.domain.Tile.move();
          mp.repaint();
        }
      }
    });
  }
}
