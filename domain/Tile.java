package mapGen.domain;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;
public class Tile {
  private Edge  edge;
  private int   x;
  private int   y;
  private boolean city = false;
  private boolean feature = false;
  public static int changeX=-1000;
  public static int changeY=-1000;
  public static int movX = 0;
  public static int movY = 0;
  public  Image tileImage;
  Random rand = new Random();
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
  public Image getImage(){
    return tileImage;
  }
  public void setImage(ImageIcon im){
    this.tileImage = im.getImage();
  }
  public Edge getEdge(){
    return edge;
  }
  public static void move(){
    changeX+=movX;
    changeY+=movY;
  }

  private String getPath(){
    StringBuilder sb = new StringBuilder();
    sb.append("mapGen/resources/").append(edge.getFullEdge());
    if(edge.equals(new Edge(0,0,0,0,0,0,0,0)) || edge.equals(new Edge(1,1,1,1,1,1,1,1)) || edge.equals(new Edge(2,2,2,2,2,2,2,2))){
      if(rand.nextInt(20)==1){
        city = true;
        if(rand.nextBoolean())
          sb.append("CA");
        else
          sb.append("CB");
      } else if(rand.nextInt(20)==1) {
        feature = true;
        if(rand.nextBoolean())
          sb.append("F1");
        else
          sb.append("F2");
      }
    }
    sb.append(".png");
    //System.out.println(sb.toString());
    return sb.toString();
  }
}
