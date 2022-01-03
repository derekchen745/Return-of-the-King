import java.awt.Rectangle;
import java.awt.Graphics;

/**
* [Entity.java]
* contains abstract method draw
* Superclass to all in game objects
* @author Derek Chen
*/
abstract class Entity {
  
  private int xPosition, yPosition, width, length;
  private Rectangle hitbox;
  
  /**
  * Entity constructor 
  * @param x, the x position of the entity
  * @param y, the y position of the entity
  */
  Entity (int x, int y) {
    this.xPosition = x;
    this.yPosition = y;
  }

  /**
  * draw 
  * draws the image to the screen
  * @param g, Graphics
  */
  abstract void draw(Graphics g);
  
  /**
  * setxPosition
  * @param xPosition, int value of xPosition
  */   
  public void setxPosition(int xPosition) {
    this.xPosition = xPosition;
  }

  /**
  * setyPosition
  * @param yPosition, int value of yPosition
  */   
  public void setyPosition(int yPosition) {
    this.yPosition = yPosition;
  }

  /**
  * setWidth
  * @param Width, int value of Width
  */   
  public void setWidth(int width) {
    this.width = width;
  }

  /**
  * setLength
  * @param Length, int value of Length
  */   
  public void setLength(int length) {
    this.length = length;
  }

  /**
  * getHitbox 
  * @return Rectangle, value of hitbox
  */
  public Rectangle getHitbox() {
    return hitbox;
  }

  /**
  * setHitbox
  * @param hitbox, Rectangle value of hitbox
  */   
  public void setHitbox(Rectangle hitbox) {
    this.hitbox = hitbox;
  }

  /**
  * getxPosition 
  * @return int, value of xPosition
  */
  public int getxPosition() {
    return xPosition;
  }

  /**
  * getyPosition
  * @return int, value of yPosition
  */
  public int getyPosition() {
    return yPosition;
  }

  /**
  * getWidth 
  * @return int, value of Width
  */
  public int getWidth() {
    return width;
  }

  /**
  * getLength
  * @return int, value of Length
  */
  public int getLength() {
    return length;
  }
  
}