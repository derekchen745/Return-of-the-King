/**
 * This template can be used as reference or a starting point
 * for your final summative project
 * @author Mangat
 **/

//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;


/**
 * [GameFrame.java]
 * the main frame of the game
 * @author Derek Chen, Waylon Chan
 */
class GameFrame extends JFrame { 
  
  private HeadUpDisplay hud;
  private Player waylon;
  private Pistol pistol;
  private Spawner spawner;
  private SingleLinkedList<Character> characters = new SingleLinkedList<Character>();
  private SingleLinkedList<Weapon> weapons = new SingleLinkedList<Weapon>();
  private SingleLinkedList<Projectile> projectiles = new SingleLinkedList<Projectile>();
  private SingleLinkedList<Chest> chests = new SingleLinkedList<Chest>();
  private SingleLinkedList<Consumable> consumables = new SingleLinkedList<Consumable>();
  private SingleLinkedList<Consumable> drops = new SingleLinkedList<Consumable>();
  private SingleLinkedList<Entity> inventory = new SingleLinkedList<Entity>();
  
  //Game Screen
  static GamePanel gamePanel;
  
  /**
   * GameFrame constructor
   * intializes all constant game objects
   */ 
  GameFrame() { 
    super("RETURN OF THE KING"); 
    spawner = new Spawner(characters, weapons, projectiles, chests, inventory, drops);
    spawner.spawnPlayer(200, 760, 100, 4);
    waylon = (Player)characters.get(0);
    hud = new HeadUpDisplay(spawner, weapons, waylon);

    
    
    spawner.addPistol(waylon);
    spawner.setCurrentWeapon(0);
    // Set the frame to full screen 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.setSize(1800,1057);//57 because the top bar is taken into account
    //this.setUndecorated(true);  //Set to true to remove title bar
    //frame.setResizable(false);
    
    //Set up the game panel (where we put our graphics)
    gamePanel = new GamePanel(characters, weapons, projectiles, hud, chests, spawner, consumables, inventory, drops);
    this.add(gamePanel);
    
    this.setFocusable(false);  //we will focus on the JPanel
    this.setVisible(true);    
  } 

}




