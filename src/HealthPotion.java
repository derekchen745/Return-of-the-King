import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
* [HealthPotion.java]
* an item that returns a given amount of health to the player
* contains overridden draw method
* @author Derek Chen
*/
class HealthPotion extends Consumable {
  
  private int healthGain;
  private BufferedImage sprite;
  
  /**
  * HealthPotion constructor 
  * sets hitbox
  * @param xPosition, the x coordinate of the health pot
  * @param yPosition, the y coordinate of the health pot
  * @param healthGain, the amount of health returned
  */
  HealthPotion(int xPosition, int yPosition, int healthGain){
    super(xPosition, yPosition);
    this.healthGain = healthGain;
    loadSprites();
    setWidth(sprite.getWidth());
    setLength(sprite.getHeight());
    setHitbox(new Rectangle(xPosition, yPosition, getWidth(), getLength()));
  }
  
  /**
  * loadSprites
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
    try {
      sprite = ImageIO.read(new File("healthPot.png"));
    } catch(Exception e) { System.out.println("Error Loading 'bandage.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxPosition(), getyPosition(), null);
  }  
  
  /**
   * setHealthGain
   * Sets the amount of health the potion returns
   * @param healthGain, the amount of health the potion returns
   */ 
  public void setHealthGain(int healthGain){
    this.healthGain = healthGain;
  }
  
  /**
   * getHealthGain
   * Gets the amount of health the potion returns
   * @return int, the amount of health returned
   */ 
  public int getHealthGain(){
    return healthGain;
  }
}