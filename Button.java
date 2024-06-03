
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button extends JButton implements ActionListener{

  private GameController gc;
  private String action;

  public Button(Icon i, GameController gc, String action){
    super(i);
    this.gc = gc;
    this.action = action;
    customizeButton();
  }

  public Button(String text, GameController gc, String action){
    super(text);
    this.gc = gc;
    this.action = action;
    customizeButton();
  }

  private void customizeButton() {
    addActionListener(this);
    setForeground(Color.WHITE);
    setBackground(new Color(0, 122, 204));
    setFont(new Font("Arial", Font.BOLD, 14));
    setFocusPainted(false);
    setBorder(BorderFactory.createRaisedBevelBorder());
    setCursor(new Cursor(Cursor.HAND_CURSOR));
    setToolTipText(action + " Action");
  }

  @Override
  public void actionPerformed(ActionEvent e){
    System.out.println(this.action + " button pressed");
    this.gc.doAction(this.action);
  }
}
