
package agame;

import java.io.*;

public class EditorWorld {
    
    // Hold tile locations in world
    public int[] wx;
    public int[] wy;
    public int[] ex,ey;
    final public static int maxEnemies = 30;  // no more than 30 enemies are alloweed in a single level
    public int worldLength; //  ----> Measured in multipiles of 40 
    
    
    
    
    public void setWorldLength(int length){
        
        worldLength = length;
        wx = new int[worldLength];
        wy = new int[worldLength];
        ex = new int[maxEnemies];
        ey = new int[maxEnemies];
        
        
    }
    
    public void save(String filename, int numTiles, int totalEnemies) throws FileNotFoundException{
        
        PrintWriter writer;
        File folder;
        folder = new File("src/World/" + filename);
        folder.mkdir();                           // Create a Folder to store player created world
        writer = new PrintWriter(folder.getAbsoluteFile().toString() + "/" + filename+".txt");  // save tile locations in a file of their choice
        
        
        
        
        
        
        for (int cnt = 0; cnt < numTiles; cnt++){
            
            writer.println(wx[cnt]);
            writer.println(wy[cnt]);
            
        }
        
        writer.close();
        
        writer = new PrintWriter(folder.getAbsoluteFile().toString()+ "/" + "enemy"+".txt");  // writer enemies to the same folder created by the player
        for(int cnt =0;cnt<totalEnemies ; cnt++){
            
             writer.println(ex[cnt]);
            writer.println(ey[cnt]);
            
            
            
        }
        
        
        writer.close();
        
        
        
        
    }
    
    
   public void orderWorld(int numTiles){
       int loc; 
       loc = 0;
       int[] tempx = new int[numTiles];
       int[] tempy = new int[numTiles];
       
       // Sort world from top --> down
      for(int x = 0 ; x < numTiles * 10; x ++){
          
          for(int y = 0; y < 15; y++){
              
               for(int cnt = 0; cnt < numTiles; cnt++){
                   
                   
                   if(wx[cnt] == x * 40 && wy[cnt] == y * 40){
                       tempx[loc] = x * 40;
                       tempy[loc] = y * 40;
                       loc++;
                       
                   }
                   
                   
                   
               }
              
          }

      }
      
      
      
      // ReWrite ordered world to original world array
          for(int cnt = 0; cnt  < numTiles; cnt++){
              wx[cnt] = tempx[cnt];
              wy[cnt] = tempy[cnt];
              
              
          }
           
       
       
   }
    
   
   
   
}
