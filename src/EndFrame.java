/** 
 * this template can be used for a start menu
 * for your final project
**/

//Imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * [EndFrame.java]
 * Ending frame of the game
 * @author Derek Chen
 */ 
class EndFrame extends JFrame { 

  JFrame thisFrame;
  private int finalLevel;
  private Image image;
  
  //EndFrame constructor
  EndFrame() { 
    super("End Screen");
    this.thisFrame = this; //lol 
    this.finalLevel = finalLevel;
    
    //configure the window
    this.setSize(1000,700);    
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setResizable (false);
    
    JButton startButton = new JButton("PLAY AGAIN");
    startButton.setBounds(425,350,150,30); 
    startButton.setBackground(Color.WHITE);
    startButton.setForeground(Color.BLACK);
    startButton.addActionListener(new StartButtonListener(this));
    
    JButton menuButton = new JButton("MENU");
    menuButton.setBounds(425,380,150,30); 
    menuButton.setBackground(Color.WHITE);
    menuButton.setForeground(Color.BLACK);
    menuButton.addActionListener(new MenuButtonListener(this));
    
    JButton exitButton = new JButton("EXIT");
    exitButton.setBounds(425,410,150,30); 
    exitButton.setBackground(Color.WHITE);
    exitButton.setForeground(Color.BLACK);
    exitButton.addActionListener(new MenuButtonListener(this));
    
    
    ImageIcon mainImage = new ImageIcon ("endScreen.png"); 
    JLabel mainLabel = new JLabel(mainImage);
    
    this.add(startButton);
    this.add(menuButton);
    this.add(exitButton);
    this.add(mainLabel);
   
    this.setVisible(true);
    
    this.requestFocusInWindow();
  }
  
  
  //These listeners were included as inner classes since they are only utilised in the starting frame
  /**
   * [MenuButtonListener.java]
   * Returns to the starting screen
   * @author Derek Chen
   */ 
  class MenuButtonListener implements ActionListener {  
   JFrame parentFrame;
   MenuButtonListener(JFrame parent) { 
     parentFrame = parent;
   }
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Back to Main Menu");
      parentFrame.dispose();
      new StartingFrameOOP();
    }
 }
}
  
  
  

