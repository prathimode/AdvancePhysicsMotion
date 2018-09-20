boolean isSave = false;
float[] angles;
int size = 50;
void setup() {
  size(800,600);
  angles = new float[width/(size-size/3)];
  float value = 0;
  for(int i =0; i<angles.length; i+=1) {
   angles[i] = (value+=0.2);
   println(angles[i]);
  }
}

void draw() {
  background(255);
  ellipseMode(CENTER);
  fill(100,200);
  stroke(0);
  for(int i=0; i< angles.length; i= i+1) {
    float dist = sin(angles[i]) * height/2;
    ellipse(i*(size-size/3), (height/2)+dist, size,size);
    angles[i] +=0.05;
  }
}


void mouseClicked() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = false;
   }
}

  
