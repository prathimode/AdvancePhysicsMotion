class CircularWalker extends Walker {
 
 float radius = 0;
 float angle = 0;
 float speed = 0.01;
 float dist = 0.001;
 
 CircularWalker(float x, float y) {
   super(x,y);
 }
 
 void move() {
  x =  x + dist*sin(angle);
  y = y+ dist* cos(angle);
  angle = angle + speed;
  dist = dist+ speed/10;
 }
 void render() {
   ellipseMode(CENTER);
   ellipse(x,y,1,1);
 }

}
