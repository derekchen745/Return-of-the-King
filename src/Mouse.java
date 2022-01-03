// ----------- class for the keyboard listener - This detects mouse movement & clicks and runs the corresponding methods 

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;

/**
 * [Mouse.java]
 * Controls all mouse interactions
 * @author Derek Chen, Waylon Chan
 * Derek Chen - Bullets
 * Waylon Chen - Automatic Weapons
 */ 
class Mouse implements MouseListener {
  private Player waylon;
  private Spawner spawner;
  private boolean holding;
  private int xCursor, yCursor;
  
  /**
   * Mouse constructor
   * @param waylon, the player
   * @param spawner, a spawner that creates all game objects
   */ 
  Mouse(Player waylon, Spawner spawner){
    this.waylon = waylon;
    this.spawner = spawner;
  }
  
  public void mouseClicked(MouseEvent e) {}
  
  /**
   * mousePressed
   * Checks all interactions when mouse is pressed
   * @param e, MouseEvent
   */
  public void mousePressed(MouseEvent e) {
    xCursor = e.getX();
    yCursor = e.getY();
    if ((spawner.getCurrentWeapon() == 0) || (spawner.getCurrentWeapon() == 2)) { //pistol
      spawner.fireBullet(waylon);
    } else if (spawner.getCurrentWeapon() == 1){
      holding = true;
    }
  }
  
  /**
   * mouseReleased
   * Checks all interactions when mouse is released
   * @param e, MouseEvent
   */ 
  public void mouseReleased(MouseEvent e) {
    holding = false;
  }
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  
  /**
   * trackX 
   * ALWAYS tracks Y position
   * @return int value of current X position of mouse adjusted for camera
   */
  public int trackX() {
    return (int)MouseInfo.getPointerInfo().getLocation().getX();
  }
  
  /**
   * trackY 
   * ALWAYS tracks X position
   * @return int value of current Y position of mouse adjusted for camera
   */
  public int trackY() {
    return (int)MouseInfo.getPointerInfo().getLocation().getY();
  }
  
  /**
   * getxCursor
   * @return int, gets the x coordinate of the mouse click
   */ 
  public int getxCursor(){
    return xCursor;
  }
  
  /**
   * getyCursor
   * @return int, gets the y coordinate of the mouse click
   */ 
  public int getyCursor(){
    return yCursor;
  }
  
  /**
   * resetxCursor
   * resets x cursor to 0
   */ 
  public void resetxCursor(){
    xCursor = 0;
  }
  
  /**
   * resetyCursor
   * resets y cursor to 0
   */
  public void resetyCursor(){
    yCursor = 0;
  }
  
  /**
   * getHolding
   * @return boolean, true if held, else false
   */ 
  public boolean getHolding(){
    return holding;
  }
} //end of mouselistener

