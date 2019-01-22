public class Drop extends Monster {
  private Item item;
  public Drop(int nx, int ny, Item newItem) {
    super(7, nx, ny);
    this.item = newItem;
  }
}
