package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class smartenemy
  extends gameObject
{
  private handler handler;
  private gameObject player;
  
  public smartenemy(int x, int y, ID id, handler handler1) {
    super(x, y, id);
    
    this.handler = handler1;
    
    for (int i = 0; i < handler.object.size(); i++) {
      if (((gameObject)handler.object.get(i)).getID() == ID.Player) {
        this.player = handler.object.get(i);
      }
    } 

    
    this.velX = 5.0F;
    this.velY = 5.0F;
  }


  
  public void tick() {
    this.x += this.velX;
    this.y += this.velY;
    
    float diffX = this.x - this.player.getX() - 8.0F;
    float diffY = this.y - this.player.getY() - 8.0F;
    
    float distance = (float)Math.sqrt(((this.x - this.player.getX()) * (this.x - this.player.getX()) + (this.y - this.player.getY()) * (this.y - this.player.getY())));
    
    this.velX = (int)(-1.0D / distance * diffX);
    this.velY = (int)(-1.0D / distance * diffY);

    
    if (this.y <= 0.0F || this.y >= 445.0F)
      this.velY *= -1.0F; 
    if (this.x <= 0.0F || this.x >= 608.0F)
      this.velX *= -1.0F; 
    this.handler.addObject(new Trail(this.x, this.y, ID.Trail, Color.green, 16, 16, 0.015F, this.handler));
  }


  
  public void render(Graphics g) {
    g.setColor(Color.green);
    g.fillRect((int)this.x, (int)this.y, 16, 16);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 16, 16);
  }
}
