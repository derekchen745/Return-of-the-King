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
import java.awt.Graphics;

/**
 * [StartingFrameOOP.java]
 * The menu frame
 * Contains Play, Rules, High Scores, Exit buttons
 * @author Derek Chen
 */ 
class StartingFrameOOP extends JFrame { 

  JFrame thisFrame;
  
  //StartingFrameOOP constructor
  StartingFrameOOP() { 
    super("Start Screen");
    this.thisFrame = this; //lol  
    
    //configure the window
    this.setSize(1000,700);    
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setResizable (false);
    
    JButton startButton = new JButton("PLAY");
    startButton.setBounds(425,350,150,30); 
    startButton.setBackground(Color.WHITE);
    startButton.setForeground(Color.BLACK);
    startButton.addActionListener(new StartButtonListener(this));
    
    JButton rulesButton = new JButton("RULES");
    rulesButton.setBounds(425,380,150,30); 
    rulesButton.setBackground(Color.WHITE);
    rulesButton.setForeground(Color.BLACK);
    rulesButton.addActionListener(new RulesButtonListener(this));
    
    JButton scoresButton = new JButton("HIGH SCORES");
    scoresButton.setBounds(425,410,150,30);
    scoresButton.setBackground(Color.WHITE);
    scoresButton.setForeground(Color.BLACK);
    scoresButton.addActionListener(new ScoresButtonListener(this));
    
    JButton exitButton = new JButton("EXIT");
    exitButton.setBounds(425,440,150,30);
    exitButton.setBackground(Color.WHITE);
    exitButton.setForeground(Color.BLACK);
    exitButton.addActionListener(new ExitButtonListener(this));

    ImageIcon mainImage = new ImageIcon ("mainScreen.png"); 
    JLabel mainLabel = new JLabel(mainImage);
    
    this.add(startButton);
    this.add(rulesButton);
    this.add(scoresButton);
    this.add(exitButton);
    this.add(mainLabel);

    this.setVisible(true);
    this.requestFocusInWindow();
  }
  
  //These listeners were included as inner classes since they are only utilised in the starting frame
  
  /**
   * [RulesButtonListener.java]
   * Navigates to the rules frame
   * @author Derek Chen
   */ 
  class RulesButtonListener implements ActionListener {  
   JFrame parentFrame;
   RulesButtonListener(JFrame parent) { 
     parentFrame = parent;
   }
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Displaying Rules");
      parentFrame.dispose();
      new RulesFrame(); //create a new frame after removing the current one
    }
 }
  
  /**
   * [ScoresButtonListener.java]
   * Navigates to the scores frame
   * @author Derek Chen
   */ 
  class ScoresButtonListener implements ActionListener {  
   JFrame parentFrame;
   ScoresButtonListener(JFrame parent) { 
     parentFrame = parent;
   }
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Displaying High Scores");
      parentFrame.dispose();
      new ScoresFrame(); //create a new frame after removing the current one
    }
 }
  

  //Main method starts this application
  public static void main(String[] args) { 
    new StartingFrameOOP();
  }
  
}