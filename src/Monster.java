/**
 * [Monster.java]
 * Represents all monsters in the game
 * @author Derek Chen
 */ 
abstract class Monster extends Character{
  
  private int damage, cooldown;
  private boolean direction;
  
  /**
   * Monster constructor
   * sets hitbox
   * @param xPostion, the x coordinate of the monster
   * @param yPostion, the y coordinate of the monster
   * @param health, the max health of the monster
   * @param moveSpeed, the monster's travel speed
   * @param damage,  the monster's base damage
   */ 
  Monster(int xPosition, int yPosition, int health, int moveSpeed, int damage){
    super(xPosition, yPosition, health, moveSpeed);
    this.damage = damage;
    cooldown = 0;
  }
  
  /**
  * attack
  * An abstract method that allows to set waylon's health and cooldown
  * @param waylon, the player
  */
  abstract void attack(Player waylon);
  
  /**
   * setDamage
   * Sets the monster's damage value
   * @param damage, the monster's damage value
   */ 
  public void setDamage(int damage){
    this.damage = damage;
  }
  
  /**
   * getDamage
   * Gets the monster's damage value
   * @return int, the monster's damag value
   */ 
  public int getDamage(){
    return damage;
  }
  
   /**
   * setCooldown
   * Sets the monster's cooldown
   * @param damage, the monster's cooldown value
   */ 
  public void setCooldown(int cooldown){
    this.cooldown = cooldown;
  }
  
  /**
   * getCooldown
   * Gets the monster's cooldown
   * @return int, the monster's cooldown value
   */ 
  public int getCooldown(){
    return cooldown;
  }
  
   /**
   * setDirection
   * A method that sets the boolean value of direction
   * @param direction, a boolean value of direction
   */
  public void setDirection(boolean direction) {
    this.direction = direction;
  }

  /**
   * getDirection
   * A method that gets the boolean value of direction, right or left
   * @return true if direction of travel is right, false if direction of travel is left
   */
  public boolean getDirection() {
    return direction;
  }
}

