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
        this.player = player;
    }
   ///////////
   // Methods
   ////////////
   public void carryToRandom(Random newRoom ){
      int currentPosition = this.player.getPosition();
      int batCarry = newRoom.nextInt(30);
      this.player.updatePosition(currentPosition, batCarry);

   }
      
      
   
 

   public boolean die(){
      // returns true or false if the pla
      return false;
   }
   public String returnName(){
      return this.name;
   }
   public int returnPosition(){
      return this.position;
   }
   
   



}