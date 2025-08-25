package game.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.Serial;

import game.enums.STATE;
import game.enums.STATE2;
import game.gui.HUD;
import game.gui.Menu;
import game.gui.Menu2;
import game.gui.Window;


public class Game extends Canvas implements Runnable {

  static final int WIDTH = 640;
  static final int HEIGHT = 477;
  private Thread thread;
  private boolean running = false;
  private final HUD hud;
  public Graphics g;
  int frames = 0;
  public static Graphics g2;
  private final Spawn spawner;
  private final Handler handler;

  private final Menu menu;
  public Menu2 menu2;

  public static STATE gameState = STATE.MENU2;
  public static STATE2 gameState2 = STATE2.NOPE;

  
  public Game() {
    this.handler = new Handler();
    this.menu = new Menu(this, this.handler);
    if (gameState == STATE.MENU2) {
        menu2 = new Menu2(this, this.handler);
    }
    addKeyListener(new KeyInput(this.handler));
    addMouseListener(this.menu);
    addMouseListener(this.menu2);
    new Window(WIDTH, HEIGHT, "the doger dager", this);
    this.hud = new HUD();
    this.spawner = new Spawn(this.handler, this.hud);
  }
  
  @Serial
  private static final long serialVersionUID = -3462486173394796704L;
  
  public synchronized void start() {
    this.thread = new Thread(this);
    this.thread.start();
    this.running = true;
  }
  public synchronized void stop() {
    try {
      this.thread.join();
      this.running = false;
    } catch (Exception ignored) {
      // sry. I dont wanna debug this shit
    } 
  }

  
  
  public void run() {
    requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0D;
    double ns = 1.0E9D / amountOfTicks;
    double delta = 0.0D;
    long timer = System.currentTimeMillis();

    while (this.running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1.0D) {
        try {
          tick();
        } catch (Exception e) {
          e.printStackTrace();
        } 
        delta--;
      }  if (this.running)
        render();
      frames++;
      
      if (System.currentTimeMillis() - timer > 1000L) {
        timer += 1000L;

      }
    } 
    stop();
  }
  
  private void tick() throws Exception {
    this.handler.tick();
    this.hud.tick();
    if (gameState == STATE.GAME) {
      this.spawner.tick();
      removeMouseListener(menu2);
    } else if (gameState == STATE.MENU) {
        this.menu.tick();
      } else if (gameState == STATE.MENU2) {
          this.menu2.tick();
      } 
  }
  private void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    } 
    g = bs.getDrawGraphics();
    g2 = g;
    g.setColor(Color.black);
    g.fillRect(0, 0, 640, 477);
    this.handler.render(g);
    if (gameState == STATE.GAME) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 640, 477);
        this.handler.render(g);
      this.hud.render(g, gameState);
    } else if (gameState == STATE.MENU) {
      this.menu.render(g);
    }  else if (gameState == STATE.MENU2) {
    
    Menu2.render(Game.g2);
    }
    g.dispose();
    bs.show();
  }
  
  public static float clamp(float y, int min, int max) {
    if (y >= max)
      return max;
    if (y <= min) {
      return min;
    }
    return y;
  }

  public static void main(String[] args) {
    new Game();
  }
}
