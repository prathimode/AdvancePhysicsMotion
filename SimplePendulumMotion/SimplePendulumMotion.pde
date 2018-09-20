boolean isSave = false;
float period = 200;

void setup() {
  size(800,600);
}

void draw() {
  background(255);
  ellipseMode(CENTER);
  fill(0);
  translate(width/2, height/2);
  float dist = sin((frameCount / period) * TWO_PI) * 200;
  line(0,0,dist,0);
  ellipse(dist,0,20,20);
}

void mouseClicked() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = true;
   }
}

  
