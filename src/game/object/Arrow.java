package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.core.Handler;
import game.enums.ID;

public class Arrow extends GameObject {// unused arrows that pops up when you press space.
  private final Handler handler;

  public Arrow(float f, float g, ID id, Handler handler1) {
    super(f, g, id);
    this.handler = handler1;
    this.velX = 5.0F;
    this.velY = 5.0F;
  }

  public void tick() {
    this.x += this.velX;
    this.y += this.velY;
  }

  public void render(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect((int)this.x, (int)this.y, 32, 32);
  }

  public Rectangle getBounds() {
    return new Rectangle((int)this.x, (int)this.y, 32, 32);
  }

  public void removeself() {
    this.handler.removeObject(this);
  }

  private void collision() {
    for (int i = 0; i < Handler.object.size(); i++) {
      GameObject tempObject = Handler.object.get(i);

      if (tempObject.getID() == ID.Enemy || tempObject.getID() == ID.fastenemy || tempObject.getID() == ID.smartenemy) {
        if (getBounds().intersects(tempObject.getBounds())) {
          Handler.object.remove(tempObject);

        }
      }
    }
  }
}
