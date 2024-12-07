
package agame;

import java.io.*;
import java.util.Scanner;

public class World {
    
    public static int[] wx, wy;
    public static int worldLength, numEnemies;
    public String filePath;
    public Enemy[] enemies;
    public File world;
    public File Enemy;
   
    public void setFiles(File world, File enemies){
        this.world = world;
        this.Enemy = enemies;
        
    }
    
    public void calcLength() throws FileNotFoundException{
        numEnemies = 0;
        int length;
        File theFile = world;
        Scanner theFileSc = new Scanner(theFile);
        length = 0;
        
        while(theFileSc.hasNext()){
            
            length++;
            theFileSc.nextLine();
        }
        worldLength = length/2;
        
        wx = new int[worldLength];
        wy = new int[worldLength];
        
        
        theFileSc.close();
        theFile = null;
        
        
        
        theFileSc = new Scanner(Enemy);
        
        while(theFileSc.hasNext()){
            numEnemies++;
            theFileSc.nextLine();
            
            
            
        }
        
        numEnemies = numEnemies /2;
        
      this.enemies = new Enemy[numEnemies];
        
        theFileSc.close();
        
    }
    
    
    public void load() throws FileNotFoundException{
        
         File theFile = world;
        Scanner theFileSc = new Scanner(theFile);
        int cnt = 0;
        // Load Tile Positions
        while(theFileSc.hasNext()){
            wx[cnt] = theFileSc.nextInt();
            wy[cnt] = theFileSc.nextInt();
            cnt++;
            
            
        }
        theFileSc.close();
        theFileSc =  new Scanner(Enemy);
        cnt = 0;
        // Load Enemies
        while(theFileSc.hasNext()){
            
            enemies[cnt] = new Enemy();
            enemies[cnt].ex = theFileSc.nextInt();
            enemies[cnt].ey = theFileSc.nextInt();
            cnt++;
            
        }
    }
    
    
}
