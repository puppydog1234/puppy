package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.core.KeyInput;
import game.core.Game;
import game.core.Handler;
import game.enums.ID;
import game.gui.HUD;
import game.logic.Trail;

public class Player extends GameObject {// you the player.(or coder)
  Handler handler2;

  public Player(int x, int y, ID id, Handler handler1) {
    super(x, y, id);
    this.handler2 = handler1;
  }

  public void tick() {
    this.x += this.velX;
    this.y += this.velY;
    this.x = Game.clamp(this.x, 0, 608);
    this.y = Game.clamp(this.y, 40, 445);
    
    try {
      collision();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace();
    }  
	try {
		if (KeyInput.nodamage) {
			this.handler2.addObject(new Trail(this.x, this.y, ID.Trail, Color.RED, 16, 16, 0.04F, this.handler2));
		} else {
			this.handler2.addObject(new Trail(this.x, this.y, ID.Trail, Color.WHITE, 16, 16, 0.04F, this.handler2));
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
  
  private void collision() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    for (int i = 0; i < Handler.object.size(); i++) {
      GameObject tempObject = Handler.object.get(i);
      if ((tempObject.getID() == ID.Enemy || tempObject.getID() == ID.fastenemy || tempObject.getID() == ID.smartenemy) && 
        getBounds().intersects(tempObject.getBounds()) && 
        !KeyInput.nodamage && !KeyInput.nopedamage) {
        damage(5);
      }
      
      if (tempObject.getID() == ID.goodPotion && 
        getBounds().intersects(tempObject.getBounds()) && 
        HUD.HEALTH != 100) {
        HUD.HEALTH = 300;
        this.handler2.removeObject(tempObject);
      } 
    } 
  }
  
  public static void damage(int d) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    HUD.HEALTH -= d;
  }


  
  public void render(Graphics g) {
    if (this.id == ID.Player) {
    	if (!KeyInput.dashing) {
    			g.setColor(Color.white);
    	} else {
    		g.setColor(Color.RED);
    	}
    }
    g.fillRect((int)this.x, (int)this.y, 16, 16);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 16, 16);
  }
}
