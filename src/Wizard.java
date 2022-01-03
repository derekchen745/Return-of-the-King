import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
 * [Wizard.java]
 * Stationary monster that throws fireballs
 * contains overriden draw and attack methods
 * @author Derek Chen
 */ 
class Wizard extends Monster{
  
  private BufferedImage sprite;
    
  /**
   * Wizard constructor
   * sets hitbox
   * @param xPostion, the x coordinate of the wizard
   * @param yPostion, the y coordinate of the wizard
   * @param health, the max health of the wizard
   * @param moveSpeed, the wizard's travel speed
   * @param damage,  the wizard's base damage
   */ 
    Wizard(int xPosition, int yPosition, int health, int moveSpeed, int damage){
    super(xPosition, yPosition, health, moveSpeed, damage);
    setCooldown(200);
    loadSprites();
    setWidth(sprite.getWidth());
    setLength(sprite.getHeight());
    setxCentre(xPosition + (getWidth() / 2));
    setyCentre(yPosition + (getLength() / 2));
    setHitbox(new Rectangle(xPosition, yPosition, getWidth() - 25, getLength()));
  }
    
 /**
   * loadSprites 
   * attempts to load all images required for pistol
   * catches error if not possible
   */
   private void loadSprites() {
    try {
      sprite = ImageIO.read(new File("wizard.png"));
    } catch(Exception e) {System.out.println("Error Loading 'wizard.png'...");};
  }
  
 @Override
  public void draw(Graphics g) {
    g.drawImage(sprite,getxPosition(), getyPosition(), null);
    
    g.setColor(Color.red);
    g.fillRect(getxPosition()+7, getyPosition()-10, (getHealth()) / 2, 7);
  }
 
 
 @Override
  public void attack(Player waylon) {
    setCooldown(100);
    waylon.setHealth(waylon.getHealth() - getDamage());
  } 
}