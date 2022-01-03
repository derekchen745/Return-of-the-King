import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

/**
 * [LevelUp.java]
 * Displays level information when a room is completed
 * @author Derek Chen
 */ 
class LevelUp{
  
  private int xPosition, yPosition;
  private BufferedImage sprite;
  private Rectangle hitboxHealth, hitboxSpeed;
  private boolean leveledUp;
  
  LevelUp(){
    this.xPosition = 0;
    this.yPosition = 0;
    loadSprites();
    hitboxHealth = new Rectangle(550, 292, 350, 416);
    hitboxSpeed = new Rectangle(900, 292, 350, 416);
    leveledUp = true;
    System.out.println("level up");
    

  }
  
  private void loadSprites() {
    try {
      sprite = ImageIO.read(new File("levelUp.png"));
    } catch(Exception e) {System.out.println("Error Loading 'levelUp.png'...");};
  }

  public void draw(Graphics g) {
    g.drawImage(sprite, getxPosition(), getyPosition(), null);

    g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
    g.setColor(Color.WHITE);
    g.drawString("SELECT A STAT TO INCREASE", 630, 770);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    g.setColor(Color.WHITE);
    g.drawString("THE NEXT LEVEL WILL BEGIN ONCE SELECTED", 590, 800);
  }
  
   /**
  * setxPosition
  * @param xPosition, int value of xPosition
  */   
  public void setxPosition(int xPosition) {
    this.xPosition = xPosition;
  }

  /**
  * setyCord
  * @param yCord, int value of yCord
  */   
  public void setyPosition(int yPosition) {
    this.yPosition = yPosition;
  }
  
   /**
  * getxPosition 
  * @return int value of xPosition
  */
  public int getxPosition() {
    return xPosition;
  }

  /**
  * getyCord 
  * @return int value of yCord
  */
  public int getyPosition() {
    return yPosition;
  }
  
  /**
  * getHitboxHealth
  * @return Rectangle, value of hitboxHealth
  */
  public Rectangle getHitboxHealth() {
    return hitboxHealth;
  }

  /**
   * getHitboxSpeed
   * @return Rectanlge, value of hitboxSpeed
   */ 
  public Rectangle getHitboxSpeed() {
    return hitboxSpeed;
  }
  
  /**
   * setLeveledUp
   * @parameter leveledUp, boolean value of leveled up
   */ 
  public void setLeveledUp(boolean leveledUp){
    this.leveledUp = leveledUp;
  }
  
  /**
   * getLeveledUp
   * @return boolean, true if leveledUp, else false
   */ 
  public boolean getLeveledUp(){
    return leveledUp;
  }
}