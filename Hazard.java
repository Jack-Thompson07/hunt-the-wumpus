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
public class Hazard{
   //////////////////
   // Properties
   //////////////////
   Random spawn;
   Player player;
   int    damage; 
   int    position;
   String name;
   Map map;
   
   /////////////
   // Constructors
   /////////////

   /*Pit */ public Hazard(String hazardName, int roomPit){
        this.name = hazardName;
        this.position = roomPit;
    }
   ///////////
   // Methods
   ////////////
   public void carryToRandom(Random newRoom, ){
      /*
      System.out.println("You ran into a bat");
      int carried = newRoom.nextInt();
      Player position = carried;
      
       */
      
      
   
 

   public boolean die(){
      // returns true or false if the player dies
   }
   public String returnName(){
      return this.name;
   }
   public int returnPosition(){
      return this.position;
   }
   
   



}