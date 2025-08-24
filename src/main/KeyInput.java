package main;

import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;










public class KeyInput
  extends KeyAdapter
{
  private handler handler2;
  public static boolean debug = false;
  private int c;
  private int pass1 = 0;
  private Random r;
  
  public int booltoint(boolean convbool) {
    if (convbool) {
      return 1;
    }
    return 0;
  }
  
  public KeyInput(handler handler1) { this.c = 0;
    this.r = new Random(); this.handler2 = handler1; } public KeyInput() { this.c = 0; this.r = new Random(); }
   public static boolean nodamage = false;
   int c1 = 0;
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    for (int i = 0; i < handler.object.size(); i++) {
      gameObject tempObject = handler.object.get(i);
      
      if (tempObject.getID() == ID.Player) {
        
        boolean pressw = (key == 87 || key == 38);
        boolean presss = (key == 83 || key == 40);
        boolean pressa = (key == 65 || key == 38);
        boolean pressd = (key == 68 || key == 38);
        if (this.debug) {
        System.out.println(key);
        }
        if (pressw)
          tempObject.setvelY((-5 * booltoint(pressw))); 
        if (presss)
          tempObject.setvelY((5 * booltoint(presss))); 
        if (pressa)
          tempObject.setvelX((-5 * booltoint(pressa))); 
        if (pressd) {
          tempObject.setvelX((5 * booltoint(pressd)));
        }
        
        if (key == 81 || key == 27)
          System.exit(0); 
        if (key == 192)
          this.debug = true; 
        if (key == 49 && 
          this.debug) {
          tempObject.setX(0.0F);
          tempObject.setY(0.0F);
        } 
        if (key == 50 && 
                this.debug) {
          tempObject.setX(500.0F);
          tempObject.setY(500.0F);
        } 
        if (key == 78 && 
                this.debug) {
        	nodamage = true;
        }
        if (key == 51 && 
          this.debug) {
          try {
            Player.damage(5);
          } catch (MalformedURLException e1) {
            
            e1.printStackTrace();
          } catch (UnsupportedAudioFileException e1) {
            
            e1.printStackTrace();
          } catch (IOException e1) {
            
            e1.printStackTrace();
          } catch (LineUnavailableException e1) {
            
            e1.printStackTrace();
          } 
        }
        if (key == 36) {
        	debug = true;
        }

     // Add this inside your keyPressed method, after other key checks
     if (key == 67) { // 67 is the key code for 'C'
    	 try {
    	        BufferedImage img = ImageIO.read(new File("catimage.jpg"));
    	        ImageIcon icon = new ImageIcon(img);
    	        game.g2.imageLabel.setIcon(icon); // mainWindow is your JFrame or main panel instance
    	    } catch (IOException ex) {
    	        ex.printStackTrace();
    	    }
     }

        if (key == 127) {
        	if (c1 != 1) {
              c1 += 1;
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2)); this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              
	              try {
	            	    	Player.damage(290);
	            	    	pass1 = 0;
					}
				catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
              }
          } this.c++;
          if (key == 32)
          this.handler2.addObject(new arrow(tempObject.getX(), tempObject.getY(), ID.ARROW, this.handler2)); 
        
      }} 
      } 
  




  
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
  }
  
  public static void playSound(String soundFile) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
    File f = new File("./" + soundFile);
    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);
    clip.start();
  }
}
