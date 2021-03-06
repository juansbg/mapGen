package mapGen.ui;
import java.awt.*;
import javax.swing.*;
import mapGen.domain.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class JMap extends JFrame {
  public MapPanel mp = new MapPanel();
  private boolean hidden = false;
  private static final long serialVersionUID = 100999;
  public JMap(){
    this.setLayout(new BorderLayout());
    this.configurarJFrame();
    this.add(mp,BorderLayout.CENTER);
    this.newAL();
    this.setVisible(true);
  }
  public static void main(String[] args) {
    JMap jmap = new JMap();
    while(true){
      jmap.check();
      jmap.repaint();
      try{
        Thread.sleep(1);
      } catch(Exception e){}
    }
  }

  private void configurarJFrame(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Map Gen v0.5");
    this.setMinimumSize(new Dimension(300,200));
    this.setSize(960,640);
    //this.setMinimumSize(new Dimension(800,550));
    this.setLocationRelativeTo(null);
    //this.setResizable(false);
  }

  public void check(){
    Point pmouse = MouseInfo.getPointerInfo().getLocation();
    Point pframe = this.getLocationOnScreen();
    mp.check(pmouse.x-pframe.x,pmouse.y-pframe.y,false);
    if(pmouse.y > pframe.y+this.getSize().height -80){
      if(hidden){
        mp.toggleShow();
        hidden = false;
      }
    } else {
      if(!hidden){
        mp.toggleHide();
        hidden = true;
      }
    }
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
    this.addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent e){
        Point pmouse = MouseInfo.getPointerInfo().getLocation();
        Point pframe = JMap.this.getLocationOnScreen();
        mp.check(pmouse.x-pframe.x,pmouse.y-pframe.y,true);
      }
    });

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
