 //Buttons that were used in multiple frames were included as their own class

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * [StartButtonListener.java]
 * Begins the game
 * @author Derek Chen
 */ 
class StartButtonListener implements ActionListener {  
   JFrame parentFrame;
   StartButtonListener(JFrame parent) { 
     parentFrame = parent;
   }
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Starting new Game");
      parentFrame.dispose();
      new GameFrame(); //create a new frame after removing the current one
    }
 }