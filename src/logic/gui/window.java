package logic.gui;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;

import logic.game;

public class window
  extends Canvas {
  private static final long serialVersionUID = 5008303798174461034L;
  public static JFrame frame;
  
  public window(int width, int height, String title, game game1) {
    JFrame frame = new JFrame(title);
    
    window.frame = frame;
    
    frame.setPreferredSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));
    frame.setDefaultCloseOperation(3);
    frame.setResizable(false);
    frame.setLocationRelativeTo((Component)null);
    frame.add(game1);
    frame.setVisible(true);
    game1.start();
  }
}
