package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class enemyparent
  extends gameObject {
  private handler handler;
  
  public enemyparent(int x, int y, ID id, handler handler1) {
    super(x, y, id);
    
    this.handler = handler1;

    
    this.velX = 0F;
    this.velY = 0F;
  }


  
  public void tick() {
    this.x += this.velX;
    this.y += this.velY;
    
    this.handler.addObject(new Trail(this.x, this.y, ID.Trail, Color.red, 32, 32, 0.01F, this.handler));
  }


  
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect((int)this.x, (int)this.y, 32, 3);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 24, 24);
  } private void collision()  {
	    for (int i = 0; i < handler.object.size(); i++) {
	        gameObject tempObject = handler.object.get(i);
	        if (tempObject.getID() == ID.ARROW) {
	          getBounds().intersects(tempObject.getBounds()); 
	          this.handler.removeObject(this);
	      } 
	    }}
}
