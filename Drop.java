public class Drop extends Monster {
  private Item item;
  public Drop(int nx, int ny, Item newItem) {
    this.setX(nx);
    this.setY(ny);
    this.item = newItem;
  }
}
