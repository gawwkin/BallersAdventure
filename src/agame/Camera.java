
package agame;


public class Camera {
    
    
    public boolean isMoving;
    public int location;
    public World world;
    public Player player;
    
    
    public Camera(World world, Player player){
        this.world = world;
        this.player = player;
        
     }
    
   
    public void moveWorld(){
        
        for(int cnt =0; cnt<world.worldLength; cnt++){
            
            if(player.dir == 1)
                world.wx[cnt] = world.wx[cnt] - player.ps;
                
            else 
                world.wx[cnt] = world.wx[cnt] + player.ps;
            
            
        }
        
        for(int cnt =0; cnt < world.enemies.length; cnt++){
            
            if(player.dir == 1 && world.enemies[cnt] != null)
                world.enemies[cnt].ex = world.enemies[cnt].ex - player.ps;
            
        }
       
        
        
        world.endFlag.fx -= player.ps;
        
        
    }
          
    
    
    
    
    
}
