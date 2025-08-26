package game.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.Serial;
import javax.swing.*;

import game.core.Game;

public class Window
  extends Canvas {//window settings
  @Serial
  private static final long serialVersionUID = 5008303798174461034L;
  public static JFrame frame;
  
  public Window(int width, int height, String title, Game game1) {
    JFrame frame = new JFrame(title);
    
    Window.frame = frame;
    
    frame.setPreferredSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.add(game1);
    frame.setVisible(true);
    game1.start();
  }
}
