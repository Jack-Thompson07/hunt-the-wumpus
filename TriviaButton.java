import javax.swing.JButton;
import java.awt.event.*;

public class TriviaButton extends JButton implements ActionListener{
  ////////////////////////
  // Properties
  ////////////////////////
  private GameController gc;
  private String action;

  ////////////////////////
  // Constructor
  ////////////////////////
  public TriviaButton(String text, GameController gc){
    super(text);
    this.action = text;
    this.gc = gc;
    this.action = action;
    addActionListener( this );
  }

  ////////////////////////
  // Methods
  ////////////////////////
  @Override
  public void actionPerformed(ActionEvent e){
    System.out.println(this.action + " button pressed");
    this.gc.answerQuestion(this.action);
  }
}