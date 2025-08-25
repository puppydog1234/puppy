package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.core.Handler;
import game.enums.ID;
import game.logic.Trail;

public class FastEnemy
  extends GameObject {
  private final Handler handler;
  
  public FastEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    this.velX = 9.0F;
    this.velY = 9.0F;
  }

  public void tick() {
    this.x += this.velX;
    this.y += this.velY;
    
    if (this.y <= 0.0F || this.y >= 445.0F)
      this.velY *= -1.0F; 
    if (this.x <= 0.0F || this.x >= 608.0F)
      this.velX *= -1.0F; 
    this.handler.addObject(new Trail(this.x, this.y, ID.Trail, Color.DARK_GRAY, 24, 24, 0.015F, this.handler));
  }


  
  public void render(Graphics g) {
    g.setColor(Color.DARK_GRAY);
    g.fillRect((int)this.x, (int)this.y, 24, 24);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 24, 24);
  }
}
