// class for the the game area - This is where all the drawing of the screen occurs

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random; 
import java.awt.Rectangle;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter; 
import javax.swing.SwingUtilities;

/**
 * [GamePanel.java]
 * Displays the visuals for the game
 * @author Derek Chen, Waylon Chan
 */ 
class GamePanel extends JPanel {
  
  private boolean game;
  private HeadUpDisplay hud;
  private LevelUp levelUp;
  private int level;
  private Random random;
  private Mouse mouse;
  private KeyPressed keys;
  private Spawner spawner;
  private Player waylon;
  private SingleLinkedList<Character> characters;
  private SingleLinkedList<Weapon> weapons;
  private SingleLinkedList<Projectile> projectiles;
  private SingleLinkedList<Chest> chests;
  private SingleLinkedList<Consumable> consumables;
  private SingleLinkedList<Entity> inventory;
  private SingleLinkedList<Consumable> drops;
  private Image floor;
  private boolean endLevel;
  private boolean drawLevel;
  private boolean alive;
  private int finalLevel;
  
  
  /**
   * GamePanel constructor
   * @param characters, a list containing all characters
   * @param weapons, a list containing all of the player's weapons
   * @param projectiles, a list containing all projectiles on the screen
   * @param hud, HeadUpDisplay
   * @param chests, a list containing all chests on the screen
   * @param spawner, spawner that creates game objects
   * @param consumables, a list containing all consumables in the game
   * @param inventory, a list containing all items the player owns
   * @param drops, a list containing all monster drops on the screen
   */ 
  GamePanel(SingleLinkedList<Character> characters, SingleLinkedList<Weapon> weapons, SingleLinkedList<Projectile> projectiles, 
            HeadUpDisplay hud, SingleLinkedList<Chest> chests, Spawner spawner, SingleLinkedList<Consumable> consumables,
            SingleLinkedList<Entity> inventory, SingleLinkedList<Consumable> drops){
    this.spawner = spawner;
    this.waylon = (Player)characters.get(0);
    this.characters = characters;
    this.weapons = weapons;
    this.projectiles = projectiles;
    this.hud = hud;
    this.chests = chests;
    this.consumables = consumables;
    this.inventory = inventory;
    this.drops = drops;
    
    //Game Object Initialization
    //Spawner spawner = new Spawner(characters, weapons, projectiles, chests);
    floor = Toolkit.getDefaultToolkit().getImage("map.png");
    random = new Random(); 
    level = 1;
    game = true;
    
    
    //Listeners
    keys = new KeyPressed(waylon, spawner, weapons, characters, chests, consumables, inventory);
    this.addKeyListener(keys);
    
    mouse = new Mouse(waylon, spawner);
    this.addMouseListener(mouse);
    
    
    //JPanel Stuff
    this.setFocusable(true);
    this.requestFocusInWindow(); 
    
    
    //Start the game loop in a separate thread (allows simple frame rate control)
    //the alternate is to delete this and just call repaint() at the end of paintComponent()
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop 
    t.start(); 
  }
  
  
  //The main gameloop - this is where the game state is updated
  public void animate() { 
    
    monsterWaves(level);      
    while(game){
      
      if(waylon.getCurrentHealth() > 0){
        reloadCounter();
        speedCounter();
        
        //update game content
        
        //Monster Movement - Derek Chen
        for (int i=1; i<characters.size(); i++){
          if (characters.get(i).getxCentre() > waylon.getxCentre()) { 
            ((Monster)characters.get(i)).setDirection(true);
            if(characters.get(i) instanceof Zombie){
              ((Zombie)characters.get(i)).move(waylon, characters);
            }else if(characters.get(i) instanceof Goblin){
              ((Goblin)characters.get(i)).move(waylon, characters);
            }
          } else {
            ((Monster)characters.get(i)).setDirection(false);
            if(characters.get(i) instanceof Zombie){
              ((Zombie)characters.get(i)).move(waylon, characters);
            }else if(characters.get(i) instanceof Goblin){
              ((Goblin)characters.get(i)).move(waylon, characters);
            }
          }
          
          //Monster Cooldown Counter - Derek Chen
          if (((Monster)characters.get(i)).getCooldown() > 0){
            ((Monster)characters.get(i)).setCooldown(((Monster)characters.get(i)).getCooldown() - 1);
          }
          
        }
        
        //Player Animation and Movement - Derek Chen
        if (keys.movement()){
          waylon.animate();
        }
        else if (mouse.trackX() > waylon.getxCentre()) { //Human Animations 
          waylon.setSprite(4);  
        } else { 
          waylon.setSprite(1); 
        } 
        if ((waylon.getxPosition() >= -20) && (waylon.getxPosition() <= 1715)){
          waylon.move();
        }
        if (waylon.getxPosition() < -20){//Left and Right Borders
          waylon.setxPosition(0);
          waylon.setxCentre(waylon.getxPosition() + waylon.getWidth() / 2);
        } else if (waylon.getxPosition() > 1715){
          waylon.setxPosition(1705);
          waylon.setxCentre(waylon.getxPosition() + waylon.getWidth() / 2);
        } 
        
        if ((waylon.getyPosition() >= -20) && (waylon.getyPosition() <= 850)){
          waylon.move();
        }
        if (waylon.getyPosition() < -20){//Top and Bottom Borders
          waylon.setyPosition(-10);
          waylon.setyCentre(waylon.getyPosition() + waylon.getLength() / 2);
        } else if (waylon.getyPosition() > 880){
          waylon.setyPosition(870);
          waylon.setyCentre(waylon.getyPosition() + waylon.getLength() / 2);
        } 
        
        
        //Weapon Sprite Directions - Derek Chen
        if (spawner.getCurrentWeapon() == 0) {
          if (mouse.trackX() > waylon.getxCentre()) {
            weapons.get(0).setDirection(true);
          } else {
            weapons.get(0).setDirection(false);
          }
        } else if (spawner.getCurrentWeapon() == 1) {
          if (mouse.trackX() > waylon.getxCentre()) {
            weapons.get(1).setDirection(true);
          } else {
            weapons.get(1).setDirection(false);
          }
        } else if (spawner.getCurrentWeapon() == 2) {
          if (mouse.trackX() > waylon.getxCentre()) {
            weapons.get(2).setDirection(true);
          } else {
            weapons.get(2).setDirection(false);
          }
        }
        
        //Weapon Movement with Player - Waylon Chan
        for (int i = 0; i < weapons.size(); i++) {
          if (weapons.get(i) != null) {
            weapons.get(i).move(waylon);
          }
        }
        
        //Automatic Weapon(SMG) - Waylon Chan
        if ((spawner.getCurrentWeapon() == 1) && (mouse.getHolding() == true)) {
          spawner.fireBullet(waylon);
        }
        
        //Bullet Movement - Derek Chen
        for (int i=0; i < projectiles.size(); i++){
          projectiles.get(i).travel(waylon, mouse.getxCursor(), mouse.getyCursor());
        }
        
        //Hitbox Checks - Derek Chen
        for (int i=0; i < projectiles.size(); i++){ 
          for(int j=1; j < characters.size(); j++){
            if ((projectiles.get(i) != null) && (projectiles.get(i) instanceof Bullet)){//Bullet and Monster Checks
              if(projectiles.get(i).getHitbox().intersects(characters.get(j).getHitbox())){
                projectiles.remove(i);
                characters.get(j).setHealth(characters.get(j).getHealth() - weapons.get(spawner.getCurrentWeapon()).getDamage());
              }
            }else if ((projectiles.get(i) != null) && (projectiles.get(i) instanceof Fireball)){//Fireball and Player Checks
              if(projectiles.get(i).getHitbox().intersects(waylon.getHitbox())){
                projectiles.remove(i);
                waylon.setCurrentHealth(waylon.getCurrentHealth() - 40);
              }
            }else if ((projectiles.get(i) != null) && (!((projectiles.get(i).getxPosition()> 0) && (projectiles.get(i).getxPosition() < 1800)))){//Bullets off Screen
              projectiles.remove(i);
            }else if ((projectiles.get(i) != null) && (!((projectiles.get(i).getyPosition()> 0) && (projectiles.get(i).getyPosition() < 1000)))){
              projectiles.remove(i);
            }
          }
        }
        
        //Monster Drops - Derek Chen
        for (int i=1; i < characters.size(); i++){
          if (characters.get(i).getHealth() <= 0){
            int randDrop = random.nextInt(30);
            if((randDrop == 1) || (randDrop == 3)){
              spawner.spawnHealthPotion((Monster)characters.get(i));
            }
            if(randDrop == 2){
              spawner.spawnSpeedPotion((Monster)characters.get(i));
            }
            characters.remove(i);
          }
        }
        
        //Picking up Monster Drops - Derek Chen
        for (int i=0; i < drops.size(); i++){
          if(characters.get(0).getHitbox().intersects(drops.get(i).getHitbox())){
            consumables.add(drops.get(i));
            if(drops.get(i) instanceof HealthPotion){
              drops.get(i).setxPosition(925);
              drops.get(i).setyPosition(895);
              inventory.add(drops.get(i));
              drops.remove(i);
            }
            else if(drops.get(i) instanceof SpeedPotion){
              drops.get(i).setxPosition(1014);
              drops.get(i).setyPosition(894);
              inventory.add(drops.get(i));
              drops.remove(i);
            } 
            
          }
        }
        
        //Monster Attacks - Derek Chen
        for (int i=1; i < characters.size(); i++){
          if(characters.get(i) instanceof Wizard){
            if(((Monster)characters.get(i)).getCooldown() == 0){
              spawner.shootFireball(((Wizard)characters.get(i)), waylon);
              ((Monster)characters.get(i)).setCooldown(200);
            }
          }
          if(characters.get(i).getHitbox().intersects(waylon.getHitbox())){
            if(((Monster)characters.get(i)).getCooldown() == 0){
              if(characters.get(i) instanceof Zombie){
                ((Zombie)characters.get(i)).attack(waylon);
              }else if(characters.get(i) instanceof Goblin){
                ((Goblin)characters.get(i)).attack(waylon);
              }
              
            }
          }
        }
        
        //Spawning Chest and Starting Next Level - Derek Chen
        if(characters.get(1) == null){
          endLevel = true;
        }
        if(endLevel == true){
          while(chests.get(0) == null){
            int randItem = random.nextInt(2);
            spawner.spawnChest(waylon, randItem);
          }
          
          //Selecting a stat to boost
          while(spawner.getOpened() == true){
            levelUp = new LevelUp();
            weapons.get(spawner.getCurrentWeapon()).setShootingLock(true);
            drawLevel = true;
            mouse.resetxCursor();
            mouse.resetyCursor();
            spawner.setOpened(false);
          }
          
          //Reset positions and create new level - Derek Chen
          if(levelUp != null){
            if(levelUp.getHitboxHealth().contains(mouse.getxCursor(), mouse.getyCursor())){
              waylon.setHealth(waylon.getHealth() + 25);
              waylon.setCurrentHealth(waylon.getCurrentHealth()+25);
              levelUp = null;
              drawLevel = false;
              reset();
            }else if(levelUp.getHitboxSpeed().contains(mouse.getxCursor(), mouse.getyCursor())){
              waylon.setMoveSpeed(waylon.getMoveSpeed() + 1);
              levelUp = null;
              drawLevel = false;
              reset();
            }
          }
        }
      }else if(waylon.getCurrentHealth() <= 0){//Reset game on player death
        finalLevel = level;
        try{
          //File highscores = new File("highscores.txt");
          FileWriter scores = new FileWriter("highscores.txt" , true);
          BufferedWriter bw = new BufferedWriter(scores);
          PrintWriter output = new PrintWriter(bw);
          output.println("User "+ finalLevel );
          output.close(); 
          System.out.println("Data successfully saved");
        }catch(Exception e){
          System.out.print("The data could not be saved...");
        }
        
        new EndFrame();
        game = false;
        GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
      }
      
      //delay
      try{ Thread.sleep(10);} 
      catch (Exception exc){
        System.out.println("Thread Error");
      }
      
      //repaint request
      this.repaint();
    }
  }
  
  
  
//paintComponnent Runs everytime the screen gets refreshed
  public void paintComponent(Graphics g) {   
    super.paintComponent(g); //required
    setDoubleBuffered(true); 
    g.drawImage(floor, 0, 0, this);
    hud.draw(g, level, weapons, waylon);
    //obstruction.draw(g);
    
    //screen is being refreshed - draw all objects
    for (int i = 0; i < characters.size(); i++){
      characters.get(i).draw(g);
    }
    
    weapons.get(spawner.getCurrentWeapon()).draw(g);
    
    for (int i = 0; i < projectiles.size(); i++){
      projectiles.get(i).draw(g);
    }
    
    for (int i = 0; i < chests.size(); i++){
      chests.get(i).draw(g);
    }
    
    for (int i = 0; i < inventory.size(); i++){
      inventory.get(i).draw(g);
    }
    
    for (int i = 0; i < drops.size(); i++){
      drops.get(i).draw(g);
    }
    
    if(drawLevel == true){
      levelUp.draw(g);
    }   
  }   
  
