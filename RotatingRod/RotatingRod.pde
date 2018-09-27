boolean isSave = false;
float vel = 0;
float acc = 0;
float radius = 50;
float angle = 0;
float ellipseRad = 10;

void setup() {
  size(800,600);
}

void draw() {
  background(255);
 fill(210);
 translate(width/2, height/2);
 
 //Either use this
 //float x = radius *  sin(angle);
 //float y = radius * cos(angle);
 
 float x = 50;
 float y = 50;
 rotate(angle);
 
 line(x,y,-1*x,-1*y);
 ellipse(x,y,ellipseRad,ellipseRad);
 ellipse(-1*x, -1*y, ellipseRad,ellipseRad);
 angle = angle + vel;
 vel += acc;
 acc +=0.0001;
 
  
}

void mouseClicked() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = true;
   }
}

  
