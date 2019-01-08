import java.util.Random;
public class Togue {
  private Player player;
  private int floor;

  public void getLoot() {
    Item i = new Item(Random.nextInt()%(floor * 2),  Random.nextInt()%(floor * 2))
    dropLoot();
  }


}
