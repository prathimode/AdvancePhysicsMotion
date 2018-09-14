
class PerlinNoiseWalker extends Walker {
 
 float timeX = 0;
 float timeY = 0;
 
 PerlinNoiseWalker(float x, float y) {
   super(x,y);
   
   timeX = random(0,1);
   timeY = random(0,1);
 }
 
 void move() {
  
  x = map(noise(timeX), 0,1, 0 ,width);
  y =  map(noise(timeY), 0,1, 0 ,height);
 
  timeX = timeX + 0.01;
  timeY = timeY + 0.01;

 
  
 }
 void render() {
   fill(255);
     ellipseMode(CENTER);
   ellipse(x,y,3,3);
 }

}
