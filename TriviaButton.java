import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.*;

public class TriviaButton extends JButton implements ActionListener{

  private GameController gc;
  private String action;

  public TriviaButton(String text, GameController gc){
    super(text);
    this.action = text;
    this.gc = gc;
    this.action = action;
    addActionListener( this );
  }

  @Override
  public void actionPerformed(ActionEvent e){
    System.out.println(this.action + " button pressed");
    this.gc.answerQuestion(this.action);
  }
}