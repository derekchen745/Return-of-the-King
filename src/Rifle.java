import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
 * [Rifle.java]
 * a powerful weapon that shoots projectiles
 * contains overridden draw, move and reload methods
 * @author Waylon Chan
 */
class Rifle extends Weapon {
  private BufferedImage sheet, spriteRight, spriteLeft;
  
  /**
   * Rifle constructor 
   * @param xCoordinate, the x position of the rifle
   * @param yCoordinate, the y position of the rifle
   * @param damage, the damage value of the rifle
   * @param clipSize, the rifle's max ammo
   * @param reloadSpeed, the time it takes to reload the rifle
   */
  Rifle(int xPosition, int yPosition, int damage, int clipSize, int reloadSpeed){
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
      sheet = ImageIO.read(new File("rifleSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 75, 30);
      spriteLeft = sheet.getSubimage(75, 0, 75, 30);
    } catch(Exception e) {System.out.println("Error Loading 'rifleSheet.png'...");};
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