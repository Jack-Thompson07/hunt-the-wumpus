import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ArrowButton extends JButton implements ActionListener{

  private GameController gc;
  private int side;


  public ArrowButton(int side, GameController gc){
    super("" + side);
    this.gc = gc;
    this.side = side;
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e){
    System.out.println("I WAS PRESSED");
    System.out.println(this.side + " button pressed");
    this.gc.doAction(this.side + "arrow shot");
  }
}
