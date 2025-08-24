package main;

import javax.swing.JOptionPane;

















public class Main
{
  public static void main(String[] args) throws Exception {
    JOptionPane.showConfirmDialog(null, "do you like dogs?", "from my game", 0);
    try {
      int secondsToSleep = 6;
      Thread.sleep((secondsToSleep * 1000));
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
    } 
  }
}
