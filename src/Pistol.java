import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
 * [Pistol.java]
 * a weak but reliable weapon that shoots projectiles
 * subclass to Weapon
 * contains overridden draw, move and reload methods
 * @author Derek Chen
 */
class Pistol extends Weapon {
  private BufferedImage sheet, spriteRight, spriteLeft;
  
  /**
   * Pistol constructor 
   * @param xCoordinate, the x position of the pistol
   * @param yCoordinate, the y position of the pistol
   * @param damage, the damage value of the pistol
   * @param clipSize, the pistol's max ammo
   * @param reloadSpeed, the time it takes to reload the pistol
   */
  Pistol(int xPosition, int yPosition, int damage, int clipSize, int reloadSpeed){
    super(xPosition, yPosition, damage, clipSize,  reloadSpeed);
    loadSprites();
    setDirection(true);
  }
  
  /**
   * loadSprites 
   * attempts to load all images required for pistol
   * catches error if not possible
   */
  private void loadSprites() {
    try {
      sheet = ImageIO.read(new File("pistolSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 61, 27);
      spriteLeft = sheet.getSubimage(61, 0, 61, 27);
    } catch(Exception e) {System.out.println("Error Loading 'pistolSheet.png'...");};
  }
  
  @Override 
  public void draw(Graphics g) {
    if (getDirection() == true) {
      g.drawImage(spriteRight, getxPosition()+2, getyPosition()+16, null);
    } else {
      g.drawImage(spriteLeft, getxPosition()-63, getyPosition()+16, null);
    }
  }
  
  @Override
  public void move(Player waylon) {
    setxPosition(waylon.getxCentre());
    setyPosition(waylon.getyCentre() - 10);
  }
  
  @Override
  public void reload(){
    if (getCurrentAmmo() < getClipSize()) {
      setCurrentAmmo(getClipSize());
    }
    setReloading(false);
    setShootingLock(false);
  }
}
