package mapGen.domain;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Tile {
  private Edge  edge;
  private int   x;
  private int   y;
  public  Image tileImage;
  public Tile(Edge e){
    this.edge = e;
    tileImage = new ImageIcon(this.getPath()).getImage();
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
  public void setX(int x){
    this.x=x;
  }
  public void setY(int y){
    this.y=y;
  }
  private String getPath(){
    StringBuilder sb = new StringBuilder();
    sb.append("mapGen/resources/").append(edge.getFullEdge()).append(".png");
    System.out.println(sb.toString());
    return sb.toString();
  }
  public Image getImage(){
    return tileImage;
  }
  public Edge getEdge(){
    return edge;
  }
}
