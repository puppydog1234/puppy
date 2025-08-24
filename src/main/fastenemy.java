package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class fastenemy
  extends gameObject {
  private handler handler;
  
  public fastenemy(int x, int y, ID id, handler handler1) {
    super(x, y, id);
    
    this.handler = handler1;

    
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
    this.handler.addObject(new Trail(this.x, this.y, ID.Trail, Color.DARK_GRAY, 16, 16, 0.015F, this.handler));
  }


  
  public void render(Graphics g) {
    g.setColor(Color.DARK_GRAY);
    g.fillRect((int)this.x, (int)this.y, 16, 16);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 16, 16);
  }
}