  /**
   * monsterWaves
   * Spawns monsters according to the level the player is on
   * @param level, the player's current level
   */ 
  public void monsterWaves(int level){
    int max = 2 + (level * 4);
    for (int i=0; i < max; i++){
      int xRange = random.nextInt(200)+1500; 
      int yRange = random.nextInt(700)+65; 
      int randomMonster = random.nextInt(10);
      if (randomMonster <= 5){
        spawner.spawnZombie(xRange, yRange, 40, 2, 10);
      }else if (randomMonster > 5 && randomMonster <= 8){
        spawner.spawnGoblin(xRange, yRange, 25, 3, 5);
      }else if (randomMonster == 9){
        spawner.spawnWizard(xRange, yRange, 90, 0, 0);
      }
    }
  }
  
  /**
   * reloadCounter
   * Checks if reload is available
   */
  public void reloadCounter() {
    if (weapons.get(spawner.getCurrentWeapon()).getReloading() == true){
      if (System.currentTimeMillis() >= weapons.get(spawner.getCurrentWeapon()).getReloadSpeed()+weapons.get(spawner.getCurrentWeapon()).getReloadTime()) {
        weapons.get(spawner.getCurrentWeapon()).reload();
      }
    }
  }
  
  public void speedCounter(){
    SpeedPotion speedPotion = new SpeedPotion(0,0,0);
    if(keys.getBoosted() == true){
      int index = consumables.indexOf(speedPotion);
      if(System.currentTimeMillis() >= ((SpeedPotion)consumables.get(index)).getBuffTime() + ((SpeedPotion)consumables.get(index)).getDuration()){
        waylon.setMoveSpeed(waylon.getMoveSpeed() - 1);
        keys.setBoosted(false);
        consumables.remove(index);
      }
    }
  }
  

  
  public void reset(){
    endLevel = false;
    chests.clear();
    drops.clear();
    level++;
    waylon.setxPosition(200);
    waylon.setyPosition(760);
    waylon.setxCentre(waylon.getxPosition() + (waylon.getWidth() / 2));
    waylon.setyCentre(waylon.getyPosition() + (waylon.getLength() / 2));
    waylon.setHitbox(new Rectangle(waylon.getxPosition()+15, waylon.getyPosition()+10, waylon.getWidth()-20, waylon.getLength()-10));
    
    weapons.get(spawner.getCurrentWeapon()).setShootingLock(false);
    monsterWaves(level);
  }
}

