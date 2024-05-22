import java.util.Random;

import java.util.Scanner;
public class Hazard{
   //////////////////
   // Properties
   //////////////////
   int yCor;
   int xCor;
   String type;
   Cave cave;
   private int[] position;

   // getHazard. Have hazard type accessor method. 
   //////////////////
   // Constructors
   //////////////////
   public Hazard(String type, Cave cave){
 
      this.type = type;
      this.cave = cave;
      if(type.equals("Bat")){
         this.type = "B";
      } else if(type.equals("Pit")){
         this.type = "P";
      }
   }
   
   ///////////////////
   // Methods
   ///////////////////
   public String getHazard(){
      return this.type;
   }
  
   public void batCarry(Random random, Player player){
         System.out.println("~ ~ ~ ~ ~ YOU RAN INTO A BAT ~ ~ ~ ~ ~");
         Random ran = new Random();

         int newX = ran.nextInt(5);
         int newY = ran.nextInt(4);

        player.move(new int[]{newX,newY});


         System.out.println("New Cave: Position Updated");
   }

     

   /// Wait for Laksh
   public void pitTrivia(){
             System.out.println("~ ~ ~ ~ ~ YOU FELL INTO A PIT ~ ~ ~ ~ ~");
             System.out.println("Answer 3 of 3 Trivia Questions To Survive!");

   }

   public int[] getPosition() {
        return this.position;
    }


}