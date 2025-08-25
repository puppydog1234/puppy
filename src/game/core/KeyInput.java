package game.core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.enums.ID;
import game.object.Player;
import game.object.Arrow;
import game.object.Enemy;
import game.object.GameObject;

public class KeyInput extends KeyAdapter {
  public static boolean dashing = false;
  private final Handler handler2;
  public static boolean debug = false;
  private int c;
  private int pass1 = 0;
  private Random r;
  public static boolean nopedamage = false;
  public static boolean LORE = false;
  public static boolean isCapsLockOn = false;
  public static boolean isCapsLockOn2 = true;
  public int booltoint(boolean convbool) {
    if (convbool) {
      return 1;
    }
    return 0;
  }
  
  public KeyInput(Handler handler1) { this.c = 0;
    this.r = new Random();
    this.handler2 = handler1; }

    public static boolean nodamage = false;
   int c1 = 0;
  public void keyPressed(KeyEvent keyEvent) {
    int key = keyEvent.getKeyCode();
    for (int i = 0; i < Handler.object.size(); i++) {
      GameObject tempObject = Handler.object.get(i);
      
      if (tempObject.getID() == ID.Player) {
        
        boolean pressw = (key == 87 || key == 38);
        boolean presss = (key == 83 || key == 40);
        boolean pressa = (key == 65 || key == 38);
        boolean pressd = (key == 68 || key == 38);
        if (debug) {
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
          debug = true;
        if (key == 49 && 
          debug) {
          tempObject.setX(0.0F);
          tempObject.setY(0.0F);
        } 
        if (key == 50 && 
                debug) {
          tempObject.setX(500.0F);
          tempObject.setY(500.0F);
        } 
        if (key == 78 && 
                debug) {
        	nodamage = true;
        }
        if (key == 51 && 
          debug) {
          try {
            Player.damage(5);
          } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
          }
        }
        if (key == 36) {
        	debug = true;
        }
		if (key == 76) {
            LORE = !LORE;
		}
			
        isCapsLockOn = java.awt.Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        nodamage = (isCapsLockOn && isCapsLockOn2);
        if (key == 127) {
        	if (c1 != 1) {
              c1 += 1;
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2)); this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));
	              this.handler2.addObject(new Enemy(this.r.nextInt(640), this.r.nextInt(477), ID.Enemy, this.handler2));

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
          if (key == 32) {
              this.handler2.addObject(new Arrow(tempObject.getX(), tempObject.getY(), ID.ARROW, this.handler2));
          }
      }
    }
  }






  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
  }
  
  public static void playSound(String soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    File f = new File("./" + soundFile);
    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);
    clip.start();
  }
}
