package game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.core.KeyInput;
import game.core.Game;
import game.core.Handler;
import game.enums.STATE;

public class HUD
{
  /* this has the health and stanima bars
    and also the level score and win and debug text
    and has logic for almost all of it.
  */
  public static int HEALTH = 300;
  public int c1 = 0;
  private int greenvalue = 255;
  private int lastLevelChecked = 1;
  private boolean staminaReduced = false;
  private Font fnt3;
  private Handler handler;
  private int score = 0;
  public int won = 0;
  private String won2 = "nope";
  public static String customt;
  private int level = 1;
  private boolean soundplayed = false;
  public static double stanima = 1200;
  public void render(Graphics g, STATE gameState) {
	    this.greenvalue = (int) Game.clamp(this.greenvalue, 0, 255);
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
    if (KeyInput.debug) {
        g.drawString("debug mode", 10, 132);
    } if (KeyInput.LORE)
	{
        fnt3 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt3);
		g.drawString("the white sqaure is the kid", 10, 170);
		g.drawString("the red sqaures are the bullies", 10, 190);
		g.drawString("the bullies are trying to hurt the kid.", 10, 215);
		g.drawString("RUN FROM THE BULLIES", 10, 245);
	}

    if (won >= 1) {
    	won2 ="YES YOU WON!";
        fnt3 = new Font("arial", Font.BOLD, 65);
        g.setFont(fnt3);
        g.setColor(Color.green);
        g.drawString("YOU WON THE GAME", 2, 200);
    	if (c1 >= 4) {
    	}
    	c1++;
    }
  }
  

public void tick() throws Exception {
    HEALTH = (int) Game.clamp(HEALTH, 0, 300);
    stanima = (int) Game.clamp((int)stanima, 0, 1200);

    this.greenvalue = HEALTH * 2;
    this.score++;

    if (KeyInput.isCapsLockOn) {
    stanima = Math.max(0, stanima - 5);
    }
    if (stanima < 1200 && !KeyInput.dashing) {
    	KeyInput.isCapsLockOn2 = false;
	}
	if (stanima >= 1200) {
		KeyInput.isCapsLockOn2 = true;
	} if (stanima < 1200 && !KeyInput.isCapsLockOn) {
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

  public void score(int score) {//set score
    this.score = score;
  } public int getScore() {//get score
    return this.score;
  } public int getLevel() {//get level
    return this.level;
  } public void setLevel(int level) {// set level
    this.level = level;
  }
}
