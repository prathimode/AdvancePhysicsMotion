boolean isSave = false;

import shiffman.box2d.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

ArrayList<Box> boxes;
ArrayList<Plank> planks;
Plane plane;
Box2DProcessing box2d;

void setup() {
  size(800,600);
  box2d = new Box2DProcessing(this);
  box2d.createWorld();
  boxes = new ArrayList<Box>();
  planks = new ArrayList<Plank>();
  plane  = new Plane();
  //planks.add(new Plank(300, height -100));
  //planks.add(new Plank(500, height -300));
  
  
}

void draw() {
  background(255);
  box2d.step();
 if(mousePressed)  {
   boxes.add(new Box(mouseX,mouseY));
 }
 for(Box b : boxes) {
   b.render();
 }
 plane.render();
 //for(Plank p: planks) {
 //  p.render();
 //}
}

void mouseClicked() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = false;
   }
}

  
