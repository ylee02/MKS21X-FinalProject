public class Monster extends Entity implements Alive{
  private String type;
  private int gold;
  private int health;
  private int strength;

  public monster(String type, int x, int y){
    this.type = type;
    setX(x);
    setY(y);
  }

  public monster(String type, int x, int y, int gold){
    this.type = type;
    setX(x);
    setY(y);
    this.gold = gold;
  }

  public void move(int newX, int newY){
    setX(getX() + newX);
    setY(getY() + newY);
  }

  public void dropLoot(){
  }

  public int getGold(){
    return gold;
  }

  public String getType(){
    return type;
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
