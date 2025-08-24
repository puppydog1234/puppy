package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import main.game.STATE;

public class Menu
  extends MouseAdapter
{
  game game;
  private handler handler;
  private Random r = new Random();
  private HUD hud;
  
  public Menu(game game1, handler handler1) {
    this.handler = handler1;
    this.game = game1;
  }
  
  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();
    


    game.menu2 = new Menu2(game, handler);
      this.game.gameState = STATE.MENU2;
      //this.game.gameState = STATE.GAME;
      try {
        //this.handler.addObject(new Player(640 / 2 - 32, 477 / 2 - 32, ID.Player, this.handler));
        //this.handler.addObject(new enemy(this.r.nextInt(640 - 50), this.r.nextInt(477 - 50), ID.Enemy, this.handler));
      } catch (Exception e1) {
        //e1.printStackTrace();
      }
    } 
 

  
  public void mouseReleased(MouseEvent e) {}

  
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

  
  public void render(Graphics g) {
    Font fnt = new Font("arial", 1, 50);
    Font fnt2 = new Font("arial", 1, 30);
    g.setFont(fnt);
    g.setColor(Color.gray);
    g.drawString("Menu", 230, 60);
    
    g.setFont(fnt2);
    g.setColor(Color.gray);
    g.drawString("Quit", 270, 200);

    g.setFont(fnt2);
    g.setColor(Color.gray);
    g.drawString("Play", 270, 290);
    
    g.setColor(Color.white);
    g.drawRect(200, 150, 200, 64);
    
    g.drawRect(200, 250, 200, 64);
  }
}
