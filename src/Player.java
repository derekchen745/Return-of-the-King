import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

/**
 * [Player.java]
 * Represents the player
 * @author Derek Chen
 */ 
class Player extends Character {   
  
  private BufferedImage sheet;
  private BufferedImage[] sprites;
  private final int width = 88, length = 126, columns = 3, rows = 2;
  private int sprite, frame;
  
  /**
   * Player constructor
   * sets hitbox
   * @param xCoordinate, the x position of the player
   * @param yCoordinate, the y position of the player
   * @param health, the player's max health
   * @param moveSpeed, the player's movement speed
   */
  Player(int xPosition, int yPosition, int health, int moveSpeed) { 
    super(xPosition, yPosition, health, moveSpeed); 
    sprites = new BufferedImage[rows * columns];
    loadSprites();
    setWidth(sprites[0].getWidth());
    setLength(sprites[0].getHeight());
    setxCentre(xPosition + (getWidth() / 2));
    setyCentre(yPosition + (getLength() / 2));
    setHitbox(new Rectangle(xPosition+15, yPosition+10, sprites[0].getWidth()-20, sprites[0].getHeight()-10));

  }
  /**
   * loadSprites 
   * attempts to load all images required for pistol
   * catches error if not possible
   */
  public void loadSprites() { 
    try {
      sheet = ImageIO.read(new File("playerSheet.png"));
      }catch(Exception e) { 
      System.out.println("error loading sprite");};
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          sprites[(i * columns) + j] = sheet.getSubimage(j * width, i * length, width, length);
        }
      }
    
  }
  
  @Override
  public void draw(Graphics g) { 
    g.drawImage(sprites[sprite],getxPosition(), getyPosition(), null);
  }
  
  /**
   * move
   * Moves the player sprite's location based on player input
   */ 
  public void move() {
    setxPosition(getxPosition() + getxDirection());
    setyPosition(getyPosition() + getyDirection());
    setxCentre(getxCentre() + getxDirection());
    setyCentre(getyCentre() + getyDirection());
    getHitbox().setLocation((int)getHitbox().getX() + getxDirection(), (int)getHitbox().getY() + getyDirection());
  }
  
  /**
   * animate
   * Used to produce a walking animation for the player
   */ 
  public void animate() {
    if (frame % 12 == 0) {
      sprite++;
    }

    if (frame > 36) {
      frame = 0;
    }

    frame++;

    if (sprite == 2) {
      sprite = 0;
    } else if (sprite == 5) {
      sprite = 3;
    }
  }
  
  
  /**setSprite
   * A method that sets the integer value of sprite number
   * @param sprite, an integer that holds the data of sprite number
   */
  public void setSprite(int sprite) {
    this.sprite = sprite;
  }

  /**getSprite
   * A method that gets the integer value of sprite number
   * @return the integer value of sprite number
   */
  public int getSprite() {
    return sprite;
  }
}