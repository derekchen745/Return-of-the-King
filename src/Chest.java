import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
 * [Chest.java]
 * Chest containing a random item
 * @author Derek Chen
 */ 
class Chest<E> extends Entity{
  
  private E item;
  private BufferedImage sprite;
  
  /**
   * Chest constructor
   * sets hitbox
   * @param xPosition, the x position of the chest
   * @param yPosition, the y position of the chest
   * @param item, the item inside the chest
   */ 
  Chest(int xPosition, int yPosition, E item){
    super(xPosition, yPosition);
    this.item = item;
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
      sprite = ImageIO.read(new File("chest.png"));
    } catch(Exception e) {System.out.println("Error Loading 'chest.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite,getxPosition(), getyPosition(), null);
  }
  
  /**
   * setItem
   * Sets the item inside of the chest
   * @param item, the item being placed in the chest
   */ 
  public void setItem(E item){
    this.item = item;
  }
  
  /**
   * open
   * opens the chest
   * @return E, the item inside of the chest
   */ 
  public E open(){
    return item;
  }
}