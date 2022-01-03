import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
 * [Bullet.java]
 * a projectile that used is by all weapons
 * subclass to Projectile
 * contains overridden travel and draw methods
 * @author Derek Chen
 **/

class Bullet extends Projectile {
  private BufferedImage sprite;
  
  /**
   * Bullet constructor 
   * sets hitbox
   * @param xCoordinate, the x position of the bullet
   * @param yCoordinate, the y position of the bullet
   * @param speed, the speed the bullet travels
   */
  Bullet(int xPosition, int yPosition, double speed) {
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
      sprite = ImageIO.read(new File("bullet.png"));
    } catch(Exception e) { System.out.println("Error Loading 'bullet.png'...");};
  }
  
  
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxPosition(), getyPosition(), null);
  }
  
  @Override
  public void travel(Player waylon, int xCursor, int yCursor) {
     if (getLimit() == false){
    setAngle((double)Math.atan2(yCursor - waylon.getyCentre(), xCursor - waylon.getxCentre()));
    setxDirection((double)((getSpeed()) * Math.cos(getAngle())));
    setyDirection((double)((getSpeed()) * Math.sin(getAngle())));
    setLimit(true);
    } 
    setxPosition(getxPosition() + (int)Math.round(getxDirection()));
    setyPosition(getyPosition() + (int)Math.round(getyDirection()));
    getHitbox().setLocation((int)Math.round(getHitbox().getX()) + (int)Math.round(getxDirection()), (int)Math.round(getHitbox().getY()) + (int)Math.round(getyDirection()));
  }
}