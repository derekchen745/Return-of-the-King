import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
 * [Zombie.java]
 * Basic monster
 * contains overriden draw and attack methods
 * @author Derek Chen
 */ 
class Zombie extends Monster{
  
  private BufferedImage sheet, spriteRight, spriteLeft;
  
  /**
   * Zombie constructor
   * sets hitbox
   * @param xPostion, the x coordinate of the zombie
   * @param yPostion, the y coordinate of the zombie
   * @param health, the max health of the zombie
   * @param moveSpeed, the zombie's travel speed
   * @param damage,  the zombie's base damage
   */ 
  Zombie(int xPosition, int yPosition, int health, int moveSpeed, int damage){
    super(xPosition, yPosition, health, moveSpeed, damage);
    loadSprites();
    setWidth(spriteRight.getWidth());
    setLength(spriteRight.getHeight());
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
      sheet = ImageIO.read(new File("zombieSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 56, 118);
      spriteLeft = sheet.getSubimage(56, 0, 56, 118);
    } catch(Exception e) {System.out.println("Error Loading 'zombieSheet.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    if (getDirection() == true) {
      g.drawImage(spriteRight, getxPosition(), getyPosition(), null);
    } else {
      g.drawImage(spriteLeft,getxPosition(), getyPosition(), null);
    }
    g.setColor(Color.red);
    g.fillRect(getxPosition()+10, getyPosition()-10, (getHealth()), 7);
  }
  
  public void move(Player waylon, SingleLinkedList<Character> characters) {
    int xDir = 0, yDir = 0;//initiate xDirection and yDirection variable for later use
    
    //get a direction of travel
    if ((getxCentre() == waylon.getxCentre()) && (getyCentre() >= waylon.getyCentre())) {
      xDir = 0;
      yDir = -(getMoveSpeed());
    } else if ((getxCentre() == waylon.getxCentre()) && (getyCentre() <= waylon.getyCentre())) {
      xDir = 0;
      yDir = getMoveSpeed();
    } else if ((getxCentre() <= waylon.getxCentre()) && (getyCentre() == waylon.getyCentre())) {
      xDir = getMoveSpeed();
      yDir = 0;
    } else if ((getxCentre() >= waylon.getxCentre()) && (getyCentre() == waylon.getyCentre())) {
      xDir = -(getMoveSpeed());
      yDir = 0;
    } else if ((getxCentre() > waylon.getxCentre()) && (getyCentre() > waylon.getyCentre())) {
      xDir = -(getMoveSpeed());
      yDir = -(getMoveSpeed());
    } else if ((getxCentre() < waylon.getxCentre()) && (getyCentre() > waylon.getyCentre())) {
      xDir = getMoveSpeed();
      yDir = -(getMoveSpeed());
    } else if ((getxCentre() < waylon.getxCentre()) && (getyCentre() < waylon.getyCentre())) {
      xDir = getMoveSpeed();
      yDir = getMoveSpeed();
    } else if ((getxCentre() > waylon.getxCentre()) && (getyCentre() < waylon.getyCentre())) {
      xDir = -(getMoveSpeed());
      yDir = getMoveSpeed();
    }
    //Stop moving when on top of the player
    if ((getxCentre() == waylon.getxCentre()) && (getyCentre() == waylon.getxCentre())){
      xDir = 0;
      yDir = 0;
    }
    
    //check if zombies are overlapping each other
    for (int i = 1; i < characters.size(); i++) {
      if (characters.get(i) instanceof Zombie) {
        Zombie zombie = (Zombie)characters.get(i);
        if (getHitbox().intersects(zombie.getHitbox())) {
          if ((getxCentre() < zombie.getxCentre())) {//super is left of the specified
            if(xDir > 0) xDir--;//slows down, moving right
            else if (xDir < 0) xDir++;//slows down, moving left
          }
          if ((getyCentre() < zombie.getyCentre())) {//super is above the specified
            if(yDir > 0) yDir--;//slows down, moving up
            else if (yDir < 0) yDir++;//slows down, moving down
          }
        }
      }
    }
    
    //Set the movement directions
    setxDirection(xDir);
    setyDirection(yDir);
    
    setxPosition(getxPosition() + getxDirection());
    setyPosition(getyPosition() + getyDirection());
    setxCentre(getxCentre() + getxDirection());
    setyCentre(getyCentre() + getyDirection());
    getHitbox().setLocation((int) getHitbox().getX() + getxDirection(), (int) getHitbox().getY() + getyDirection());
    
  }
  
  @Override
  public void attack(Player waylon) {
    setCooldown(100);
    waylon.setCurrentHealth(waylon.getCurrentHealth() - getDamage());
  } 
}