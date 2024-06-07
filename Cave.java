import java.io.File;
import java.util.Scanner;

public class Cave {


    private final int HEIGHT = 5;
    private final int WIDTH = 6;
    private final File file = new File("CaveData.csv");
    Scanner reader;


    private int[][][] rooms;
    // stores the locaitons of all rooms with the location of the tunnel is on the room(0-5; 0 is top; clockwise)

    public Cave(){
        this.rooms = new int[HEIGHT][WIDTH][3];
        build();
    }

    public void build(){
        String[] data = readFile();
        for(int row = 0; row < HEIGHT; row ++){
            for(int col = 0; col < WIDTH; col ++){
                this.rooms[row][col] = new int[]{-1,-1,-1};
                for(int i = 0; i <  data[row * WIDTH + col].split(",").length; i ++){
                    this.rooms[row][col][i] = Integer.parseInt(data[row * WIDTH + col].split(",")[i]);
                }
            }
        }
    }

    public String[] readFile(){
        try{
            this.reader = new Scanner(this.file);
        }
        catch(Exception e){
            System.out.println("<FILE NOT FOUND>");
        }
        for(int i = 0; i < (int)(Math.random() * 3); i ++){
            this.reader.nextLine();
        }
        return this.reader.nextLine().split(";");
    }

    public int[] getPosOfTunnel(int[] cord, int side){
        int x = cord[1];
        int y = cord[0];


        int newY = y;
        int newX = x;

        if(side == 0){
            if(y == 0)
                newY = HEIGHT - 1;
            else
                 newY --;
        }

        else if(side == 1){
            if(x == WIDTH - 1)
                newX = 0; 
            else
                    newX++;
            if(x % 2 == 0){
                if(y == 0)
                    newY = HEIGHT - 1;
                else
                    newY--;
            }
        }

        else if(side == 2){
            if(x == WIDTH - 1)
                newX = 0; 
            else
                newX ++;
            if(x % 2 == 1){
                if(y == HEIGHT - 1)
                    newY = 0;
                else
                    newY ++;
            }
        }
        if(side == 3){
            if(y == this.HEIGHT - 1)
                newY = 0;
            else
                newY ++;
        }
        if(side == 4){
            if(x == 0)
                newX = this.WIDTH - 1;
            else
                newX --;
            if(x % 2 == 1){
                if(y == HEIGHT - 1)
                    newY = 0;
                else
                    newY ++;
            }
        }
        if(side == 5){
            if(x == 0)
                newX = this.WIDTH - 1;
            else
                newX --;
            if(x % 2 == 0){
                if(y == 0)
                    newY = HEIGHT - 1;
                else
                    newY --;
            }
        } 
        
        return new int[]{newY, newX};
    }

    public int[] getTunnels(int[] cords){
        return this.rooms[cords[0]][cords[1]];
    }

    public int convertToIndex(int[] cords){
        return WIDTH * cords[0] + cords[1] + 1; 
    }
}