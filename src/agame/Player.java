
package agame;


public class Player {
    
    public int px, py, ps;
    public int loc;
    public boolean isJump;
    public boolean isTouch;
    public boolean isAlive, isTop, isBottom;
    public short dir;
    final public int maxXV = 30;
    public World theWorld;
    double pyV,pxV;
    
    public Player(){
        isAlive = true;
        isJump  = false;
        loc = -1;
        dir = 1;
        isTouch = false;
       pyV = 0;
       pxV = 0;
        px = 0;
        py = 320;
        ps =5; // player speed
        
        
    }
    
    public void setWorld(World world){
        theWorld = world;
    }
    
    public void collision(){
       
       // Check collisions
        if(this.loc != -1){
            
            
             
            
            // Dealing With the players y - and collisions;
            if(this.py + 25 < theWorld.wy[this.loc] - 1){  // if bottom of player is less than top of tile
                
                this.isTop = false;
                
            }
           
            
            else if (this.py - 1 <= theWorld.wy[this.loc] + 40 && this.py > theWorld.wy[this.loc]  +30){    // if top of player is touching bottom of tile
                
                
               // System.out.println("This statement (2) !!!!!!!!!!!!!!!!!!");
                this.isTop = false;
                this.pyV = -10;
                this.isJump = true;
                this.py = theWorld.wy[this.loc] + 40;
                
            }
            else if(this.py  + 25 >= theWorld.wy[this.loc]){  // if bottome of player goes through the top of tile indicating that the player should be touching the top of that itle or is inside of the next tile
                 
                    // handles if player is colliding with a block that it is currently near 
                    if(this.px + 25 >= theWorld.wx[this.loc] && this.py + 25 <= theWorld.wy[this.loc] + 42 && this.py + 24 > theWorld.wy[this.loc] + 2 && this.py + 25 < 405 && (this.pyV >= 0 || this.isTop)){
                        //System.out.println("Touching a wall !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        if(this.dir == 1){
                            this.px -= this.ps;
                        }
                        else{
                            this.px += this.ps;
                        }
                        this.isTouch = true;
                        
                    }
                    // Handles if player is on top of a block as a reasult of gravity or landing on top of tile
                    else{
                            //System.out.println("im your issue :)");
                            this.py = theWorld.wy[this.loc] - 25;
                            this.isTop = true;
                            this.isTouch = false;
                            this.isJump = false;
                            Render.time = 0;
                            this.pyV = 0;
                        
                    }
                    
            }
            
            // Dealing with the players x -  and collision
           
//             
//           System.out.println("Tile being checked : " + this.loc);
//            System.out.println("Is Touching Top : " + this.isTop);
//            System.out.println("Tiles X : " + theWorld.wx[this.loc]);
//            System.out.println("Tiles Y TOP : " + theWorld.wy[this.loc]);
//            System.out.println("Tiles Y Bottom : " + (theWorld.wy[this.loc] + 40));
//            System.out.println("Current Y - Postion : " + (this.py + 25));
//            System.out.println("Current X - Postion : " + (this.px + 25));
//            System.out.println("Current Y - Velocity : " + this.pyV);
            
        }
        else{
            this.isBottom = false;
            this.isJump = true;
            Render.time= 0.2;
            if(this.py >= 500){
                System.out.println("End Game!");
                this.isAlive = false;
            }
        }
            
        
        
        
        
       
   }
    
    public int findTile(){
        int loc;
        int dy;
        loc = 0;
     
        dy= 0;
        while(loc < theWorld.worldLength){
            
            
            // Find Tile location relative to playes x & y coordinate in the world
            if( this.px + 25  <=(theWorld.wx[loc] + 50) && this.px + 25 >= theWorld.wx[loc]){
                dy = Math.abs(theWorld.wy[loc] - (this.py )); // Calculate Delta y from tile and player's position
                if(dy< 40 && (this.py) < 400 ){
                    break;
                }
                
            }
            
            loc++;
        }
            if(loc == theWorld.worldLength){   // if we check every tile in game and still cannot find a tile then return negative one indicating that no tile was found
                loc= -1;
            }
        
        return loc;
    }
    
    
    
    
}
