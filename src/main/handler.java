package main;

import java.awt.Graphics;
import java.util.LinkedList;

import gameobjects.Player;

public class handler {
  public static LinkedList<gameObject> object = new LinkedList<>();
  public void tick() {
    for (int i = 0; i < object.size(); i++) {
      gameObject tempObject = object.get(i);
      
      tempObject.tick();
    } 
  } public void render(Graphics g) {
    for (int i = 0; i < object.size(); i++) {
      gameObject tempObject = object.get(i);
      
      tempObject.render(g);
    } 
  } public void clearEnemy() throws Exception {
    for (int i = 0; i < object.size(); i++) {
      gameObject tempObject = object.get(i);
      
      if (tempObject.getID() != ID.Player) {
        object.clear();
        addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
      } 
    } 
  }
  
  public void addObject(gameObject object) {
    handler.object.add(object);
  } public void removeObject(gameObject object) {
    handler.object.remove(object);
  }
}
