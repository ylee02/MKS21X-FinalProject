public class Player extends Entity implements Alive {
  private int luck;
  private int cons;
  private int level;
  private int health;
  private int strength;

  public Player() {
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

  public void die(){
  }

  public int getHealth(){
    return health;
  }

  public int getStrength(){
    return strength;
  }

  public void setHealth(int health){
    this.health = health;
  }
  
  public void setStrength(int strength){
    this.strength = strength;
  }
}
