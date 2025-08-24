package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail
  extends gameObject {
  private float alpha = 1.0F;
  
  private handler handler;
  private Color color;
  private int width;
  private int height;
  private float life;
  
  public Trail(float x, float y, ID id, Color color, int width, int height, float life, handler handler1) {
    super(x, y, id);
    this.color = color;
    this.height = height;
    this.width = width;
    this.life = life;
    this.handler = handler1;
  }



  
  public void tick() {
    if (this.alpha > this.life) {
      this.alpha -= this.life - 0.01F;
    } else {
      this.handler.removeObject(this);
    } 
  }
  
  private AlphaComposite makeTransparent(float alpha) {
    int type = 3;
    return AlphaComposite.getInstance(type, alpha);
  }


  
  public void render(Graphics g) {
    Graphics2D g2d = (Graphics2D)g;
    g2d.setComposite(makeTransparent(this.alpha));

    
    g.setColor(this.color);
    g.fillRect((int)this.x, (int)this.y, this.width, this.height);
    
    g2d.setComposite(makeTransparent(1.0F));
  }


  
  public Rectangle getBounds() {
    return null;
  }
}
