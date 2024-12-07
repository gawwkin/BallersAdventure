
package agame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class EditorWindow extends JFrame implements WindowListener, MouseListener, KeyListener, ActionListener{
    
public LevelEditor editor;  
public static int mx, my;
public JMenuBar bar;
public JMenu menu;
public JMenu enem;
public JMenu Flag;
public JMenu tile;
public JMenuItem save, boog,grass, theFlag;
    
    
    public EditorWindow() throws IOException{
        
        editor = new LevelEditor();
        bar = new JMenuBar();
        menu = new JMenu("Menu");
         Flag = new JMenu("Flag");
        save = new JMenuItem("Save");
        enem = new JMenu("Enemy");
        tile = new JMenu("Tile");
        boog = new JMenuItem("Boog");
        grass = new JMenuItem("Grass");
        theFlag = new JMenuItem("FLAG");
        
        
        
        
        
        bar.add(menu);
        bar.add(enem);
        bar.add(tile);
        bar.add(Flag);
        tile.add(grass);
        enem.add(boog);
        menu.add(save);
        Flag.add(theFlag);
        grass.addActionListener(this);
        save.addActionListener(this);
        boog.addActionListener(this);
        theFlag.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Level Editor");
        this.add(editor);
        this.setJMenuBar(bar);
        this.pack();
        this.setResizable(false);
        this.addKeyListener(this);
        this.addWindowListener(this);
        this.addMouseListener(this);
        this.setBackground(Color.BLACK);
        
        
        
        
        this.setVisible(true);
        
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
       Window.theWindow.setEnabled(true);
        Window.theWindow.setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY() - 40;
        System.out.println("Mouse X :" + mx + "\n Mouse Y : " + my);
        if(editor.activeImage != null){
            editor.camera.setTile(mx, my, editor.world);
            editor.update();
        }
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
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'd'){
            editor.camera.shiftCamera();
        }
        editor.update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        if(e.getSource() == this.save){
            String input;
            input = JOptionPane.showInputDialog("Enter a name for the file ");
        try {
            if(input != null)
                editor.world.orderWorld(editor.camera.totalTile);
                editor.world.save(input, editor.camera.totalTile, editor.camera.totalEnemy);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(input != null){
                JOptionPane.showMessageDialog(null, "File Saved !!!");
                this.setEnabled(false);
                this.setVisible(false);
                Window.theWindow.setEnabled(true);
                Window.theWindow.setVisible(true);

            }
        }
        else if (e.getSource() == this.boog){
           editor.activeImage = editor.boog;
        }
        else if (e.getSource() == this.grass){
           editor.activeImage = editor.grass;
        }
        else if (e.getSource() == this.theFlag){
           editor.activeImage = editor.flager;
        }
    
        
    }
    
    
    
}
