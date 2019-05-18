package mapGen.domain;
import java.awt.*;

public class Tile {
  private Edge  edge;
  private int   x;
  private int   y;
  public  Image tileImage;
  public Tile(Edge e, Image i){
    this.edge = e;
    this.tileImage = i;
  }

  public void setPos(int x, int y){
    this.x=x;
    this.y=y;
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
}
