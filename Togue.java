import java.util.Random;
public class Togue {
  private Player player;
  private static int floor;
  
  public Togue() {
	  player = new Player();
	  floor = 1;
  }
  
  public int getFloor() {
	  return floor;
  }
  
  public void setFloor(int f) {
	  floor = f;
  }
  
  public Player getPlayer() {
	  return player;
  }



  public static void getLoot(boolean chest, Monster m) {
	Item i;
	Random ran = new Random();
    if (chest) {
      i = new Item(ran.nextBoolean(), Math.abs(ran.nextInt()%(floor * 4)));
    }
    else {
      i = new Item(ran.nextBoolean(), Math.abs(ran.nextInt()%(floor * 2)));
    }
    m.dropLoot(i);

  }


}
