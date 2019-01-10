import java.util.Random;
public class Togue {
  private Player player;
  private int floor;



  public void getLoot(boolean chest) {
    if (chest) {
      Item i = new Item(Random.nextBoolean(), Math.abs(Random.nextInt()%(floor * 4));
    }
    else {
      Item i = new Item(Random.nextBoolean(), Math.abs(Random.nextInt()%(floor * 2));
    }
    dropLoot(i);

  }


}
