package mapGen.domain;
public class Edge {
  private int N;
  private int NE;
  private int E;
  private int SE;
  private int S;
  private int SW;
  private int W;
  private int NW;
  public Edge(int nw, int n, int ne, int e, int se, int s, int sw, int w){
    this.N  =n;
    this.NE =ne;
    this.E  =e;
    this.SE =se;
    this.S  =s;
    this.SW =sw;
    this.W  =w;
    this.NW =nw;
  }
  public int getNorth(){
    return 100*NE+10*N+NW;
  }
  public int getSouth(){
    return 100*SE+10*S+SW;
  }
  public int getEast(){
    return 100*NE+10*E+SE;
  }
  public int getWest(){
    return 100*NW+10*W+SW;
  }
  public void setNorth(int n){
    this.NW = n%10;
    this.N  = (n/10)%10;
    this.NE = (n/100);
  }
  public void setSouth(int n){
    this.SW = n%10;
    this.S  = (n/10)%10;
    this.SE = (n/100);
  }
  public void setEast(int n){
    this.SE = n%10;
    this.E  = (n/10)%10;
    this.NE = (n/100);
  }
  public void setWest(int n){
    this.SW = n%10;
    this.W  = (n/10)%10;
    this.NW = (n/100);
  }
  public String getFullEdge(){
    StringBuilder sb = new StringBuilder();
    sb.append(NW).append(N).append(NE).append(E).append(SE).append(S).append(SW).append(W);
    return sb.toString();
  }
}
