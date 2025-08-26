
// src/game/core/AudioPlayer.java
package game.core;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {
    private static final Map<String, Clip> soundMap = new HashMap<>();

    public static void loadSound(String key, String path) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            soundMap.put(key, clip);
        } catch (Exception e) {
            System.err.println("Failed to load sound: " + path);
            e.printStackTrace();
        }
    }

    public static void playSound(String key) {
        Clip clip = soundMap.get(key);
        if (clip != null) {
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public static void loopSound(String key) {
        Clip clip = soundMap.get(key);
        if (clip != null) {
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void stopSound(String key) {
        Clip clip = soundMap.get(key);
        if (clip != null) {
            clip.stop();
        }
    }
}
