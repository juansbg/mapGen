package mapGen.domain;
import javax.swing.*;
import java.awt.event.*;

public class InfoButton extends ButtonMap {
  public static final int BUILD = 10;
  public static final int SIZE  = 11;
  public static final int FPS   = 12;
  static ImageIcon infoBuild = new ImageIcon("mapGen/resources/infoBuild.png");
  static ImageIcon infoSize  = new ImageIcon("mapGen/resources/infoSize.png");
  static ImageIcon infoFPS   = new ImageIcon("mapGen/resources/infoFPS.png");
  public InfoButton(int type){
    super(type);
    this.setEdge(new Edge(3,3,3,3,3,3,3,3));
    this.type=type;
    if(type==BUILD)
      this.setImage(infoBuild);
    if(type==SIZE)
      this.setImage(infoSize);
    if(type==FPS)
      this.setImage(infoFPS);
  }
}
