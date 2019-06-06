package mapGen.domain;
import javax.swing.*;
import java.awt.event.*;

public class NumButton extends ButtonMap {
  public static final int ONE = 5;
  public static final int TWO = 6;
  public static final int THREE = 7;
  public static final int FOUR = 8;
  public static final int FIVE = 9;
  static ImageIcon oneB   = new ImageIcon("mapGen/resources/one_B.png");
  static ImageIcon oneG   = new ImageIcon("mapGen/resources/one_G.png");
  static ImageIcon twoB   = new ImageIcon("mapGen/resources/two_B.png");
  static ImageIcon twoG   = new ImageIcon("mapGen/resources/two_G.png");
  static ImageIcon threeB   = new ImageIcon("mapGen/resources/three_B.png");
  static ImageIcon threeG   = new ImageIcon("mapGen/resources/three_G.png");
  static ImageIcon fourB   = new ImageIcon("mapGen/resources/four_B.png");
  static ImageIcon fourG   = new ImageIcon("mapGen/resources/four_G.png");
  static ImageIcon fiveB   = new ImageIcon("mapGen/resources/five_B.png");
  static ImageIcon fiveG   = new ImageIcon("mapGen/resources/five_G.png");

  public NumButton(int type){
    super(type);
    this.setEdge(new Edge(3,3,3,3,3,3,3,3));
    this.type=type;
    if(type==ONE)
      this.setImage(oneB);
    if(type==TWO)
      this.setImage(twoB);
    if(type==THREE)
      this.setImage(threeB);
    if(type==FOUR)
      this.setImage(fourB);
    if(type==FIVE)
      this.setImage(fiveB);
  }
  @Override
  public void turnGray(){
    if(type==ONE)
      this.setImage(oneG);
    if(type==TWO)
      this.setImage(twoG);
    if(type==THREE)
      this.setImage(threeG);
    if(type==FOUR)
      this.setImage(fourG);
    if(type==FIVE)
      this.setImage(fiveG);
  }
  @Override
  public void turnBlack(){
    if(type==ONE)
      this.setImage(oneB);
    if(type==TWO)
      this.setImage(twoB);
    if(type==THREE)
      this.setImage(threeB);
    if(type==FOUR)
      this.setImage(fourB);
    if(type==FIVE)
      this.setImage(fiveB);
  }
}
