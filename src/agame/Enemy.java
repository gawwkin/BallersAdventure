    
package agame;

import static agame.Render.time;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Enemy{
    
    public int ex, ey;
    public boolean isCollide, isTouch, isActive, isLeft, isRight;
    public int loc;
    public double totalStep;
    public double eyV;
    public int es = 1;
    public Thread enemThread;
    public float time;
    
   
    public Enemy(){
        
        
        this.isLeft = true;
        this.isActive =  false;
       this.isCollide = false;
        eyV = 0;
        time = 0;
    }
           
    
    
    
   
    
    
    
    
    
    public void pathTrace(){ 
      
         
         
         if(this.isActive){
             
             
             totalStep+= 0.25;
             
             if(totalStep < 45.0){
                 this.ex -= es;
             }
             else if(totalStep >=45.0 && totalStep < 90.0){
                 this.ex += es;
             }
             else
                 totalStep =0.0;
            
         }
       
               
              
        
        
    }
    
    
    public void setActive(){
        
       if(this.ex <= 800 && this.ex > 0){
           this.isActive = true;
           
       }
        
    }
    
    
    public void place(){
        loc = findTile();
        if(loc != -1){
            
            this.ey = World.wy[loc] - 25;
            time = 0;
        }
        else{
            physics();
        }
    }
    
    
    
    
   public int findTile(){
        int loc;
        int dy;
        loc = 0;
     
        dy= 0;
        while(loc < World.worldLength){
            
            
            // Find Tile location relative to this enemy's x & y coordinate in the world
            if( this.ex + 25  <=(World.wx[loc] + 50) && this.ex + 25 >= World.wx[loc]){
                dy = Math.abs(World.wy[loc] - (this.ey )); // Calculate Delta y from tile and player's position
                if(dy< 50 && (this.ey) < 400 ){
                    break;
                }
                
            }
            
            loc++;
        }
            if(loc == World.worldLength){   // if we check every tile in game and still cannot find a tile then return negative one indicating that no tile was found
                loc= -1;
            }
        
        return loc;
    }

    public boolean isCollide(Player player){
        
        // Is Player touching the top of enemy
        
        if(player.px + 10 >= this.ex && player.px + 10 <= this.ex + 25 && player.py + 24 >= this.ey && !player.isTop && player.py + 25 < this.ey + 25){
            
            this.ex = -50;
            this.ey = 0;
            this.isActive = false;
            player.pyV = 15;
            player.isJump = true;
            player.isTop = false;
            isCollide = true;
            
            
        }
        else{
            isCollide = false;
        }
        return isCollide;
        
    }
    
    
    public void checkTouch(Player player){
        
        
        if(player.px + 10 >= this.ex && player.px + 10 <= this.ex + 25 && player.isTop && player.py + 25 >= this.ey && player.py <= this.ey + 25 ){ // touching the player on its x region
            player.isAlive = false;
        }
        
        
        
    }
    
    public void physics(){
        
        
       
        
        // Calculate Delta S off of starting velocity
            this.ey = (int)(this.ey - ((eyV * time )- (0.5 *7 * Math.pow(time, 2))) );
          
            
        // ReCalculate Player Velocity Based off elapsed time
            eyV = eyV - (7 * time);
            
        
          
            //System.out.println("Current Y - Postion : " + (player.py + 25));
//            System.out.println("Current Velcoity: " + (int) player.pyV);
//            
         // Increment time 
         time+= 0.0043;
         
        
    }
    
    
}
