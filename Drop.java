public class Drop extends Monster {
  private Item item;
  public Drop(int nx, int ny, Item newItem) {
    super("Item", nx, ny);
    this.item = newItem;
  }
}
