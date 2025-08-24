package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD
{
  public static int HEALTH = 300;
  public int c1 = 0;
  private int greenvalue = 255;
  private int lastLevelChecked = 1;
  private boolean staminaReduced = false;
  private Font fnt3;
  private handler handler;
  private int score = 0;
  public int won = 0;
  private String won2 = "nope";
  public static String customt;
  private int level = 98;
  private boolean soundplayed = false;
  public static double stanima = 1200;
  public void render(Graphics g, game.STATE gameState) {
	    this.greenvalue = (int)game.clamp(this.greenvalue, 0, 255);
    g.setColor(Color.gray);
    g.fillRect(15, 15, 600, 32);
    g.setColor(new Color(0, 255, 0));
    g.fillRect(15, 15, HEALTH * 2, 32);
    g.setColor(Color.gray);
    g.fillRect(15, 15*5, 600, 32);
    g.setColor(new Color(75, this.greenvalue, 0));
    g.fillRect(15, 15*5, (int)stanima /2, 32);
    g.setColor(Color.white);
    g.drawRect(15, 15, 600, 32);
    g.drawString("Score: " + this.score, 10, 68);
    g.drawString("Level: " + this.level, 10, 80);
    g.drawString("DID YOU WIN?: " + this.won2, 10, 102);
    if (main.KeyInput.debug) {
        g.drawString("debug mode", 10, 132);
    } if (KeyInput.LORE)
	{
        fnt3 = new Font("arial", 1, 30);
        g.setFont(fnt3);
		g.drawString("the white sqaure is the kid", 10, 170);
		g.drawString("the red sqaures are the bullies", 10, 190);
		g.drawString("the bullies are trying to hurt the kid.", 10, 215);
		g.drawString("RUN FROM THE BULLIES", 10, 245);
	}

    if (won >= 1) {
    	won2 ="YES YOU WON!";
        fnt3 = new Font("arial", 1, 65);
        g.setFont(fnt3);
        g.setColor(Color.green);
        g.drawString("YOU WON THE GAME", 2, 200);
    	if (c1 >= 4) {
    	}
    	c1++;
    }
  }
  

public void tick() throws Exception {
    HEALTH = (int)game.clamp(HEALTH, 0, 300);
    stanima = (int)game.clamp((int)stanima, 0, 1200);
    this.greenvalue = (int)game.clamp(this.greenvalue, 0, 255);

    this.greenvalue = HEALTH * 2;
    this.score++;

    if (KeyInput.isCapsLockOn) {
    stanima = Math.max(0, stanima - 5);
    }
    if (stanima == 0) {
    	KeyInput.isCapsLockOn2 = false;
	}
	if (stanima >= 1200) {
		KeyInput.isCapsLockOn2 = true;
	} if (stanima < 1200 && !KeyInput.isCapsLockOn2) {
		stanima = Math.min(1200, stanima + 1);
	}
    if (HEALTH < 5 && !this.soundplayed) {
        Thread.currentThread().interrupt();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.exit(0);
    }
  }
  public void score(int score) {
    this.score = score;
  } public int getScore() {
    return this.score;
  } public int getLevel() {
    return this.level;
  } public void setLevel(int level) {
    this.level = level;
  }
}
