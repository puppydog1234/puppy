package main;

import java.util.Random;

public class spawn
{
  game game;
  private handler handler;
  private HUD hud;
  private int scoreKeep = 0;
  private Random r = new Random();
  private int c;
  private int c1;
  private int c2;
  enemy en1;
  
  public spawn(handler handler2, HUD hud) {
    this.c = 0;
    this.en1 = new enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler);
    this.handler = handler2;
    this.hud = hud; } public void tick() throws Exception { this.scoreKeep++;
    
    if (this.scoreKeep >= 200) {
      this.scoreKeep = 0;
      this.hud.setLevel(this.hud.getLevel() + 1);
      if (this.hud.getLevel() == 5)
        this.handler.addObject(new fastenemy(this.r.nextInt(620), this.r.nextInt(457), ID.fastenemy, this.handler)); 
      if (this.hud.getLevel() == 8 || this.hud.getLevel() == 6)
        this.handler.addObject(new enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler)); 
      if (this.hud.getLevel() == 12) {
        this.handler.clearEnemy();
        this.handler.addObject(new boss1(272, -120, ID.Enemy, this.handler));
      }  if (this.hud.getLevel() == 24) {
        this.handler.clearEnemy();
        this.handler.addObject(new enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler));
      }  if (this.hud.getLevel() == 34) {
        this.handler.addObject(new enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler));
      }
      if (this.hud.getLevel() >= 38) {
    	  if (this.c1 == 20) {
              this.handler.addObject(new enemy(this.r.nextInt(620), this.r.nextInt(457), ID.Enemy, this.handler));
          this.c1 = 0;
      }}
      if (this.hud.getLevel() >= 74) {
    	  if (game.gameState2 == main.game.STATE2.EASY) {
    	  this.hud.won += 1;
      }}
      if (this.hud.getLevel() >= 100) {
    	  if (game.gameState2 != main.game.STATE2.EASY) {
    	  this.hud.won += 1;
      }}
      if (this.hud.getLevel() >= 301) {
    	  if (game.gameState2 == main.game.STATE2.HARD) {
    	  this.hud.won += 1;
      }}
      if  (this.hud.getLevel() >= 68) {
    	  if (this.c2 == 25) {
              this.handler.addObject(new smartenemy(this.r.nextInt(620), this.r.nextInt(457), ID.smartenemy, this.handler));
              this.c2 = 0;
    	  }
      }
      if (this.c == 2) {
        this.handler.addObject(new goodPotion(this.r.nextInt(590), this.r.nextInt(427), ID.goodPotion, this.handler));
        this.c = 0;
      } 
      this.c++;
      this.c1++;
      this.c2++;
    }  }

}
