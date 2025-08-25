package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.core.Handler;
import game.enums.ID;
import game.logic.Trail;

public class EnemyBossBullet
  extends GameObject {
  private final Handler handler;
  Random r = new Random();
  
  public EnemyBossBullet(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    this.velX = (this.r.nextInt(10) - 5);
    this.velY = 5.0F;
  }


  
  public void tick() {
    this.x += this.velX;
    this.y += this.velY;






    
    if (this.y >= 477.0F) {
      this.handler.removeObject(this);
    }
    
    this.handler.addObject(new Trail(this.x, this.y, ID.Trail, Color.red, 16, 16, 0.015F, this.handler));
  }


  
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect((int)this.x, (int)this.y, 16, 16);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 16, 16);
  }
}
