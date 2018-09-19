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
   vel.add(acc);
   vel.limit(10);
   pos.add(vel);
   acc.mult(0);
 }
 
 void checkBoudaries() {
  
   if(pos.x > width )
   {
     pos.x = width ;
     vel.x *= -1;
   } else if( pos.x <0 )
   {
    pos.x = 0;
    vel.x *=-1;
   }
   if(pos.y > height) {
     vel.y *=-1;
     pos.y = height;
   } else if(pos.y<0)
   {
     vel.y *=-1;
     pos.y =0;
   }
 }
 void applyForce(PVector f)
 {
   acc.add(f);
 }
 
 
 void render() {
   fill(255);
   stroke(0);
   ellipseMode(CENTER);
   ellipse(pos.x, pos.y, 20 ,20);
 }
}
