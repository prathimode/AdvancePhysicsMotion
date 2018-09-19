class Mover{
 PVector pos;
 PVector acc;
 PVector vel;
 
 Mover(float x, float y)
 {
   pos = new PVector(x,y);
   acc = new PVector(0,0);
   vel = new PVector(0,0);
 }
 
 void update(int mx, int my) {
   println(mx +" " + my); //<>//
   PVector mouse = new PVector(mx,my);
   acc = mouse.sub(pos);
   acc.setMag(0.5);
  
   vel.add(acc);
   vel.limit(10);
   pos.add(vel);
 }
 
 void render() {
   fill(255);
   stroke(0);
   ellipseMode(CENTER);
   ellipse(pos.x, pos.y, 20 ,20);
 }
}
