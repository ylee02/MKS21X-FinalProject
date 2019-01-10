public class Box extends Entity {
  private Drop drop;
  public Box(int nx, int ny, Drop newDrop){
    x = nx;
    y = ny;
    drop = newDrop;
  }
  
}
