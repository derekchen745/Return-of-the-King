import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
 * [Goblin.java]
 * A faster monster
 * contains overriden draw and attack method
 * @author Derek Chen
 */ 
class Goblin extends Monster{
  
  private BufferedImage sheet, spriteRight, spriteLeft;
  
  /**
   * Goblin constructor
   * sets hitbox
   * @param xPostion, the x coordinate of the goblin
   * @param yPostion, the y coordinate of the goblin
   * @param health, the max health of the goblin
   * @param moveSpeed, the goblin's travel speed
   * @param damage,  the goblin's base damage
   */ 
  Goblin(int xPosition, int yPosition, int health, int moveSpeed, int damage){
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
   * attempts to open the necessary image(s)
   * catches error if not possible
   */
  private void loadSprites() {
    try {
      sheet = ImageIO.read(new File("goblinSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 50, 64);
      spriteLeft = sheet.getSubimage(50, 0, 50, 64);
    } catch(Exception e) {System.out.println("Error Loading 'goblinSheet.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    if (getDirection() == false) {
      g.drawImage(spriteRight, getxPosition(), getyPosition(), null);
    } else {
      g.drawImage(spriteLeft,getxPosition(), getyPosition(), null);
    }
    g.setColor(Color.red);
    g.fillRect(getxPosition()+10, getyPosition()-10, (getHealth()), 7);
  }
  
  /**
   * move
   * sets the goblin on a path towards the player
   * @param waylon, the player
   * @param characters, a single linked list constaining all characters(player + monster)
   */ 
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
    
    //check if goblins are overlapping each other
    for (int i = 1; i < characters.size(); i++) {
      if (characters.get(i) instanceof Goblin) {
        Goblin goblin = (Goblin)characters.get(i);
        if (getHitbox().intersects(goblin.getHitbox())) {
          if ((getxCentre() < goblin.getxCentre())) {//super is left of the specified
            if(xDir > 0) xDir--;//slows down, moving right
            else if (xDir < 0) xDir++;//slows down, moving left
          }
          if ((getyCentre() < goblin.getyCentre())) {//super is above the specified
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