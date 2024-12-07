
package agame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Render extends JPanel implements Runnable{
public long dT, pT;
public int fps;
public static double time;
final private int sleepTime =15;

private BufferedImage baller;
private BufferedImage background;
private BufferedImage tiles;
private BufferedImage Rballer;
public Player player;
public World theWorld;
public Camera camera;
public Animation backFlip;
public Thread RenderThread;
public BufferedImage boog;
    
    public Render(File world, File enemer) throws InterruptedException, IOException{
        
         
        this.setSize(800, 600);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        
        
        RenderThread = new Thread(this);
         player = new Player();
       
       
      
       theWorld = new World();
       theWorld.setFiles(world, enemer);
       theWorld.calcLength();
       theWorld.load();
       
       camera = new Camera(theWorld, player);
       player.setWorld(theWorld);
       
       RenderThread.start();
       
       
       
       try {
           tiles = ImageIO.read(new File("src/Images/tile.png"));
            baller = ImageIO.read(new File("src/Images/ball.png"));
            Rballer = ImageIO.read(new File("src/Images/ballR.png"));
            background = ImageIO.read(new File("src/Images/background.png"));
             backFlip = new Animation();
            backFlip.setFrames(61);
            backFlip.setAnimation("src/Images/BackFlip/ball");
            boog = ImageIO.read(new File("src/Images/Boog.png"));
            
       } catch(IOException e){
           e.printStackTrace();
       }
       
      
       
       
       
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
         Graphics2D g2 = (Graphics2D) g;  
                     
         
         // Drawing Background and player
         if(baller != null && player.isAlive){
             // Draw Background 
             g2.drawImage(background, 0, 0,this);
        
             
             // Drawing Tiles For Map
             for(int cnt =0; cnt < theWorld.worldLength; cnt++){
                 g2.drawImage(tiles, theWorld.wx[cnt], theWorld.wy[cnt],this);
             }
             
             // Draw the Player
             
             if(player.dir == 1){
                g2.drawImage(baller,player.px, player.py,this);
             }
             else{
                 g2.drawImage(Rballer,player.px, player.py,this);
             }
             
             
             
             // draw enemies rq
             for(int cnt =0; cnt<theWorld.numEnemies; cnt++){
                 
                 if(theWorld.enemies[cnt] != null){
                    theWorld.enemies[cnt].setActive();
                    theWorld.enemies[cnt].place();
                    if(theWorld.enemies[cnt].isActive){
                        theWorld.enemies[cnt].pathTrace();
                        g2.drawImage(boog,theWorld.enemies[cnt].ex, theWorld.enemies[cnt].ey,this);
                        System.out.println("Enemy : " + cnt );
                        System.out.println("Enemies tile : " + theWorld.enemies[cnt].findTile());
                        if(theWorld.enemies[cnt].isCollide(player)){
                            theWorld.enemies[cnt] = null;
                        }
                        else{
                            theWorld.enemies[cnt].checkTouch(player);
                        }
                        
                    }

                    
                 }
             }
             
             
             
         
         }
         else{
             
             g2.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
             g2.setPaint(Color.RED);
             g2.drawString("GAME OVER", 250, 300);
             
             
             
         }
         
          
            
            
        
    }
    
   

    @Override
    public void run() {
   
        dT = 0;
        time = 0.0038;
        // Render Loop ( Game Loop )
        while(player.isAlive){
            
            pT = System.currentTimeMillis();
           //System.out.println("Render Loop Thread Is Running Waiting 1s");
           
            try {
                Thread.sleep(sleepTime); // Set time to 10 milli seconds for 60FPS ----> 390 for 3fps for debugging
            } catch (InterruptedException ex) {
                Logger.getLogger(Render.class.getName()).log(Level.SEVERE, null, ex);
            }
            
 // GAME LOGIC : 
        
                
                 // Physics
                 if(!player.isTop || player.isJump)
                         physics();
                
                
                 // Find tile nearest ot player
                     player.loc = player.findTile();
                     
                 // Check Collision
                        player.collision();
                        
            
            
            // Draw Frame
            super.repaint();
            
            
            
            
            
            // Display FPS
            dT = dT +(System.currentTimeMillis() - pT);
            if(dT < 1000){ fps++;} else { dT = 0; Window.theWindow.setTitle("A Game :)     FPS : " + fps); fps=0;}
            
        
        
         
            
            
        }
        
        
    }
    
   
    
   
    public void physics(){
        
        
       
        
        // Calculate Delta S off of starting velocity
            player.py = (int)(player.py - ((player.pyV * time )- (0.5 *7 * Math.pow(time, 2))) );
          
            
        // ReCalculate Player Velocity Based off elapsed time
            player.pyV = player.pyV - (7 * time);
            
        
          
            //System.out.println("Current Y - Postion : " + (player.py + 25));
//            System.out.println("Current Velcoity: " + (int) player.pyV);
//            
         // Increment time 
         time+= 0.0043;
         
        
    }
    
 
    
}
