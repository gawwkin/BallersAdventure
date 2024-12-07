
package agame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Window extends JFrame implements KeyListener, MouseListener, ActionListener {
public Dimension screenSize;
public Render game;  
public static Window theWindow;
public static boolean isJumped;
public JMenuBar menuBar;
public JMenu levelCreatorMenu, levelLoader;
public JMenuItem levelCreator;
public EditorWindow editor;
public JMenuItem[] levels;
public File world;
    
    // Initalize Window
    public Window() throws InterruptedException, IOException{
       
        // Initalize Menu for Level Creator
        menuBar = new JMenuBar();
        levelCreatorMenu = new JMenu("Menu");
        levelCreator = new JMenuItem("Level Creator");
        levelLoader = new JMenu("Load Level");
        
        levelCreatorMenu.add(levelCreator);
        levelCreatorMenu.add(levelLoader);
        menuBar.add(levelCreatorMenu);
        levelCreator.addActionListener(this);
        
        
        
       
        
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       // game = new Render();
        theWindow = this;
        isJumped = false;
        
        
        
        
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
       // this.add(game);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setJMenuBar(menuBar);
        loadLevel();
        JOptionPane.showMessageDialog(null, "Please Select A Level to Load From the Menu");
        this.setResizable(false);
        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        

        if(e.getKeyChar() == 'a' && !game.player.isTouch){
            game.player.dir = 0;
            if(game.player.px <=300 && game.player.px > 0){
                game.player.px -= game.player.ps;
                
            } 
            //game.player.px = game.player.px - game.player.ps;
        }
        else if (e.getKeyChar() == 'd' && !game.player.isTouch){
            game.player.dir = 1;
            if(game.player.px >= 300){
                game.camera.moveWorld();
                //game.player.px -= game.player.ps * 6;
            } 
            else{
                
                game.player.px += game.player.ps;
            }
            //game.player.px = game.player.px + game.player.ps;
            
        }
        else if (e.getKeyChar() == 'r'){
            game.player.px = 0;
            game.player.py = 300;
            game.player.pyV = 0;
            game.time = 0.0019;
        }
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       //.out.println("Mouse Clicked");
       
       
       
       if(e.getButton() == MouseEvent.BUTTON1 && !game.player.isJump && game.player.pyV == 0){
               game.player.isJump = true;
               game.player.isTouch = false;
                game.time = 0.006;          
                game.player.pyV = 30;
       }
       
       
       
       
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        //System.out.println(e.getActionCommand());
        if(e.getSource() == this.levelCreator){
            this.setEnabled(false);

            this.setVisible(false);

            try {
                editor = new EditorWindow();
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   
        else{
             for(int cnt=0; cnt<levels.length; cnt++){
                 if(levels[cnt] != null && e.getActionCommand().equalsIgnoreCase(levels[cnt].getText())){
                     world = new File("src/World/" + e.getActionCommand());
                     System.out.println(world.getAbsolutePath());
                     try {
                         setLevel();
                     } catch (InterruptedException ex) {
                         Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
        }
    }

   
    public void loadLevel(){
        
        File[] files = findLevels();
        
        
        levels = new JMenuItem[files.length];
        
        for(int cnt =0; cnt< files.length; cnt++){
            
            if(files[cnt].isDirectory()){
                levels[cnt] = new JMenuItem(files[cnt].getName());
                levelLoader.add(levels[cnt]);
                levels[cnt].addActionListener(this);
            }
            
        }
        
        
        
    }
    
    
    
    public File[] findLevels(){
        
       
        File mainFolder = new File("src/World/");
        File[] files;
        files = mainFolder.listFiles();
       
        return files;
        
        
    }
    
    public void setLevel() throws InterruptedException, IOException{
        File[] files;
        files = new File(world.getAbsoluteFile().toString()).listFiles();
        for(int cnt = 0; cnt<files.length; cnt++){
            System.out.println(files[cnt].getName());
        }
        
        if(files[0].getName().equalsIgnoreCase("enemy.txt")){
            game = new Render(files[2], files[0], files[1]);
        }
        else
            game = new Render(files[0], files[1], files[2]);
        
       
        
        this.add(game);
        
    }
    
    
    
}
