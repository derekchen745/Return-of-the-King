 //This is a class that is used to detect a button press

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * [ExitButtonListener.java]
 * Ends the game
 * @author Waylon Chan
 */ 
class ExitButtonListener implements ActionListener {  
   JFrame parentFrame;
   ExitButtonListener(JFrame parent) { 
     parentFrame = parent;
   }
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Shutting Down");
      parentFrame.dispose();
    }
 }