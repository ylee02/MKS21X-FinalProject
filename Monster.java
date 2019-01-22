public class Monster extends Entity implements Alive{
  private int type;
  private int gold;
  private int health;
  private int strength;
  private boolean chest = false;
  private boolean item = false;
  private char letter;
  private String name;

  public Monster(int type, int x, int y){
    this.type = type;
    this.setX(x);
    this.setY(y);
    setMStats();
  }

  public void setMStats() {
    if (type == 1) {
      setHealth(5);
      setStrength(1);
	  letter = 'G';
	  name = "Goblin";
    }
    if (type == 2) {
      setHealth(3);
      setStrength(3);
	  letter = 'S';
	  name = "Skeleton";
    }
    if (type == 3) {
      setHealth(10);
      setStrength(2);
	  letter = 'O';
	  name = "Ogre";
    }
    if (type == 4) {
      setHealth(1);
      setStrength(1);
	  letter = 'B';
	  name = "Blob";
    }
    if (type == 5) {
      setHealth(1);
      setStrength(1000);
	  letter = 'A';
	  name = "Assassin";
    }
    if (type == 6) {
      setHealth(1);
      setStrength(0);
      chest = true;
	  letter = 'C';
	  name = "Chest";
    }
	if (type == 7) {
		setHealth(1);
		setStrength(0);
		item = true;
		letter = 'I';
		name = "Item";
	}
	if (type == 8) {
		setHealth(400);
		setStrength(5);
		letter = 'D';
		name = "Dragon";
    }

  }
  
  public char getLetter() {
	  return letter;
  }
  
  public String getName() {
	  return name;
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
