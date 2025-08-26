package game.core;

import java.awt.Graphics;
import java.util.LinkedList;

import game.enums.ID;
import game.object.GameObject;

public class Handler {
    public static LinkedList<GameObject> object = new LinkedList<>();
    public void tick() {//run every tick
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    } public void render(Graphics g) {//put stuff on screen
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }
    public void clearEnemy() {// REMOVE ALL THE ENEMIES!
        object.removeIf(tempObject -> tempObject.getID() != ID.Player);
    }

    public void addObject(GameObject object) {// add a enemy
        Handler.object.add(object);
    } public void removeObject(GameObject object) { //kill a enemy
        Handler.object.remove(object);
    }
}
