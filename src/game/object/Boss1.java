package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.core.Game;
import game.core.Handler;
import game.enums.ID;
import game.logic.Trail;

public class Boss1
  extends GameObject {
  private final Handler handler;
  private int timer = 80;
  private int timer2 = 50;
  Random r = new Random();
  
  public Boss1(int x, int y, ID id, Handler handler1) {
    super(x, y, id);
    this.handler = handler1;
    this.velX = 0.0F;
    this.velY = 2.0F;
  }


  
  public void tick() {
    this.x += this.velX;
    this.y += this.velY;
    if (this.timer <= 0) {
      this.velY = 0.0F;
    } else {
      this.timer--;
    } 
    
    if (this.timer <= 0) {
      this.timer2--;
    }
    if (this.timer2 <= 0) {
      if (this.velX == 0.0F) {
        this.velX = 2.0F;
      }
      if (this.velX > 0.0F) {
        this.velX += 0.005F;
      } else if (this.velX < 0.0F) {
        this.velX -= 0.005F;
      } 
      this.velX = Game.clamp(this.velX, -10, 10);
      
      int spawn = this.r.nextInt(120);
      if (spawn == 0) {
          this.handler.addObject(new EnemyBossBullet((int)this.x + 48, (int)this.y + 48, ID.Enemy, this.handler));
          this.handler.addObject(new EnemyBossBullet((int)this.x - 48, (int)this.y - 48, ID.Enemy, this.handler));
      }
    } 
    
    if (this.x <= 0.0F || this.x >= 544.0F) {
      this.velX *= -1.0F;
    }
    this.handler.addObject(new Trail(this.x, this.y, ID.Trail, Color.red, 96, 96, 0.08F, this.handler));
  }


  
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect((int)this.x, (int)this.y, 96, 96);
  }


  
  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 96, 96);
  }
}
