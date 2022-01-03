import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
* [SpeedPotion.java]
* an item that boosts the player's speed for a small period of time
* contains overridden draw method
* @author Derek Chen
*/
class SpeedPotion extends Consumable {
  
  private int duration;
  private long buffTime;
  private boolean buffLock;
  private BufferedImage sprite;
  
  /**
  * SpeedPotion constructor 
  * sets hitbox
  * @param xPosition, the x coordinate of the speed potion
  * @param yPosition, the y coordinate of the speed potion
  * @param duration, the duration of the boost
  */
  SpeedPotion(int xPosition, int yPosition, int duration){
    super(xPosition, yPosition);
    this.duration = duration;
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
      sprite = ImageIO.read(new File("speedPotion.png"));
    } catch(Exception e) { System.out.println("Error Loading 'speedPotion.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxPosition(), getyPosition(), null);
  }  
  
  /**
   * setDuration
   * @param duration, the duration of the boost
   */ 
  public void setDuration(int duration){
    this.duration = duration;
  }
  
  /**
   * getDuration
   * @return int, the duration of the boost
   */ 
  public int getDuration(){
    return duration;
  }
  
  /**
   * setBuffTime
   * @param buffTime, the instant in time the boost was activated
   */ 
  public void setBuffTime(long buffTime){
    this.buffTime = buffTime;
  }
  
  /**
   * getBuffTime
   * @return long, the instant in time the boost was activated
   */ 
  public long getBuffTime(){
    return buffTime;
  }
}