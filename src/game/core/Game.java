package game.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

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
    private final Spawn spawner;
    private final Handler handler;

    private final Menu menu;
    private final Menu2 menu2;

    public static Graphics g2;
    private Graphics g;

    public static Map<String, Sound> soundMap = new HashMap<>();
    public static Map<String, Music> musicMap = new HashMap<>();

    private int frames = 0;
    private int count = 0;

    public static STATE gameState = STATE.MENU2;
    public static STATE2 gameState2 = STATE2.NOPE;

    @Serial
    private static final long serialVersionUID = -3462486173394796704L;

    public Game() {
        this.handler = new Handler();
        this.hud = new HUD();
        this.spawner = new Spawn(this.handler, this.hud);

        this.menu = new Menu(this, this.handler);
        this.menu2 = new Menu2(this, this.handler); // always initialized safely

        // Load and start background music once
        try {
            musicMap.put("main", new Music("res/song.wav"));
            musicMap.get("main").loop();
        } catch (Exception e) {
            System.err.println("Failed to load music: " + e.getMessage());
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this.handler));
        addMouseListener(this.menu);
        addMouseListener(this.menu2);

        new Window(WIDTH, HEIGHT, "the doger dager", this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception ignored) {
            // Ignore errors when stopping thread
        }
    }

    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                try {
                    tick();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                delta--;
            }

            if (running) render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() throws Exception {
        handler.tick();
        hud.tick();

        if (gameState == STATE.GAME) {
            spawner.tick();
            removeMouseListener(menu2);
        } else if (gameState == STATE.MENU) {
            menu.tick();
        } else if (gameState == STATE.MENU2) {
            menu2.tick();
        }

        // After some time, switch background music (only once)
        if (count == 5600) {
            try {
                musicMap.put("main", new Music("res/tell-me-what-379638.wav"));
                musicMap.get("main").loop();
            } catch (Exception e) {
                System.err.println("Failed to load alternate music: " + e.getMessage());
                e.printStackTrace();
            }
        }
        count++;
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g2 = g;

        // Clear screen
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Render game elements
        if (gameState == STATE.GAME) {
            handler.render(g);
            hud.render(g, gameState);
        } else if (gameState == STATE.MENU) {
            menu.render(g);
        } else if (gameState == STATE.MENU2) {
            menu2.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float value, int min, int max) {
        if (value >= max) return max;
        if (value <= min) return min;
        return value;
    }

    public static void main(String[] args) {
        new Game();
    }
}
