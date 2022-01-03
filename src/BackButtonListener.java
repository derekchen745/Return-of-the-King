//Buttons that were used in multiple frames were included as their own class

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * [BackButtonListener.java]
 * Returns to the previous frame
 * @author Waylon Chan
 */ 
class BackButtonListener implements ActionListener {  
  JFrame parentFrame;
  BackButtonListener(JFrame parent) { 
    parentFrame = parent;
  }
  public void actionPerformed(ActionEvent event)  {  
    System.out.println("Back to Menu");
    parentFrame.dispose();
    new StartingFrameOOP(); //create a new frame after removing the current one
  }
}