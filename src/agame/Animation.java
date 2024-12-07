
package agame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Animation implements Runnable{
    
    public int numFrames;
    public BufferedImage frame;
    public boolean isComplete;
    public String theAnimation;
    public int currentFrame;
    public Thread anim;
    
    
    public void setFrames(int num){
        numFrames = num;
    }
    
    
    public void setAnimation(String filePath) throws IOException{
        theAnimation = filePath;
        currentFrame = 1;
        frame = ImageIO.read(new File(theAnimation + currentFrame + ".png"));
        isComplete =  false;
        anim = new Thread(this);
        anim.start();
       
        
    }
    
    
    public void changeFrame() throws IOException{
        
       this.run();
       
        
        
    }
    
    
    
    public boolean isDone(){
        return isComplete;
    }

    @Override
    public void run() {
        if(currentFrame < numFrames){
            currentFrame++;
            try {
                frame = ImageIO.read(new File(theAnimation + currentFrame + ".png"));
            } catch (IOException ex) {
                Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            currentFrame = 1;
            isComplete = true;
            try {
                frame = ImageIO.read(new File(theAnimation +currentFrame+".png"));
            } catch (IOException ex) {
                Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
    
    
    
    
}
