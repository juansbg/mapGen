package mapGen.ui;
import java.awt.*;
import javax.swing.*;
import mapGen.domain.*;


public class JMap extends JFrame {
  MapPanel mp = new MapPanel();
  public JMap(){
    this.configurarJFrame();
    this.add(mp);
    this.setVisible(true);
  }
  public static void main(String[] args) {
    new JMap();
  }

  private void configurarJFrame(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Map Gen v0.1");
    this.setSize(960,640);
    //this.setMinimumSize(new Dimension(800,550));
    this.setLocationRelativeTo(null);
    //this.setResizable(false);
  }
}
