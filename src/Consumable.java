
/**
 * [Consumable.java]
 * a consumable used by the player
 * superclass to HealthPotion, Speed Potion
 * @author Derek Chen
 */
abstract class Consumable extends Entity{
  
  /**
   * Consumable constructor
   * @param xPosition, the x position of the consumable
   * @param yPosition, the y position of the consumable
   */ 
  Consumable(int xPosition, int yPosition){
    super(xPosition, yPosition);
  }
}