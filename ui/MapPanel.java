package mapGen.ui;
import java.awt.*;
import javax.swing.*;
import mapGen.domain.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
public class MapPanel extends JPanel {
  public static int NX = 100;
  public static int NY = 100;
  public static boolean moreWater = true;
  public static boolean moreGrass = true;
  private static final long serialVersionUID = 110999;
  public static ArrayList<Edge> accEdges = new ArrayList<Edge>();
  Random rand = new Random();
  Tile tiles[][] = new Tile[NX][NY];
  public MapPanel(){
    this.setupAccEdges();
    this.createTiles();
    this.setBackground(Color.black);
    this.repaint();
  }
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.paintTiles(g);
  }
  private void paintTiles(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    for(int i=0;i<NX;i++)
      for(int j=0;j<NY;j++){
        Tile t = tiles[i][j];
        g2d.drawImage(t.getImage(),32*t.getX()+mapGen.domain.Tile.changeX, 32*t.getY()+mapGen.domain.Tile.changeY, this);
      }
  }
  private void createTiles() {
    for(int i=0;i<NX;i++)
      for(int j=0;j<NY;j++){
        tiles[i][j] = new Tile(this.selectEdge(i,j));
        tiles[i][j].setX(i);
        tiles[i][j].setY(j);
      }
  }
  private Edge selectEdge(int i, int j) {
    //System.out.print(i);
    //System.out.println(j);
    Edge e = new Edge(1,1,1,1,1,1,1,1);
    if(i!=0)
      if(tiles[i-1][j]!=null)
        e.setWest(tiles[i-1][j].getEdge().getEast());
    if(j!=0)
      if(tiles[i][j-1]!=null)
        e.setNorth(tiles[i][j-1].getEdge().getSouth());
    boolean keep = true;
    int cnt = 0;
    do{
      Edge pos = accEdges.get(rand.nextInt(accEdges.size()));
      if((pos.getNorth()==e.getNorth())&&(pos.getWest()==e.getWest())){
        e = pos;
        keep = false;
      }
      if(cnt>=10000){
        e = new Edge(0,0,0,0,0,0,0,0);
        keep=false;
      }
      cnt++;
    }while(keep);
    return e;
  }
  private void setupAccEdges(){
    accEdges.add(new Edge(0,0,0,0,0,0,0,0));
    // water
    accEdges.add(new Edge(0,0,0,0,0,1,1,1));
    accEdges.add(new Edge(0,0,0,1,1,1,0,0));
    accEdges.add(new Edge(0,0,0,1,1,1,1,1));
    accEdges.add(new Edge(0,1,1,1,1,1,1,1));
    accEdges.add(new Edge(0,1,1,1,1,1,0,0));
    accEdges.add(new Edge(1,1,0,0,0,0,0,1));
    accEdges.add(new Edge(1,1,0,0,0,1,1,1));
    accEdges.add(new Edge(1,1,0,1,1,1,1,1));
    accEdges.add(new Edge(1,1,1,1,0,0,0,1));
    accEdges.add(new Edge(1,1,1,1,0,1,1,1));
    accEdges.add(new Edge(1,1,1,1,1,1,0,1));
    accEdges.add(new Edge(1,1,1,1,1,1,1,1));
    // sand
    accEdges.add(new Edge(0,0,0,2,2,2,0,0));
    accEdges.add(new Edge(0,0,0,2,2,2,2,2));
    accEdges.add(new Edge(0,2,2,2,0,0,0,0));
    accEdges.add(new Edge(0,2,2,2,2,2,0,0));
    accEdges.add(new Edge(0,2,2,2,2,2,2,2));
    accEdges.add(new Edge(0,0,0,0,0,2,2,2));
    accEdges.add(new Edge(2,2,0,0,0,0,0,2));
    accEdges.add(new Edge(2,2,0,0,0,2,2,2));
    accEdges.add(new Edge(2,2,0,2,2,2,2,2));
    accEdges.add(new Edge(2,2,2,2,0,0,0,2));
    accEdges.add(new Edge(2,2,2,2,0,2,2,2));
    accEdges.add(new Edge(2,2,2,2,2,2,0,2));
    accEdges.add(new Edge(2,2,2,2,2,2,2,2));
    // mutations
    accEdges.add(new Edge(0,1,1,2,2,2,2,2));
    accEdges.add(new Edge(1,1,1,2,2,2,2,2));
    accEdges.add(new Edge(1,1,0,2,2,2,2,2));
    accEdges.add(new Edge(0,2,2,2,2,2,1,1));
    accEdges.add(new Edge(1,2,2,2,2,2,1,1));
    accEdges.add(new Edge(2,2,2,2,2,2,1,2));
    accEdges.add(new Edge(2,2,1,1,1,2,2,2));
    accEdges.add(new Edge(1,2,2,2,2,2,2,2));
    accEdges.add(new Edge(2,2,2,2,1,1,1,2));
    accEdges.add(new Edge(2,2,1,2,2,2,2,2));
    accEdges.add(new Edge(2,2,2,2,1,2,2,2));
    accEdges.add(new Edge(1,1,1,2,2,2,1,1));
    accEdges.add(new Edge(1,1,1,1,1,2,2,2));
    accEdges.add(new Edge(1,2,2,2,1,1,1,1));
    accEdges.add(new Edge(2,2,1,1,1,1,1,2));
    accEdges.add(new Edge(1,2,2,2,2,2,0,1));
    accEdges.add(new Edge(0,0,0,2,1,1,1,2));
    accEdges.add(new Edge(2,2,0,2,1,1,1,2));
    accEdges.add(new Edge(1,1,1,2,0,0,0,2));
    accEdges.add(new Edge(0,2,2,2,1,1,1,2));
    accEdges.add(new Edge(1,1,1,2,0,2,2,2));
    accEdges.add(new Edge(1,1,1,2,2,2,0,2));
    accEdges.add(new Edge(2,2,1,1,0,2,2,2));
    accEdges.add(new Edge(2,2,0,1,1,2,2,2));
    accEdges.add(new Edge(1,2,2,2,0,0,0,1));
    accEdges.add(new Edge(2,2,1,1,0,0,0,2));
    accEdges.add(new Edge(0,0,1,1,1,1,1,2));
    accEdges.add(new Edge(0,1,1,1,1,1,1,2));
    accEdges.add(new Edge(1,2,2,2,0,0,0,2));
    accEdges.add(new Edge(1,1,0,0,0,0,0,2));
    accEdges.add(new Edge(0,0,0,2,1,1,0,0));
    accEdges.add(new Edge(0,0,0,0,0,1,1,2));
    accEdges.add(new Edge(0,1,1,2,0,0,0,0));
    accEdges.add(new Edge(0,0,0,2,2,2,1,1));
    accEdges.add(new Edge(0,2,2,2,1,1,0,0));
    accEdges.add(new Edge(0,2,1,2,2,2,0,0));
    accEdges.add(new Edge(0,2,2,2,1,2,0,0));
    accEdges.add(new Edge(1,2,0,0,0,2,2,2));
    accEdges.add(new Edge(2,2,0,0,0,2,1,0));
    accEdges.add(new Edge(2,2,0,0,0,1,1,2));
    accEdges.add(new Edge(0,0,0,1,1,2,2,2));
    accEdges.add(new Edge(0,0,0,2,1,2,2,2));
    accEdges.add(new Edge(1,1,0,0,0,2,2,2));
    accEdges.add(new Edge(1,2,2,2,2,2,0,1));
    accEdges.add(new Edge(0,2,2,2,2,2,1,1));
    accEdges.add(new Edge(2,2,1,1,0,2,2,2));
    accEdges.add(new Edge(2,2,2,2,0,1,1,2));
    accEdges.add(new Edge(2,2,2,2,1,1,0,2));
    accEdges.add(new Edge(0,1,1,2,2,2,2,2));
    accEdges.add(new Edge(1,1,0,2,2,2,2,2));
    accEdges.add(new Edge(2,2,0,1,1,2,2,2));
    accEdges.add(new Edge(0,2,1,1,1,2,0,0));
    accEdges.add(new Edge(1,2,0,0,0,2,1,1));
    accEdges.add(new Edge(1,2,0,0,0,2,1,1));
    accEdges.add(new Edge(0,2,1,1,1,2,2,2));
    accEdges.add(new Edge(1,2,2,2,0,2,1,1));
    accEdges.add(new Edge(2,2,1,1,1,2,0,2));
    accEdges.add(new Edge(1,0,0,2,1,1,1,1));
    accEdges.add(new Edge(1,1,0,2,1,1,1,1));
    accEdges.add(new Edge(1,2,0,0,1,1,1,1));
    accEdges.add(new Edge(0,2,1,1,1,1,1,0));
    accEdges.add(new Edge(1,1,1,1,1,2,0,0));
    accEdges.add(new Edge(1,1,1,1,1,1,0,2));
    accEdges.add(new Edge(1,2,0,1,1,1,1,1));
    accEdges.add(new Edge(0,2,1,1,1,1,1,1));
    accEdges.add(new Edge(1,1,1,1,1,0,0,2));
    accEdges.add(new Edge(1,1,1,2,0,1,1,1));
    accEdges.add(new Edge(1,1,1,0,0,2,1,1));
    accEdges.add(new Edge(1,1,1,1,1,2,0,1));
    accEdges.add(new Edge(1,1,1,2,0,0,1,1));
    accEdges.add(new Edge(1,1,1,1,0,2,1,1));

    accEdges.add(new Edge(0,0,0,0,0,1,1,0));
    accEdges.add(new Edge(0,0,0,0,0,0,1,1));
    accEdges.add(new Edge(0,0,0,1,1,0,0,0));
    accEdges.add(new Edge(0,0,0,0,1,1,0,0));
    accEdges.add(new Edge(0,0,1,1,0,0,0,0));
    accEdges.add(new Edge(1,0,0,0,0,0,0,1));
    accEdges.add(new Edge(0,1,1,0,0,0,0,0));
    accEdges.add(new Edge(1,1,0,0,0,0,0,0));
    accEdges.add(new Edge(0,0,0,0,0,2,1,1));
    accEdges.add(new Edge(0,0,0,1,1,2,0,0));
    accEdges.add(new Edge(1,2,0,0,0,0,0,1));
    accEdges.add(new Edge(0,2,1,1,0,0,0,0));
    accEdges.add(new Edge(1,2,2,2,1,1,0,1));
    accEdges.add(new Edge(2,2,1,1,0,1,1,2));
    accEdges.add(new Edge(0,0,2,2,1,0,0,0));
    accEdges.add(new Edge(0,0,0,0,1,2,2,0));
    accEdges.add(new Edge(0,0,0,0,2,2,1,0));
    accEdges.add(new Edge(2,0,0,0,0,0,1,2));
    accEdges.add(new Edge(0,1,1,2,2,2,1,1));
    accEdges.add(new Edge(1,1,0,1,1,2,2,2));
    accEdges.add(new Edge(1,2,2,0,0,0,0,0));
    accEdges.add(new Edge(0,0,1,2,2,0,0,0));
    accEdges.add(new Edge(1,0,0,0,0,0,2,2));
    accEdges.add(new Edge(2,2,1,0,0,0,0,0));
    accEdges.add(new Edge(0,2,2,2,2,2,1,0));
    accEdges.add(new Edge(1,2,2,2,2,2,0,0));
    accEdges.add(new Edge(2,2,0,0,1,2,2,2));
    accEdges.add(new Edge(2,2,2,2,1,0,0,2));
    accEdges.add(new Edge(1,0,0,2,2,2,2,2));
    accEdges.add(new Edge(0,0,1,2,2,2,2,2));
    accEdges.add(new Edge(2,2,1,0,0,2,2,2));
    accEdges.add(new Edge(2,2,2,2,0,0,1,2));

    if(moreWater)
      for(int i = 0;i<10;i++)
        accEdges.add(new Edge(1,1,1,1,1,1,1,1));
    if(moreGrass)
      for (int i = 0;i<15;i++)
        accEdges.add(new Edge(0,0,0,0,0,0,0,0));
  }
}
