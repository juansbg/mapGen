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
  private static int offsetR = 0;
  private static int offsetS = 0;
  private static int offsetL = 0;
  private static int offset1 = 0;
  private static int offset2 = 0;
  private static int offset3 = 0;
  private static int offset4 = 0;
  private static int offset5 = 0;
  private static int offset1x = 0;
  private static int offset2x = 0;
  private static int offset3x = 0;
  private static int offset4x = 0;
  private static int offset5x = 0;
  public static boolean moreWater = true;
  public static boolean moreGrass = true;
  private boolean buttons = true;
  private boolean notOut = true;
  private static final long serialVersionUID = 110999;
  private static final int  time = 1;
  ButtonMap mbR = new ButtonMap(ButtonMap.RELOAD);
  ButtonMap mbS = new ButtonMap(ButtonMap.SAVE);
  ButtonMap mbL = new ButtonMap(ButtonMap.LOAD);
  ButtonMap mbT = new ButtonMap(ButtonMap.TITLE);
  NumButton nb1 = new NumButton(NumButton.ONE);
  NumButton nb2 = new NumButton(NumButton.TWO);
  NumButton nb3 = new NumButton(NumButton.THREE);
  NumButton nb4 = new NumButton(NumButton.FOUR);
  NumButton nb5 = new NumButton(NumButton.FIVE);
  public static ArrayList<Edge> accEdges = new ArrayList<Edge>();
  Random rand = new Random();
  Tile tiles[][] = new Tile[NX+9][NY];
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
    if(buttons){
      Tile t1 = tiles[NX][NY-1];
      Tile t2 = tiles[NX+1][NY-1];
      Tile t3 = tiles[NX+2][NY-1];
      Tile t4 = tiles[NX+3][NY-1];
      Tile t5 = tiles[NX+4][NY-1];
      Tile t6 = tiles[NX+5][NY-1];
      Tile t7 = tiles[NX+6][NY-1];
      Tile t8 = tiles[NX+7][NY-1];
      Tile t9 = tiles[NX+8][NY-1];
      mbR.setPos(this.getSize().width / 2 -60,this.getSize().height -50);
      mbS.setPos(this.getSize().width / 2 -20,this.getSize().height -50);
      mbL.setPos(this.getSize().width / 2 +20,this.getSize().height -50);
      mbT.setPos(this.getSize().width -150,20);
      nb1.setPos(this.getSize().width / 2 -20,this.getSize().height -50);
      nb2.setPos(this.getSize().width / 2 -20,this.getSize().height -50);
      nb3.setPos(this.getSize().width / 2 -20,this.getSize().height -50);
      nb4.setPos(this.getSize().width / 2 -20,this.getSize().height -50);
      nb5.setPos(this.getSize().width / 2 -20,this.getSize().height -50);
      g2d.drawImage(t1.getImage(),t1.getX(),t1.getY()+offsetR,this);
      g2d.drawImage(t3.getImage(),t3.getX(),t3.getY()+offsetL, this);
      g2d.drawImage(t4.getImage(),t4.getX(),t4.getY(), this);
      g2d.drawImage(t5.getImage(),t5.getX()+offset1x,t5.getY()+offsetS+offset1, this);
      g2d.drawImage(t6.getImage(),t6.getX()+offset2x,t6.getY()+offsetS+offset2, this);
      g2d.drawImage(t7.getImage(),t7.getX()+offset3x,t7.getY()+offsetS+offset3, this);
      g2d.drawImage(t8.getImage(),t8.getX()+offset4x,t8.getY()+offsetS+offset4, this);
      g2d.drawImage(t9.getImage(),t9.getX()+offset5x,t9.getY()+offsetS+offset5, this);
      g2d.drawImage(t2.getImage(),t2.getX(),t2.getY()+offsetS, this);
    }
  }
  private void createTiles() {
    for(int i=0;i<NX;i++)
      for(int j=0;j<NY;j++){
        tiles[i][j] = new Tile(this.selectEdge(i,j));
        tiles[i][j].setX(i);
        tiles[i][j].setY(j);
      }
    tiles[NX][NY-1]   = (Tile) mbR;
    tiles[NX+1][NY-1] = (Tile) mbS;
    tiles[NX+2][NY-1] = (Tile) mbL;
    tiles[NX+3][NY-1] = (Tile) mbT;
    tiles[NX+4][NY-1] = (Tile) nb1;
    tiles[NX+5][NY-1] = (Tile) nb2;
    tiles[NX+6][NY-1] = (Tile) nb3;
    tiles[NX+7][NY-1] = (Tile) nb4;
    tiles[NX+8][NY-1] = (Tile) nb5;
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
        e = new Edge(1,1,1,1,1,1,1,1);
        keep=false;
      }
      cnt++;
    }while(keep);
    return e;
  }
  public void check(int x, int y, boolean clicked){
    if(mbR.checkPositioning(x,y)){
      if(clicked)
        this.createTiles();
      this.repaint();
    }
    if(mbS.checkPositioning(x,y)){
      if(notOut)
        this.toggleSpread();
      this.repaint();
    } else if(!notOut){
      this.toggleSpreadBack();
    }
    if(mbL.checkPositioning(x,y))
      this.repaint();
  }
  public void toggleHide(){
    try{
        for(int i=0;i<25;i++){
          offsetR+=1;
          Thread.sleep(time);
          this.repaint();
        }
        for(int i=0;i<25;i++){
          offsetR+=1;
          offsetS+=1;
          Thread.sleep(time);
          this.repaint();
        }
        for(int i=0;i<25;i++){
          offsetS+=1;
          offsetL+=1;
          Thread.sleep(time);
          this.repaint();
        }
        for(int i=0;i<25;i++){
          offsetL+=1;
          Thread.sleep(time);
          this.repaint();
        }
    } catch (Exception e) {}
  }
  public void toggleShow(){
    try{
      for(int i=0;i<25;i++){
        offsetR+=-1;
        Thread.sleep(time);
        this.repaint();
      }
      for(int i=0;i<25;i++){
        offsetR+=-1;
        offsetS+=-1;
        Thread.sleep(time);
        this.repaint();
      }
      for(int i=0;i<25;i++){
        offsetS+=-1;
        offsetL+=-1;
        Thread.sleep(time);
        this.repaint();
      }
      for(int i=0;i<25;i++){
        offsetL+=-1;
        Thread.sleep(time);
        this.repaint();
      }
    }catch(Exception e) {}
  }
  public void toggleSpread(){
    notOut = false;
    try{
      for(int i=0;i<50;i++){
        offset1+=-1;
        offset2+=-1;
        offset3+=-1;
        offset4+=-1;
        offset5+=-1;
        Thread.sleep(time);
        this.repaint();
      }
      for(int i=0;i<12;i++){
        offset1x+=-4;
        offset2x+=-2;
        offset4x+=2;
        offset5x+=4;
        Thread.sleep(time);
        this.repaint();
      }
      for(int i=0;i<38;i++){
        offset1x+=-2;
        offset2x+=-1;
        offset4x+=1;
        offset5x+=2;
        Thread.sleep(time);
        this.repaint();
      }
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  public void toggleSpreadBack(){
    notOut = true;
    try{
      for(int i=0;i<38;i++){
        offset1x+=2;
        offset2x+=1;
        offset4x+=-1;
        offset5x+=-2;
        Thread.sleep(time);
        this.repaint();
      }
      for(int i=0;i<12;i++){
        offset1x+=4;
        offset2x+=2;
        offset4x+=-2;
        offset5x+=-4;
        Thread.sleep(time);
        this.repaint();
      }
      for(int i=0;i<50;i++){
        offset1+=1;
        offset2+=1;
        offset3+=1;
        offset4+=1;
        offset5+=1;
        Thread.sleep(time);
        this.repaint();
      }
    } catch(Exception e){
      e.printStackTrace();
    }
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
    // sandç
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
    accEdges.add(new Edge(0,2,1,1,1,1,1,2));
    accEdges.add(new Edge(1,1,1,1,1,2,0,2));
    accEdges.add(new Edge(1,1,1,2,0,2,1,1));
    accEdges.add(new Edge(1,2,0,2,1,1,1,1));
    accEdges.add(new Edge(0,0,2,2,2,2,2,2));
    accEdges.add(new Edge(0,2,2,2,2,2,2,0));
    accEdges.add(new Edge(2,0,0,2,2,2,2,2));
    accEdges.add(new Edge(2,2,2,2,0,0,0,2));
    accEdges.add(new Edge(2,2,2,2,2,2,0,0));
    accEdges.add(new Edge(0,0,1,1,1,1,1,1));
    accEdges.add(new Edge(1,0,0,1,1,1,1,1));
    accEdges.add(new Edge(1,1,0,0,1,1,1,1));
    accEdges.add(new Edge(1,1,1,0,0,1,1,1));
    accEdges.add(new Edge(1,0,0,0,0,0,0,2));
    accEdges.add(new Edge(1,2,0,0,0,0,0,0));
    accEdges.add(new Edge(1,2,0,0,0,0,0,2));
    accEdges.add(new Edge(0,0,1,1,1,1,1,0));
    accEdges.add(new Edge(0,0,1,1,1,1,1,1));
    accEdges.add(new Edge(0,1,1,1,1,1,1,0));
    accEdges.add(new Edge(0,2,2,2,2,2,2,0));
    accEdges.add(new Edge(0,0,2,2,2,2,2,0));
    accEdges.add(new Edge(0,0,2,2,2,2,2,2));
    accEdges.add(new Edge(1,0,0,0,0,0,0,0));
    if(moreWater)
      for(int i = 0;i<30;i++)
        accEdges.add(new Edge(1,1,1,1,1,1,1,1));
    if(moreGrass)
      for (int i = 0;i<25;i++)
        accEdges.add(new Edge(0,0,0,0,0,0,0,0));
  }
}
