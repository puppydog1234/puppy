package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import gameobjects.Player;
import gameobjects.enemy;
import main.game.STATE;

public class Menu2
  extends MouseAdapter
{
	game game;
  private handler handler;
  private Random r = new Random();
  private spawn spawner;
  private HUD hud;
  
  public Menu2(main.game game2, handler handler1) {
    this.handler = handler1;
    this.spawner = new spawn(handler, hud);
  }
  
  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();
    if (game.gameState!=STATE.GAME) {
    if (mouseOver(mx, my, 200, 150, 200, 64)) {
        try {
			main.game.gameState2 = main.game.STATE2.EASY;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
				main.game.gameState = STATE.GAME;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
          this.handler.addObject(new Player(640 / 2 - 32, 477 / 2 - 32, ID.Player, this.handler));
          this.handler.addObject(new enemy(this.r.nextInt(640 - 50), this.r.nextInt(477 - 50), ID.Enemy, this.handler));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
    } else if (mouseOver(mx, my, 200, 250, 200, 64)) {
        try {
			game.gameState2 = main.game.STATE2.HARD;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			this.game.gameState = main.game.STATE.GAME;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
          this.handler.addObject(new Player(640 / 2 - 32, 477 / 2 - 32, ID.Player, this.handler));
          this.handler.addObject(new enemy(this.r.nextInt(640 - 50), this.r.nextInt(477 - 50), ID.Enemy, this.handler));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
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
