public class Item {
  private boolean armor;
  private int stat;

  public Item(boolean a, int s) {
    armor = a;
    stat = s;
  }

  public int getStat() {
    return stat;
  }

  public boolean getArmor() {
    return armor;
  }
}
