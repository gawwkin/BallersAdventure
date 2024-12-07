
package agame;

public class Flag {
    
    public boolean isTouch;
    public int fx, fy;
    
    
    public Flag(){
        isTouch = false;
    }
    
    public void isTouch(Player player){
        
        
        if(player.px + 10 >= this.fx && player.px + 10 <= this.fx + 40 && player.isTop){
            
            isTouch = true;
        }
        
        
    }
    
    
    public void setFlagLocation(int flagX, int flagY){
        fx = flagX;
        fy = flagY;
        
        
    }
    
    

}
