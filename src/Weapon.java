import java.awt.Graphics;

/**
* [Weapon.java]
* Represents a weapon
* superclass to Pistol, SMG and Rifle
* contains abstract draw, move and reload methods
* @author Derek Chen, Waylon Chan
* Derek Chen - Reloading, Shooting Lock
* Waylon Chan - Foundation
*/
abstract class Weapon extends Entity {
  private int xCord, yCord;
  private int damage;
  private int clipSize, currentAmmo;
  private int reloadSpeed;
  private boolean shootingLock; 
  private boolean direction;
  private boolean reloading;
  private long reloadTime;
  
  /**
  * Weapon constructor 
  * @param xCoordinate, the x position of the weapon
   * @param yCoordinate, the y position of the weapon
   * @param damage, the damage value of the weapon
   * @param clipSize, the weapons's max ammo
   * @param reloadSpeed, the time it takes to reload the weapon
   */
  Weapon(int xPosition, int yPosition, int damage, int clipSize, int reloadSpeed) {
    super(xPosition, yPosition);
    this.damage = damage;
    this.clipSize = clipSize;
    this.reloadSpeed = reloadSpeed;
    currentAmmo = clipSize;
  }
  
  
  /**
  * move 
  * Moves weapon with the player
  * @param waylon, the player
  */
  abstract void move(Player waylon);
  
  /**
  * reload 
  * Determines if reloading is possible and then reloads ammo in the gun
  * Prevents shooting while reloading
  */
  abstract void reload();
  
  
  /**
  * getDamage
  * @return int, value of damage
  */
  public int getDamage() {
    return damage;
  }

  
  /**
  * getClip 
  * @return int, value of clip
  */
  public int getClipSize(){
    return clipSize;
  }
  
  /**
  * getCurrentAmmo
  * @return int, value of current ammo
  */
  public int getCurrentAmmo(){
    return currentAmmo;
  }

    /**
  * setCurrentAmmo
  * @param currentAmmo, int value of current ammo
  */
  public void setCurrentAmmo(int currentAmmo){
    this.currentAmmo = currentAmmo;
  }
  

  /**
  * setDamage 
  * @param damage, int value of damage
  */
  public void setDamage(int damage) {
    this.damage = damage;
  }
  
  /**
  /**
  * setClip 
  * @param clip, int value of clip
  */
  public void setClipSize(int clipSize){
    this.clipSize = clipSize;
  }
  
  /**
  * setShootingLock 
  * @param shootingLock, boolean value of shootingLock
  */
  public void setShootingLock(boolean shootingLock){
    this.shootingLock = shootingLock;
  }
  
  /**
  * getShootingLock 
  * @return boolean, value of shootingLock
  */
  public boolean getShootingLock(){
    return shootingLock;
  }
  
  /**
  * setDirection 
  * @param direction, boolean value of direction
  */
  public void setDirection(boolean direction) {
    this.direction = direction;
  }
  
  /**
  * getDirection 
  * @return boolean, value of direction
  */
  public boolean getDirection() {
    return direction;
  }
  
  /**
  * setReloading 
  * @param reloading, boolean value of reloading
  */
  public void setReloading(boolean reloading){
    this.reloading = reloading;
  }
  
  /**
  * getReloading 
  * @return boolean, value of reloading
  */
  public boolean getReloading(){
    return reloading;
  }
  
  /**
  * getReloadSpeed 
  * @return int, value of reloadSpeed
  */
  public int getReloadSpeed(){
    return reloadSpeed;
  }
  
  /**
   * setReloadTime
   * @param reloadTime, long value of instance in time reloading began
   */ 
  public void setReloadTime(long reloadTime){
    this.reloadTime = reloadTime;
  }
  
  /**
   * getReloadTime
   * @return long, value of instance in time reloading began
   */ 
  public long getReloadTime(){
    return reloadTime;
  }
}