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
import java.io.File;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * [ScoresFrame.java]
 * Presents high scores
 * Contains bubble sort algorithm
 * @author Derek Chen
 */ 
class ScoresFrame extends JFrame { 
  
  JFrame thisFrame;
  private SingleLinkedList<String> scores;
  private ScoreScreen scoreScreen;
  private Image floor;
  
  //ScoresFrame constructor
  ScoresFrame() { 
    super("Start Screen");
    this.thisFrame = this; //lol  

    //configure the window
    this.setSize(1000,700);    
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setResizable (false);
    
    scores = new SingleLinkedList<String>();
    
    //Create a Panel for stuff
    JButton backButton = new JButton("BACK");
    backButton.setBounds(67,550,200,50); //method found from http://www.java2s.com/Code/Java/Swing-JFC/LayingOutComponentsUsingAbsoluteCoordinates.htm
    backButton.setBackground(Color.WHITE);
    backButton.setForeground(Color.BLACK);
    backButton.addActionListener(new BackButtonListener(this));
    
    this.add(backButton);
    
    //Loading previous scores from textfile
    try{
      File highscores = new File("highscores.txt");
      Scanner reader = new Scanner(highscores);
      while(reader.hasNext()){
        scores.add(reader.nextLine());
      } 
      reader.close();
    }catch(Exception e){
      System.out.println("The data could not be loaded...");
    }
    
    scoreScreen = new ScoreScreen();
    this.add(new ScoreScreen());
    
    this.setVisible(true); 
    this.requestFocusInWindow();
  }
  
 /**
  * [ScoreScreen.java]
  * Displays all graphics for scores frame
  * @author Derek Chen
  */ 
  private class ScoreScreen extends JPanel {
    public void paintComponent(Graphics g) {   
      super.paintComponent(g); //required
      int level;
      floor = Toolkit.getDefaultToolkit().getImage("scoreScreen.png");
      g.drawImage(floor, 0, 0, this);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
      g.setColor(Color.WHITE);
      int[] sorted = bubbleSort(scores);
      for(int i=0; i < sorted.length;  i++){
        level = sorted[i];
        g.drawString(""+ (i+1) , 375, 170 + (i * 35));
        g.drawString("" + level, 595, 170 + (i * 35));
      }
    }
  }
  
  /** 
   * bubbleSort
   * Sorts a random array on integers using bubble sort
   * @param the unsorted integer array
   * @return the sorted integer array
   */
  public static int[] bubbleSort(SingleLinkedList<String> scores) {    
    int temp;
    boolean swapped;
    String str= "";
    int level;
    int[] numbers = new int[scores.size()-1];
    for(int i = 0; i < scores.size()-1; i++){
      str = scores.get(i).substring(scores.get(i).lastIndexOf(" ")+1);
      level = Integer.parseInt(str);
      numbers[i] = level;
      do {
        swapped = false;
        for (int j = 0; j < (numbers.length - 1); j++) {
          if (numbers[j] < numbers[j + 1]) {
            temp = numbers[j];
            numbers[j] = numbers[j + 1];
            numbers[j + 1] = temp;
            swapped = true;
          }
        }
      } while (swapped);
      
    }
    return numbers; 
  }
}




