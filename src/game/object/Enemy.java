package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.core.Handler;
import game.enums.ID;
import game.gui.HUD;
import game.logic.Trail;

public class Enemy
  extends GameObject {
  private final Handler handler;

  private final HUD hud;
  public Enemy(int x, int y, ID id, Handler handler1) {//the red enemys
    super(x, y, id);
    
    this.handler = handler1;

    
    this.velX = 5.0F;
    this.velY = 5.0F;
    this.hud = new HUD();
  }


  
  public void tick() {
	if (!(this.hud.getLevel() >= 100)) {
    this.x += this.velX;
    this.y += this.velY;
	}
    if (this.y <= 0.0F || this.y >= 445.0F && !(this.hud.getLevel() >= 100))
      this.velY *= -1.0F; 
    if (this.x <= 0.0F || this.x >= 608.0F)
    	if (!(this.hud.getLevel() >= 100)) {
      this.velX *= -1.0F; 
    	}
    this.handler.addObject(new Trail(this.x, this.y, ID.Trail, Color.red, 24, 24, 0.015F, this.handler));
    if (this.hud.getLevel() >= 100) {
		this.velX = 0;
		this.velY = 0;
    }
  }


  
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect((int)this.x, (int)this.y, 24, 24);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 24, 24);
  }
  private void collision()  {
      for (int i = 0; i < Handler.object.size(); i++) {
          GameObject tempObject = Handler.object.get(i);
          if (tempObject.getID() == ID.ARROW) {
              getBounds().intersects(tempObject.getBounds());
	          this.handler.removeObject(this);
	      } 
      }
  }
}
