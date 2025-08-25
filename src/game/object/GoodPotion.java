package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.core.Handler;
import game.enums.ID;




public class GoodPotion extends GameObject {
  private final Handler handler;
  
  public GoodPotion(int x, int y, ID id, Handler handler1) {
    super(x, y, id);
    this.handler = handler1;
    this.velX = 0.0F;
    this.velY = 0.0F;
  }

  public void tick() {}

  public void render(Graphics g) {
    g.setColor(Color.CYAN);
    g.fillRect((int)this.x, (int)this.y, 16, 16);
  }

  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 16, 16);
  } public void removeself() {
    this.handler.removeObject(this);
  }
}
