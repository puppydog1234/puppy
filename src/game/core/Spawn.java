package game.core;

import java.util.Random;

import game.enums.ID;
import game.enums.STATE2;
import game.gui.HUD;
import game.object.Boss1;
import game.object.Enemy;
import game.object.FastEnemy;
import game.object.GoodPotion;
import game.object.SmartEnemy;

public class Spawn
{
  private Handler handler;
  private final HUD hud;
  private int scoreKeep = 0;
  private final Random r = new Random();
  private int c;
  private int c1;
  private int c2;
  Enemy en1;
  
  public Spawn(Handler handler2, HUD hud) {
    this.c = 0;
    this.en1 = new Enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler);
    this.handler = handler2;
    this.hud = hud; } public void tick() throws Exception { this.scoreKeep++;
    
    if (this.scoreKeep >= 200) {
      this.scoreKeep = 0;
      this.hud.setLevel(this.hud.getLevel() + 1);
      if (this.hud.getLevel() == 5)
        this.handler.addObject(new FastEnemy(this.r.nextInt(620), this.r.nextInt(457), ID.fastenemy, this.handler));
      if (this.hud.getLevel() == 8 || this.hud.getLevel() == 6)
        this.handler.addObject(new Enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler));
      if (this.hud.getLevel() == 12) {
        this.handler.clearEnemy();
        this.handler.addObject(new Boss1(272, -120, ID.Enemy, this.handler));
      }  if (this.hud.getLevel() == 24) {
        this.handler.clearEnemy();
        this.handler.addObject(new Enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler));
      }  if (this.hud.getLevel() == 34) {
        this.handler.addObject(new Enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler));
      }
      if (this.hud.getLevel() >= 38) {
    	  if (this.c1 == 20) {
              this.handler.addObject(new Enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler));
          this.c1 = 0;
      }}
      if (this.hud.getLevel() >= 115) {
    	  if (Game.gameState2 == STATE2.EASY) {
    	  this.hud.won += 1;
    	  ending();
      }}
      if (this.hud.getLevel() >= 400) {
    	  if (Game.gameState2 == STATE2.HARD) {
    	  this.hud.won += 1;
    	  ending();
      }}
      if  (this.hud.getLevel() >= 68) {
    	  if (this.c2 == 25) {
              this.handler.addObject(new SmartEnemy(this.r.nextInt(620), this.r.nextInt(457), ID.smartenemy, this.handler));
              this.c2 = 0;
    	  }
      }
      if (this.c == 2) {
        this.handler.addObject(new GoodPotion(this.r.nextInt(590), this.r.nextInt(427), ID.goodPotion, this.handler));
        this.c = 0;
      } 
      this.c++;
      this.c1++;
      this.c2++;
    }  }

public void ending() {
	   KeyInput.nopedamage = true;
}
}
