/**
 * [Spawner.java]
 * Spawns most game objects
 * @author Derek Chen
 */ 
class Spawner{
  
  private static int currentWeapon;
  private static boolean opened;
  private static SingleLinkedList<Character> characters = new SingleLinkedList<Character>();
  private static SingleLinkedList<Weapon> weapons = new SingleLinkedList<Weapon>();
  private static SingleLinkedList<Projectile> projectiles = new SingleLinkedList<Projectile>();
  private static SingleLinkedList<Chest> chests = new SingleLinkedList<Chest>();
  private static SingleLinkedList<Entity> inventory = new SingleLinkedList<Entity>();
  private static SingleLinkedList<Consumable> drops = new SingleLinkedList<Consumable>();
  
  /**
   * Spawner constructor
   * @param characters, a list containing all characters
   * @param weapons, a list containing all of the player's weapons
   * @param projectiles, a list containing all projectiles on screen
   * @param chests, a list containing all chests on screen
   * @param inventory, a list containing everything the player owns
   * @ param drops, a list containing all dropped items on the floor
   */ 
  Spawner(SingleLinkedList<Character> characters, SingleLinkedList<Weapon> weapons, SingleLinkedList<Projectile> projectiles, 
                  SingleLinkedList<Chest> chests, SingleLinkedList<Entity> inventory, SingleLinkedList<Consumable> drops){
    this.characters = characters;
    this.weapons = weapons;
    this.projectiles = projectiles;
    this.chests = chests;
    this.inventory = inventory;
    this.drops = drops;
  }
  
  /**
   * spawnHuman 
   * adds the player to characters list
   * @param xPosition, int value for xCoordinate
   * @param yPosition, int value for yCoordinate
   * @param health, int value for health
   * @param damage, int value for damage
   * @param speed, int value for speed
   */
  public static void spawnPlayer(int xPosition, int yPosition, int health, int moveSpeed) {
    Player player = new Player(xPosition, yPosition, health, moveSpeed);
    characters.add(player);
  }
  
  /**
   * spawnZombie 
   * adds a zombie to characters list
   * @param xPosition, int value for xCoordinate
   * @param yPosition, int value for yCoordinate
   * @param health, int value for health
   * @param moveSpeed, int value for speed
   * @param damage, int value for damage
   */
  public static void spawnZombie(int xPosition, int yPosition, int health, int moveSpeed, int damage) {
    Zombie zombie = new Zombie(xPosition, yPosition, health, moveSpeed, damage);
    characters.add(zombie);
  }
  
  /**
   * spawnGoblin
   * adds a goblin to characters list
   * @param xPosition, int value for xCoordinate
   * @param yPosition, int value for yCoordinate
   * @param health, int value for health
   * @param moveSpeed, int value for speed
   * @param damage, int value for damage
   */
  public static void spawnGoblin(int xPosition, int yPosition, int health, int moveSpeed, int damage) {
    Goblin goblin = new Goblin(xPosition, yPosition, health, moveSpeed, damage);
    characters.add(goblin);
  }
  
  /**
   * spawnWizard
   * ads a zombie to characters list
   * @param xPosition, int value for xCoordinate
   * @param yPosition, int value for yCoordinate
   * @param health, int value for health
   * @param moveSpeed, int value for speed
   * @param damage, int value for damage
   */
  public static void spawnWizard(int xPosition, int yPosition, int health, int moveSpeed, int damage) {
    Wizard wizard = new Wizard(xPosition, yPosition, health, moveSpeed, damage);
    characters.add(wizard);
  }
  
  /**
   * fireBullet
   * adds a bullet to projectiles list
   * @param waylon, the player
   */
  public static void fireBullet(Player waylon) {
    if ((weapons.get(currentWeapon).getShootingLock() == false) && (weapons.get(currentWeapon).getCurrentAmmo() > 0)){
      weapons.get(currentWeapon).setCurrentAmmo(weapons.get(currentWeapon).getCurrentAmmo()-1);
      Bullet bullet = new Bullet(waylon.getxCentre(), waylon.getyCentre(), 20);
      projectiles.add(bullet);
    }
  }
  
  /**
   * shootFireball
   * adds a fireball to projectiles list
   * @param wizard, the wizard casting the fireball
   * @param waylon, the player
   */
  public static void shootFireball(Wizard wizard, Player waylon){
        Fireball fireball = new Fireball(wizard.getxCentre(), wizard.getyCentre()-5, 20);
        projectiles.add(fireball);
  }
  
  /**
   * spawnHealthPotion
   * adds a health pot to drops list
   * @param monster, the monster that dropped the item
   */
  public static void spawnHealthPotion(Monster monster){
    HealthPotion healthPot = new HealthPotion(monster.getxCentre(), monster.getyCentre(), 30);
    drops.add(healthPot);
  }
  
  /**
   * spawnSpeedPotion
   * adds a speed potion to drops list
   * @param monster, the monster that dropped the item
   */
  public static void spawnSpeedPotion(Monster monster){
    SpeedPotion speedPot = new SpeedPotion(monster.getxCentre(), monster.getyCentre(), 3000);
    drops.add(speedPot);
  }
  
  /**
   * addPistol
   * adds the pistol to the weapons list and the player's inventory(list)
   * @param waylon, the player
   */ 
  public static void addPistol(Player waylon){
    Pistol pistol = new Pistol(waylon.getxCentre(), waylon.getyCentre(), 15, 30, 1000);
    weapons.add(pistol);
    Pistol pistolInv = new Pistol(655,885, 0, 0, 0);
    inventory.add(pistolInv);
  }
  
  /**
   * spawnChest
   * creates a chest at the back of the room with a randomized item
   * @param waylon, the player
   * @param random, a random integer
   */ 
  public static void spawnChest(Player waylon, int random){
    if(weapons.get(1) == null){
      SMG uzi = new SMG(waylon.getxCentre(), waylon.getyCentre(), 12, 40, 1500);
      Chest<SMG> chest = new Chest<SMG>(1650, 475, uzi);
      chests.add(chest);
      opened = false;
      return;
    }else if (weapons.get(2) == null){
      Rifle rifle = new Rifle(waylon.getxCentre(), waylon.getyCentre(), 25, 50, 2000);
      Chest<Rifle> chest = new Chest<Rifle>(1650, 475, rifle);
      chests.add(chest);
      opened = false;
    }else if(random == 0){
      HealthPotion healthPot = new HealthPotion(0, 0, 30);
      Chest<HealthPotion> chest = new Chest<HealthPotion>(1650, 475, healthPot);
      chests.add(chest);
      opened = false;
    }else if(random == 1){
      SpeedPotion speedPotion = new SpeedPotion(0, 0, 10000);
      Chest<SpeedPotion> chest = new Chest<SpeedPotion>(1650, 475, speedPotion);
      chests.add(chest);
      opened = false;
    } 
  }
  
  /**
   * getCurrentWeapon
   * @return int, the value of the weapon currently equipped
   */ 
  public static int getCurrentWeapon(){
    return currentWeapon;
  }
  
  /**
   * setCurrentWeapon
   * @param currentWeapon, the value of the weapon currently equipped
   */ 
  public void setCurrentWeapon(int currentWeapon){
    this.currentWeapon = currentWeapon;
  }
  
  /**
   * setOpened
   * @param opened, boolean representing if a chest has been opened
   */ 
  public void setOpened(boolean opened){
    this.opened = opened;
  }
  
  /**
   * getOpened
   * @return boolean, true if chest has been opened else false
   */ 
  public boolean getOpened(){
    return opened;
  }
  
}

