import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
 * [Fireball.java]
 * a projectile that is used by the pistol and machinegun
 * subclass to Projectile
 * contains overridden travel and draw methods
 * @author Derek Chen
 */

class Fireball extends Projectile {
  private BufferedImage sprite;
  
  /**
   * Fireball constructor 
   * sets hitbox
   * @param xCoordinate, the x position of the fireball
   * @param yCoordinate, the y position of the fireball
   * @param speed, the speed the fireball travels
   */
  Fireball(int xPosition, int yPosition, double speed) {
    super(xPosition, yPosition, speed);
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
      sprite = ImageIO.read(new File("fireball.png"));
    } catch(Exception e) { System.out.println("Error Loading 'fireball.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxPosition(), getyPosition(), null);
  }
  
   @Override
  public void travel(Player waylon, int xCursor, int yCursor) {
    if (getLimit() == false){
      setAngle((double)Math.atan2(getyPosition() - waylon.getyCentre(), getxPosition() - waylon.getxCentre()));
      setxDirection(-(double)((getSpeed()) * Math.cos(getAngle())));
      setyDirection(-(double)((getSpeed()) * Math.sin(getAngle())));
      setLimit(true);
    } 
    getHitbox().setLocation((int)Math.round(getHitbox().getX()) + (int)Math.round(getxDirection()),(int)Math.round(getHitbox().getY()) + (int)Math.round(getyDirection()));
    setxPosition(getxPosition() + (int)Math.round(getxDirection()));
    setyPosition(getyPosition() + (int)Math.round(getyDirection()));
  }
}