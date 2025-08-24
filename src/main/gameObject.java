package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class gameObject
{
  protected float x;
  protected float y;
  
  public gameObject(float x, float y, ID id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }
  protected ID id; protected float velX; protected float velY;
  public abstract void tick();
  public abstract void render(Graphics paramGraphics);

  public abstract Rectangle getBounds();
  public void setX(float x) {
    this.x = x;
  } public void setY(float y) {
    this.y = y;
  } public float getX() {
    return this.x;
  } public float getY() {
    return this.y;
  } public void setID(ID id) {
    this.id = id;
  } public ID getID() {
    return this.id;
  } public void setvelX(float velx) {
    this.velX = velx;
  } public void setvelY(float vely) {
    this.velY = vely;
  } public float getvelX() {
    return this.velX;
  } public float getvelY() {
    return this.velY;
  }
}
