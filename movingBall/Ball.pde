class Ball {
 PVector loc;
 PVector vel;
 float r;

 
 Ball(PVector loc, PVector vel, float radius) 
 {
   this.loc = loc;
   this.vel = vel;
   this.r = radius;
 }
  
 void move() {
   if(loc.x > width- r || loc.x<0 + r) {
    vel.x = -1* vel.x;
  }
  if(loc.y > height-r || loc.y<0 + r) {
    vel.y = -1* vel.y;
  }
  
  loc.x = loc.x + vel.x;
  loc.y = loc.y + vel.y;
 }
 
 void render() {
   fill(150);
  stroke(0);
  
  ellipseMode(CENTER);
  ellipse(loc.x, loc.y, r, r);
  
 }
  
}
