import javax.swing.*;
import java.awt.event.*;

public class ArrowButton extends JButton implements ActionListener{

  ////////////////////////
  // Properties
  ////////////////////////

  private GameController gc;
  private int side;

  ////////////////////////
  // Constructor
  ////////////////////////
  public ArrowButton(int side, GameController gc){
    this.side = side;
    System.out.println(side);
    if(this.side == 0){
      System.out.println("Up");
      super.setText("⬆");
    }
    else if(this.side == 1){
      System.out.println("Up Right");
      super.setText("↗");
    }
    else if(this.side == 2){
      System.out.println("Down Right");
      super.setText("↘");
    }
    else if(this.side == 3){
      System.out.println("Down");
      super.setText("⬇");
    }
    else if(this.side == 4){
      System.out.println("Down Left");
      super.setText("↙");
    }
    else{
      System.out.println("Up Left");
      super.setText("↖");
    }

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