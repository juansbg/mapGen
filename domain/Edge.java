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
  public Edge(int n, int ne, int e, int se, int s, int sw, int w, int nw){
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
}
