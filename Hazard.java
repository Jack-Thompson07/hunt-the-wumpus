import java.util.Random;
// Wumpus
// Bats
// Botomless pit

// use random object
// Bat brings you do a randomized room
// Pit kills you. 
// no restrictions but read specs for hazards. 
// 2 pits 2 bats
// 

import java.util.Random;
public class Hazard{
   //////////////////
   // Properties
   //////////////////
   Random spawn;
   Player player;
   int    damage; 
   int    position;
   String name;
   Cave[][] grid;
   Wumpus wumpus;
   //Map map;
   
   /////////////
   // Constructors
   /////////////


   public Hazard(String hazardName, int roomPit, int cords){
      Random ran = new Random();
      this.position = cords;
      int xCor = ran.nextInt(30);
      int yCor = ran.nextInt(30);

      if(hazardName.equals("Bat")){
         grid[xCor][yCor] = new Cave("B");
      } 
      else if(hazardName.equals("Pit") ){
         grid[xCor][yCor] = new Cave("Pit");
      }
        
    }
   ///////////
   // Methods
   ////////////
   public void carryToRandom(Random newRoom ){
      int batCarry = newRoom.nextInt(30);
     this.position = batCarry;

      

   }

   public void checkPosition(){

   }
   
   public void spawn(){

   }

   public boolean die(Wumpus wumpus){
      if(wumpus.returnPosition() == returnPosition()){
         return false;
      }
   return true;
   }
   public String returnName(){
      return this.name;
   }
   public int returnPosition(){
      return this.position;
   }
   
   




}