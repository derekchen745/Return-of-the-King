import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

/**
 * [HeadUpDisplay.java]
 * Displays player information
 * @author Waylon Chan
 */ 
public class HeadUpDisplay {
  private BufferedImage[] sprites;
  private Spawner spawner;
  private Player waylon;
  private SingleLinkedList weapons;
  private int healthBar;
  
  HeadUpDisplay(Spawner spawner, SingleLinkedList<Weapon> weapons, Player waylon){
    sprites = new BufferedImage[2];
    loadSprites();
    this.spawner = spawner;
    this.weapons = weapons;
    this.waylon = waylon;
    
    
  }
  
  /**
   * loadSprites
   * attempts to open the necessary image(s)
   * catches error if not possible
   */
  private void loadSprites() {
    try {
      sprites[0] = ImageIO.read(new File("ItemSlots.png"));
      sprites[1] = ImageIO.read(new File("itemIndicator.png"));
    } catch(Exception e) { System.out.println("Error Loading HUD Sprites... ");}
  }
  
   /**
  * draw 
  * draws the image to the screen
  * @param g, Graphics
  * @param weapons, the player's weapons
  * @param waylon, the player
  */
  public void draw(Graphics g, int level, SingleLinkedList<Weapon> weapons, Player waylon){
    if(waylon.getCurrentHealth() >= 0){
      healthBar = waylon.getCurrentHealth() * 510 / waylon.getHealth();
    }else{
      healthBar = 0;
    }
    
    //health bar
    g.setColor(Color.RED);
    g.fillRect(645, 847, healthBar, 25);
    g.setColor(Color.LIGHT_GRAY);
    g.drawRect(645, 847, 510, 25);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    g.setColor(Color.WHITE);
    g.drawString("Health " + waylon.getCurrentHealth() + "/" + waylon.getHealth(), 650, 867);
    
    //item slots
    g.drawImage(sprites[0], 645, 875, null);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    g.setColor(Color.BLACK);
    g.drawString("1", 683, 950);
    g.drawString("2", 768, 950);
    g.drawString("3", 853, 950);
    g.drawString("4", 938, 950);
    g.drawString("5", 1023, 950);
    g.drawString("6", 1108, 950);
    
    
    //display level number
    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    g.setColor(Color.WHITE);
    g.drawString("Room : " + level, 1600, 30);
    
    //displau ammo counter
    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    g.setColor(Color.WHITE);
    g.drawString("Ammo " + weapons.get(spawner.getCurrentWeapon()).getCurrentAmmo() + "/" + weapons.get(spawner.getCurrentWeapon()).getClipSize() , 20, 970);
    
    //selected weapon indicator
    if(spawner.getCurrentWeapon() == 0){
      g.drawImage(sprites[1], 645, 875, null);
    }else if(spawner.getCurrentWeapon() == 1){
      g.drawImage(sprites[1], 730, 875, null);
    }else if(spawner.getCurrentWeapon() == 2){
      g.drawImage(sprites[1], 815, 875, null);
    }
  }
}