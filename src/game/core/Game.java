package game.core;
// imports
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

    static final int WIDTH = 640; // set window width
    static final int HEIGHT = 477; // set window height

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

    public static STATE gameState = STATE.MENU2; // gamestate can be MENU, MENU2, or GAME. check STATE enum in files for more
    public static STATE2 gameState2 = STATE2.NOPE; // can be NOPE, EASY, or HARD.

    @Serial
    private static final long serialVersionUID = -3462486173394796704L;

    public Game() {
        this.handler = new Handler();
        this.hud = new HUD();
        this.spawner = new Spawn(this.handler, this.hud);

        this.menu = new Menu(this, this.handler);
        this.menu2 = new Menu2(this, this.handler);

        // Load background music
        try {
            musicMap.put("main", new Music("res/song.wav"));
            musicMap.get("main").loop();
        } catch (Exception e) {
            System.err.println("Failed to load background music: " + e.getMessage());
            e.printStackTrace();
        }

        // Load sound effects
        try {
            soundMap.put("tap", new Sound("res/mixkit-game-ball-tap-2073.wav"));
            soundMap.put("treasure", new Sound("res/mixkit-video-game-treasure-2066.wav"));
            soundMap.put("fail", new Sound("res/mixkit-player-losing-or-failing-2042.wav"));
        } catch (Exception e) {
            System.err.println("Failed to load sound effects: " + e.getMessage());
            e.printStackTrace();
        }

        // Key and mouse inputs
        addKeyListener(new KeyInput(this.handler) {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                super.keyPressed(e);
                // Play tap sound whenever any key is pressed
                if (soundMap.containsKey("tap")) {
                    soundMap.get("tap").play();
                }
            }
        });

        addMouseListener(this.menu);
        addMouseListener(this.menu2);
        new Window(WIDTH, HEIGHT, "the doger dager", this); // call windows
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
            // ignore thread shutdown errors
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

        while (running) { // run tick clock
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

    private void tick() throws Exception {// run these every tick.
        handler.tick();
        hud.tick();

        // Example: simulate treasure event
        if (hud.getScore() > 0 && hud.getScore() % 100 == 0) {
            if (soundMap.containsKey("treasure")) {
                soundMap.get("treasure").play();
            }
        }

        if (gameState == STATE.GAME) {
            spawner.tick();
            removeMouseListener(menu2);
        } else if (gameState == STATE.MENU) {
            menu.tick();
        } else if (gameState == STATE.MENU2) {
            menu2.tick();
        }

        // After ~5600 ticks (~93 seconds), switch background music once
        if (count == 5600) {
            try {
                musicMap.put("main", new Music("res/tell-me-what-379638.wav"));
                musicMap.get("main").loop();
            } catch (Exception e) {
                System.err.println("Failed to load alternate music: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Example: simulate fail state
        if (hud.getHealth() <= 0 && soundMap.containsKey("fail")) {
            soundMap.get("fail").play();
        }

        count++;
    }

    private void render() {// what to put on screen
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

    public static float clamp(float value, int min, int max) {// keep value between min and max
        if (value >= max) return max;
        if (value <= min) return min;
        return value;
    }

    public static void main(String[] args) {// when run
        new Game();
    }
}
