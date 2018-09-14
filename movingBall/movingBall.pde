
boolean isSave = false;
ArrayList<Ball> B1;
int valueX = 8, valueY = 8;
void setup() {
  size(800, 600);
 
  B1 = new ArrayList<Ball>();
  B1.add(getAnyBall());
  background(0);
}

void draw() {
  background(0);
  for(Ball b : B1) {
  b.move();
  b.render();
  }
  
}

void mousePressed() {
  B1.add(getAnyBall());
}

void keyPressed() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = true;
   }
  
}

public Ball getAnyBall() 
 {
   return new Ball(new PVector(random(width), random(height)), new PVector(random(10), random(20)), random(20,50));
 }
