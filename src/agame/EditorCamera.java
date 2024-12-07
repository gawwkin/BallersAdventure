
package agame;


public class EditorCamera {
    
    public int[] px;
    public int[] py;
    public int totalTile, totalEnemy;
    public int dS;
    public int[] ex,ey;
    
    
    
    public void setUp(int worldLength){
        totalTile = 0; totalEnemy =0;
        dS = 0;
        px = new int[worldLength];
        py = new int[worldLength];
        ex = new int[EditorWorld.maxEnemies];
        ey = new int[EditorWorld.maxEnemies];
       
        
        
    }
    
    
    public void setTile(int mx, int my, EditorWorld world){
        
        int lx, ly;
        lx = calcLoc(mx);
        ly = calcLoc(my);
        if(LevelEditor.activeImage == LevelEditor.grass){
            px[totalTile] = (lx * 40);
            py[totalTile] = ly * 40;
            world.wx[totalTile] = (lx * 40) + dS;
             world.wy[totalTile] = ly * 40;
            totalTile++;
        }
        else if(LevelEditor.activeImage == LevelEditor.boog){
            ex[totalEnemy] = (lx * 40);
            ey[totalEnemy] = ly * 40;
            world.ex[totalEnemy] = (lx * 40) + dS;
             world.ey[totalEnemy] = ly * 40;
            totalEnemy++;
        }
        
    }
    
    public void shiftCamera(){
        
        
        
        for(int cnt =0; cnt < totalTile; cnt++){
            
            px[cnt] = px[cnt] - 800;
            
        }
        for(int cnt = 0; cnt<totalEnemy; cnt++){
            ex[cnt] = ex[cnt] - 800;
            
        }
        dS += 800;
        
        
    }
    
    
    public int calcLoc(int mL){
        
        return mL / 40;
    }
    
    public int getTotalTiles(){
        return totalTile;
    }
   
    public int getTotalEnemies(){
        return totalEnemy;
    }
}
