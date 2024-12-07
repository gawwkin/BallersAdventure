
package agame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


public class LevelEditor extends JPanel{
    
    public int tileD;
    public EditorWorld world;
    public static BufferedImage grass;
    public static EditorCamera camera;
    public static BufferedImage boog;
    public static BufferedImage activeImage;

    
    public LevelEditor() throws IOException{
        grass = ImageIO.read(new File("src/Images/tile.png")); // load tile image
        boog = ImageIO.read(new File("src/Images/Boog.png")); // load boog image
        
        
        world = new EditorWorld();
        camera = new EditorCamera();
        
        tileD = 40;
        this.setPreferredSize(new Dimension(800,600));
        setWorldLength();
        this.setVisible(true);
        this.setBackground(Color.BLACK);
;
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
       // Draw Line Borders of Screen
            for(int cnt =1; cnt < (600/40); cnt++){
                g2.drawLine(0, cnt*tileD, 800, cnt * tileD);

            }
            for(int cnt =1; cnt < (800/40); cnt++){
                g2.drawLine(cnt*tileD, 0, cnt*tileD, 600);

            }
       
     
            
            // Drawing Tiles and Enemies 
            for(int cnt = 0; cnt< camera.totalTile; cnt++){
                g2.drawImage(grass, camera.px[cnt], camera.py[cnt],Color.RED, this);
            }
       
  
           for(int cnt = 0; cnt< camera.totalEnemy; cnt++){
                g2.drawImage(boog, camera.ex[cnt], camera.ey[cnt],Color.RED, this);
            }

       
        
    }
    
    public void setWorldLength(){
        
        
        int length;
        length = 0;
        
        while(length < 1){
            length = Integer.parseInt(JOptionPane.showInputDialog("Enter The Desired of Length of World (Minimum is 10)  : "));
        }
        length = length * 20;
        world.setWorldLength(length);
        camera.setUp(length);
        
        
        
    }
    
    public void update(){
        
        
        this.repaint();
    }

   
    
    
}
