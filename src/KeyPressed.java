// class for the keyboard listener - this detects key presses and runs the corresponding code

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * [KeyPressed.java]
 * Controls all keyboard interations
 * @author Derek Chen, Waylon Chan
 * Derek Chen - Movement, Reload, Opening Chests
 * Waylon Chan - Swapping Weapons, Consumables
 */ 
class KeyPressed implements KeyListener {
  
  //reference to items effected by keyboard press
  private Player waylon;
  private Spawner spawner;
  private SingleLinkedList<Character> characters;
  private SingleLinkedList<Chest> chests;
  private SingleLinkedList<Weapon> weapons;
  private SingleLinkedList<Consumable> consumables;
  private SingleLinkedList<Entity> inventory;
  private boolean left, right, down, up;
  private boolean boosted;
  private boolean found;
  private int counter;
  
  /**
   * KeyPressed Constructor
   * @param waylon, the player
   * @param spawner, spawner that creates game objects
   * @param weapons, a list containing all of the player's weapons
   * @param characters, a list containing all characters(monster + player)
   * @param chests, a list containing all chests
   * @param consumables, a list containing all consumables in the game
   * @param inventory, a list containing every the player has
   */ 
  KeyPressed(Player waylon, Spawner spawner, SingleLinkedList<Weapon> weapons, SingleLinkedList<Character> characters, 
             SingleLinkedList<Chest> chests, SingleLinkedList<Consumable> consumables, SingleLinkedList<Entity> inventory) {
    this.waylon = waylon;
    this.spawner = spawner;
    this.weapons = weapons;
    this.characters = characters;
    this.chests = chests;
    this.consumables = consumables;
    this.inventory = inventory;
    counter = 0;
  }

  public void keyTyped(KeyEvent e) { }
  
  /**
   * keyPressed
   * checks all interactions when a key is pressed
   * @param e, Key Event
   */ 
  public void keyPressed(KeyEvent e) {
    if(e.getKeyChar() == 'a'){//waylon moves along x and negative speed - left
      left = true;
      waylon.setxDirection(0-waylon.getMoveSpeed());
    } else if(e.getKeyChar() == 's' ){
      down = true; 
      waylon.setyDirection(waylon.getMoveSpeed()); //wayln moves along y at positive speed - down
    } else if(e.getKeyChar() == 'd' ){
      right = true;
      waylon.setxDirection(waylon.getMoveSpeed()); //waylon moves along x at positive speed - right
    } else if(e.getKeyChar() == 'w' ){
      up = true;
      waylon.setyDirection(0-waylon.getMoveSpeed()); //waylon moves along y at negative speed - up
    } else if(e.getKeyChar() == 'r' ){//reload
      if(weapons.get(spawner.getCurrentWeapon()).getCurrentAmmo() < weapons.get(spawner.getCurrentWeapon()).getClipSize()){
        weapons.get(spawner.getCurrentWeapon()).setReloadTime(System.currentTimeMillis());
        weapons.get(spawner.getCurrentWeapon()).setReloading(true); 
        weapons.get(spawner.getCurrentWeapon()).setShootingLock(true); //lock shooting while reloading
      }
    } else if(e.getKeyChar() == 'f' ){//opening chests
      if(characters.get(0).getHitbox().intersects(chests.get(0).getHitbox())){
        if(chests.get(0).open() instanceof Weapon){
          weapons.add((Weapon)chests.get(0).open());
          if((Weapon)chests.get(0).open() instanceof SMG){
            SMG uziInv = new SMG(745,885, 0, 0, 0);
            inventory.add(uziInv);
          }else{
            Rifle rifleInv = new Rifle(823,885, 0, 0, 0);
            inventory.add(rifleInv);
          }
          spawner.setOpened(true);
        }else if(chests.get(0).open() instanceof Consumable){
          consumables.add((Consumable)chests.get(0).open());
          if((Consumable)chests.get(0).open() instanceof HealthPotion){
            HealthPotion healthPotInv = new HealthPotion(925,895, 30);
            inventory.add(healthPotInv);
          }else if((Consumable)chests.get(0).open() instanceof SpeedPotion){
            SpeedPotion speedPotInv = new SpeedPotion(1014,894, 0);
            inventory.add(speedPotInv);
          }
        }
        spawner.setOpened(true);
      }
      
    } 
    
    //Swapping weapons and using consumables
    if (e.getKeyChar() == '1' ){
      spawner.setCurrentWeapon(0);
    } else if(e.getKeyChar() == '2' ){
      if(weapons.get(1) != null){
        spawner.setCurrentWeapon(1);
      }
    } else if(e.getKeyChar() == '3' ){
      if(weapons.get(2) != null){
        spawner.setCurrentWeapon(2);
      }
    } else if(e.getKeyChar() == '4' ){
      for(int i=0; i < consumables.size(); i++){
        if(consumables.get(i) instanceof HealthPotion){
          if(waylon.getCurrentHealth() != waylon.getHealth()){
            if(waylon.getCurrentHealth() + ((HealthPotion)consumables.get(i)).getHealthGain() > waylon.getHealth()){
              waylon.setCurrentHealth(waylon.getHealth());
              consumables.remove(i);
              while(found == false){
                if(inventory.get(counter) instanceof HealthPotion){
                  inventory.remove(counter);
                  found = true;
                }
                counter++;
              }
              found = false;
              counter = 0;
            }else if(waylon.getCurrentHealth() < waylon.getHealth()){
              waylon.setCurrentHealth(waylon.getCurrentHealth() + ((HealthPotion)consumables.get(i)).getHealthGain());
              consumables.remove(i);
              while(found == false){
                if(inventory.get(counter) instanceof HealthPotion){
                  inventory.remove(counter);
                  found = true;
                }
                counter++;
              }
              found = false;
              counter = 0;
            }
            
          }
        }
      } 
    } else if(e.getKeyChar() == '5' ){
      for(int i=0; i < consumables.size(); i++){
        if(consumables.get(i) instanceof SpeedPotion){
          waylon.setMoveSpeed(waylon.getMoveSpeed() + 1);
          ((SpeedPotion)consumables.get(i)).setBuffTime(System.currentTimeMillis());
          while(found == false){
            if(inventory.get(counter) instanceof SpeedPotion){
              inventory.remove(counter);
              found = true;
            }
            counter++;
          }
          found = false;
          counter = 0;
          boosted = true;
        }
      }
    }
  }
  
  /**
   * keyReleased
   * checks when interactions when a key is released
   * @param e, Key Event
   */ 
  public void keyReleased(KeyEvent e) {
    if(e.getKeyChar() == 'a' ){   
      waylon.setxDirection(0); 
      left = false;
      
    } else if(e.getKeyChar() == 's' ){
      waylon.setyDirection(0);  
      down = false;
      
    } else if(e.getKeyChar() == 'd' ){
      waylon.setxDirection(0);  
      right = false;
      
    } else if(e.getKeyChar() == 'w' ){
      waylon.setyDirection(0);
      up = false;
    } 
  }
  
  /**
   * movement
   * checks if the player is currently moving
   * @return boolean, true if player is moving, else false
   */ 
  public boolean movement() {
    if (left || down || right || up) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * getBoosted
   * checks if the player is currently buffed
   * @return boolean, true if buffed, else false
   */ 
  public boolean getBoosted(){
    return boosted;
  }
  
  /**
   * setBoosted
   * sets the player boosted status
   * @param boosted, boolean representing if the player is boosted
   */ 
  public void setBoosted(boolean boosted){
    this.boosted = boosted;
  }
  
} //end of keyboard listener