package mapGen.domain;
import javax.swing.*;
import java.awt.event.*;

public class ButtonMap extends Tile {
  public static final int RELOAD = 1;
  public static final int SAVE = 2;
  public static final int LOAD = 3;
  public static final int TITLE = 4;
  int type;
  ImageIcon reloadB = new ImageIcon("mapGen/resources/reload_B.png");
  ImageIcon reloadG = new ImageIcon("mapGen/resources/reload_G.png");
  ImageIcon saveB   = new ImageIcon("mapGen/resources/save_B.png");
  ImageIcon saveG   = new ImageIcon("mapGen/resources/save_G.png");
  ImageIcon loadB   = new ImageIcon("mapGen/resources/load_B.png");
  ImageIcon loadG   = new ImageIcon("mapGen/resources/load_G.png");
  ImageIcon title   = new ImageIcon("mapGen/resources/title.png");

  public ButtonMap(int type){
    super(new Edge(3,3,3,3,3,3,3,3));
    this.type=type;
    if(type==RELOAD)
      this.setImage(reloadB);
    if(type==SAVE)
      this.setImage(saveB);
    if(type==LOAD)
      this.setImage(loadB);
    if(type==TITLE)
      this.setImage(title);
  }

  public boolean checkPositioning(int x, int y){
    boolean inPosition = false;
    if(this.getX()<=x && (this.getX()+34)>=x){
      if((this.getY()+34)<=y && (this.getY()+68)>=y){
        inPosition = true;
        if(type==RELOAD)
          this.setImage(reloadG);
        if(type==SAVE)
          this.setImage(saveG);
        if(type==LOAD)
          this.setImage(loadG);
      }
    } else {
      inPosition = false;
      if(type==RELOAD)
        this.setImage(reloadB);
      if(type==SAVE)
        this.setImage(saveB);
      if(type==LOAD)
        this.setImage(loadB);
    }
    return inPosition;
  }
}
