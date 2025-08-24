package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class enemy
  extends gameObject {
  private handler handler;
  
  public enemy(int x, int y, ID id, handler handler1) {
    super(x, y, id);
    
    this.handler = handler1;

    
    this.velX = 5.0F;
    this.velY = 5.0F;
  }


  
  public void tick() {
    this.x += this.velX;
    this.y += this.velY;
    
    if (this.y <= 0.0F || this.y >= 445.0F)
      this.velY *= -1.0F; 
    if (this.x <= 0.0F || this.x >= 608.0F)
      this.velX *= -1.0F; 
    this.handler.addObject(new Trail(this.x, this.y, ID.Trail, Color.red, 16, 16, 0.015F, this.handler));
  }


  
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect((int)this.x, (int)this.y, 16, 16);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 16, 16);
  } private void collision()  {
	    for (int i = 0; i < handler.object.size(); i++) {
	        gameObject tempObject = handler.object.get(i);
	        if (tempObject.getID() == ID.ARROW) {
	          getBounds().intersects(tempObject.getBounds()); 
	          this.handler.removeObject(this);
	      } 
	    }}
}
