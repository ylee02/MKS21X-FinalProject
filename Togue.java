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
  
  public void setFloor(int f) {
	  floor = f;
  }
  
  public Player getPlayer() {
	  return player;
  }



  public Item getLoot(boolean chest, Random ran) {
	Item i;
    if (chest) {
      i = new Item(ran.nextBoolean(), Math.abs(ran.nextInt()%(floor * 3)));
    }
    else {
      i = new Item(ran.nextBoolean(), Math.abs(ran.nextInt()%(floor * 2)));
    }
	return i;
  }


}
