import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.*;

public class Button extends JButton implements ActionListener{

  private GameController gc;
  private String action;
  
  public Button(Icon i, GameController gc, String action){
    super(i);
    this.gc = gc;
    this.action = action;
    addActionListener( this );
  }

  public Button(String text, GameController gc, String action){
    super(text);
    this.gc = gc;
    this.action = action;
    addActionListener( this );
  }

  @Override
  public void actionPerformed(ActionEvent e){
    System.out.println(this.action + " button pressed");
    this.gc.doAction(this.action);
  }
}
