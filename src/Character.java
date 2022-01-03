/**[Character.java]
 * An abstract Character class from Entity that declares all living organisms
 * @author Derek Chen
 */
abstract class Character extends Entity {
  private int health, currentHealth, moveSpeed;
  private int xDirection, yDirection, direction; 
  private int xCentre, yCentre;

  /**
   * Character constructor
   * The constructor that inherits x coordinate,  y coordinate, health, damage, and speed
   * @param xPosition, an integer value that represents the x coordinate
   * @param yPosition, an integer value that represents the y coordinate
   * @param health, an integer value that represents the max/spawn health
   * @param moveSpeed, an integer value that represents the speed of the character
   */
  Character(int xPosition, int yPosition, int health, int moveSpeed) {
    super(xPosition, yPosition);
    this.xDirection = 0;
    this.yDirection = 0;
    this.health = health;
    this.moveSpeed = moveSpeed;
    currentHealth = health;
  }

  /**
   * setHealth
   * Sets the max health of the character
   * @param health, the max health of the character
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * getHealth
   * Gets the max health of the character
   * @return int, the characters max health
   */
  public int getHealth() {
    return this.health;
  }
  
  /**
   * setCurrentHealth
   * Sets the current health of the character
   * @param currentHealth, the current health of the character
   */ 
  public void setCurrentHealth(int currentHealth){
    this.currentHealth = currentHealth;
  }
  
  /**
   * getCurrentHealth
   * Gets the current health of the character
   * @return int, the current health of the character
   */ 
  public int getCurrentHealth(){
    return currentHealth;
  }

  /**
   * setMoveSpeed
   * Sets the character's movespeed
   * @param moveSpeed, the speed of the character
   */
  public void setMoveSpeed(int moveSpeed) {
    this.moveSpeed = moveSpeed;
  }

  /**
   * getMoveSpeed
   * Gets the character's movespeed
   * @return int, the speed of the character
   */
  public int getMoveSpeed() {
    return this.moveSpeed;
  }

  /**
   * getxDirection
   * Gets the horizontal direction of travel
   * @return int, -1 represents left, 0 stays, 1 towards the right
   */
  public int getxDirection() {
    return xDirection;
  }

  /**
   * getyDirection
   * Gets the vertical direction of travel
   * @return int, -1 represents up, 0 stays, 1 towards the bottom
   */
  public int getyDirection() {
    return yDirection;
  }

  /**
   * getxCentre
   * Gets the centre x coordinate of the character
   * @return int, the x centre value of the character
   */
  public int getxCentre() {
    return xCentre;
  }

  /**
   * getyCentre
   * Gets the centre y coordinate of the character
   * @return int, the y centre value of the character
   */
  public int getyCentre() {
    return yCentre;
  }

  /**
   * setxDirection
   * Sets the horizontal direction of travel
   * @param xDirection, an integer representing the x direction of travel
   */
  public void setxDirection(int xDirection) {
    this.xDirection = xDirection;
  }

  /**
   * setyDirection
   * Sets the vertical direction of travel
   * @param yDirection, an integer representing the x direction of travel
   */
  public void setyDirection(int yDirection) {
    this.yDirection = yDirection;
  }

  /**
   * setxCentre
   * Sets the centre x coordinate of the sprite
   * @param xCentre, an integer representing the x center coordinate
   */
  public void setxCentre(int xCentre) {
    this.xCentre = xCentre;
  }

  /**
   * setyCentre
   * Ssets the centre y coordinate of the sprite
   * @param yCentre,  an integer representing the y center coordinate
   */
  public void setyCentre(int yCentre) {
    this.yCentre = yCentre;
  }
}