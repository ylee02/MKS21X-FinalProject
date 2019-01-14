public class Monster extends Entity implements Alive{
  private String type;
  private int gold;
  private int health;
  private int strength;
  private boolean chest;

  public Monster(String type, int x, int y){
    this.type = type;
    this.setX(x);
    this.setY(y);
    setMStats();
  }

  public void setMStats() {
    if (type.equals("Goblin")) {
      setHealth(5);
      setStrength(1);
    }
    if (type.equals("Skeleton")) {
      setHealth(3);
      setStrength(3);
    }
    if (type.equals("Ogre")) {
      setHealth(10);
      setStrength(2);
    }
    if (type.equals("Slime")) {
      setHealth(1);
      setStrength(1);
    }
    if (type.equals("Dragon")) {
      setHealth(40);
      setStrength(3);
    }
    if (type.equals("Assassin")) {
      setHealth(1);
      setStrength(1000);
    }
    if (type.equals("Chest")) {
      setHealth(1);
      setStrength(0);
      chest = true; 
    }

  }

  public void move(int newX, int newY){
    setX(getX() + newX);
    setY(getY() + newY);
  }

  public void dropLoot(Item i){
    Drop item = new Drop(getX(), getY(), i);

  }

  public int getGold(){
    return gold;
  }

  public String getType(){
    return type;
  }

  public void die(){
    Togue.getLoot(chest, this);
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
