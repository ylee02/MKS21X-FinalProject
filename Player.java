public class Player extends Entity implements Alive {
  private int luck;
  private int cons;
  private int level;
  private int health;
  private int strength;
  private Item weapon;
  private Item armor;
  private int xp;

  public Player() {
    luck = 1;
    cons = 1;
    level = 1;
    strength = 2;
    health = 5;
    weapon = new Item(false, 1);
    armor = new Item(true, 1);
    xp = 0;
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

  public void setHealth(int newHealth){
    this.health = newHealth;
  }

  public void setStrength(int newStrength){
    this.strength = newStrength;
  }

  public void equipWeapon(Item i) {
    boolean ar = i.getArmor();
    if (ar) {
      setCons(getCons() - armor.getStat() + i.getStat());
      armor = i;
    }
    else {
      setStrength(getStrength() - weapon.getStat()- i.getStat());
      weapon = i;
    }
  }

}
