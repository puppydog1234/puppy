package game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import game.core.Game;
import game.core.Handler;
import game.core.Spawn;
import game.enums.ID;
import game.enums.STATE;
import game.enums.STATE2;
import game.object.Player;
import game.object.Enemy;

public class Menu2
  extends MouseAdapter {
	Game game;
  private Handler handler;
  private Random r = new Random();
  private Spawn spawner;
  private HUD hud;
  private static boolean play = false;
  
  public Menu2(Game game2, Handler handler1) {
    this.handler = handler1;
    this.spawner = new Spawn(handler, hud);
  }
  
  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();
    if (game.gameState != STATE.GAME) {

    if (mouseOver(mx, my, 200, 150, 200, 64)) {
    	if (!play) {
        try {
			game.gameState2 = STATE2.EASY;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
				game.gameState = STATE.GAME;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
          this.handler.addObject(new Player(640 / 2 - 32, 477 / 2 - 32, ID.Player, this.handler));
          this.handler.addObject(new Enemy(this.r.nextInt(640 - 50), this.r.nextInt(477 - 50), ID.Enemy, this.handler));
        } catch (Exception e1) {
          e1.printStackTrace();
        }}
    	play = true;
    } else if (mouseOver(mx, my, 200, 250, 200, 64)) {
        try {
        	if (!play) {
			game.gameState2 = STATE2.HARD;
        	}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
        	if (!play) {
			this.game.gameState = STATE.GAME;
        	}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
        	if (!play) {
          this.handler.addObject(new Player(640 / 2 - 32, 477 / 2 - 32, ID.Player, this.handler));
          this.handler.addObject(new Enemy(this.r.nextInt(640 - 50), this.r.nextInt(477 - 50), ID.Enemy, this.handler));
        	}} catch (Exception e1) {
          e1.printStackTrace();
        }
        play = true;
    } }
  }

  
  public void mouseReleased(MouseEvent e) {
	  
  }

  
  private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
    if (mx > x && mx < x + width) {
      if (my > y && my < y + height) {
        return true;
      }
      return false;
    } 
    
    return false;
  }


  
  public void tick() {}

  
  public static void render(Graphics g) {
    Font fnt = new Font("arial", 1, 50);
    Font fnt2 = new Font("arial", 1, 30);
    g.setFont(fnt);
    g.setColor(Color.gray);
    g.drawString("difficulty", 230, 60);
    
    g.setFont(fnt2);
    g.setColor(Color.gray);
    g.drawString("Easy", 270, 200);

    g.setFont(fnt2);
    g.setColor(Color.gray);
    g.drawString("HARD MODE", 205, 290);
    
    g.setColor(Color.white);
    g.drawRect(200, 150, 200, 64);
    
    g.drawRect(200, 250, 200, 64);
  }
}
