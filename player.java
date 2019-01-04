public class player extends entity implements alive {
  private int luck;
  private int cons;
  private int level;

  public player() {
    luck = 1;
    cons = 1;
    level = 1;
    strength = 1;
    health = 5;
  }

  public int getLuck() {
    return luck;
  }

  public int getCons() {
    return cons;
  }

  public int getLevel() {
    return level;
  }

  public void setLuck(int newLuck) {
    luck = newLuck;
  }

  public void setCons(int newCons) {
    cons = newCons;
  }

  public void levelUp() {
    level += 1;
  }
}
