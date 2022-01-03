import java.awt.Graphics;

/**
* [Projectile.java]
* an object that is thrown across the screen
* superclass to Bullet, Fireball
* contains abstract travel method
* @author Derek Chen
*/
abstract class Projectile extends Entity {
  
  private double speed;
  private double angle, xDirection, yDirection;
  private boolean limit = false;
  
  /**
  * Projectile constructor 
  * @param xCoordinate, the x position of the projectile
  * @param yCoordinate, the y position of the projectile
  * @param speed, the speed that the projectile travels at
  */
  Projectile(int xPosition, int yPosition, double speed){
    super(xPosition, yPosition);
    this.speed = speed;
  }
  
  /**
  * travel
  * Calculates angle and position of mouse and fires projectiles to it's position
  * @param waylon, the player
  * @param xCursor, current X location of the cursor
  * @param yCursor, current Y location of the cursor
  */
  abstract void travel(Player waylon, int xCursor, int yCursor);
  
  /**
  * getSpeed 
  * @return double, value of speed
  */
  public double getSpeed() {
    return speed;
  }
  
  /**
  * getxDirection 
  * @return double value of xDirection
  */
  public double getxDirection() {
    return xDirection;
  }

  /**
  * getyCord
  * @return double, value of yDirection
  */
  public double getyDirection() {
    return yDirection;
  }

  /**
  * getAngle
  * @return double, value of angle
  */
  public double getAngle() {
    return angle;
  }

  /**
  * getIsLimit
  * @return boolean, value of limit
  */
  public boolean getLimit() {
    return limit;
  }

  /**
  * setLimit
  * @param limit, boolean value of limit
  */
  public void setLimit(boolean limit) {
    this.limit = limit;
  }

  /**
  * setAngle 
  * @param angle, double value of angle
  */
  public void setAngle(double angle) {
    this.angle = angle;
  }

  /**
  * setSpeed 
  * @param speed, double value of speed
  */
  public void setSpeed(double speed) {
    this.speed = speed;
  }

  /**
  * setxDirection
  * @param xDirection, double value of xDirection
  */
  public void setxDirection(double xDirection) {
    this.xDirection = xDirection;
  }

  /**
  * setyDirection 
  * @param yDirection, double value of yDirection
  */
  public void setyDirection(double yDirection) {
    this.yDirection = yDirection;
  }
}