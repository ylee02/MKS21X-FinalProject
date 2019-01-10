public class Drop extends Entity {
  private Item newItem;
  public Drop(int nx, int ny, Item i) {
    x = nx;
    y = ny;
    newItem = i;
  }
}
