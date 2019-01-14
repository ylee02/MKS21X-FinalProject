import java.util.Random;
public class Togue {
  private Player player;
  private int floor;
  
  public Togue() {
	  player = new Player();
	  floor = 1;
  }
  
  public int getFloor() {
	  return floor;
  }
  
  public Player getPlayer() {
	  return player;
  }



  public void getLoot(boolean chest) {
	Item i;
	Random ran = new Random();
    if (chest) {
      i = new Item(ran.nextBoolean(), Math.abs(ran.nextInt()%(floor * 4));
    }
    else {
      i = new Item(ran.nextBoolean(), Math.abs(ran.nextInt()%(floor * 2));
    }
    dropLoot(i);

  }


}
