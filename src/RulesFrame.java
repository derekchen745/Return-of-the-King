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
import javax.swing.ImageIcon;

/**
 * [RulesFrame.java]
 * the rules page
 * @author Derek Chen
 */ 
class RulesFrame extends JFrame { 

  JFrame thisFrame;
  
  //RulesFrame constructor
  RulesFrame() { 
    super("Instructions Screen");
    this.thisFrame = this; //lol  
    
    //configure the window
    this.setSize(1000,700);    
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setResizable (false);
    
    JButton backButton = new JButton("BACK");
    backButton.setBounds(400,400,200,50); //method found from http://www.java2s.com/Code/Java/Swing-JFC/LayingOutComponentsUsingAbsoluteCoordinates.htm
    backButton.setBackground(Color.WHITE);
    backButton.setForeground(Color.BLACK);
    backButton.addActionListener(new BackButtonListener(this));

    ImageIcon rulesImage = new ImageIcon ("rulesScreen.png"); 
    JLabel rulesLabel = new JLabel(rulesImage);
    
    this.add(backButton);
    this.add(rulesLabel);
    
    this.setVisible(true);
    this.requestFocusInWindow();
  }
}
  
  
  

