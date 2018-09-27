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
   println(mx +" " + my);
   PVector mouse = new PVector(mx,my);
   acc = mouse.sub(pos);
   acc.setMag(0.5);
  
  vel.add(acc);
   vel.limit(5);
   pos.add(vel);
 }
 
 void checkBoundery() {
  if(pos.x > width )
  {
        pos.x = 0 ;
        
  } else if( pos.x <0 )
  {
      pos.x = width;
  }
  if(pos.y > height) {
      pos.y = 0;
  } else if(pos.y<0)
  {
      pos.y =height;
  }
 }
 
 void render() {
   fill(255);
   stroke(0);
   pushMatrix();
   translate(pos.x,pos.y);
   
   rotate(vel.heading()+ PI/2);
   rectMode(CENTER);
   rect(0, 0, 12 ,30);
   popMatrix();
 }
}
