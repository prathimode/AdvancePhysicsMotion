boolean isSave = false;

Pendulum p,p2;
void setup() {
  size(800,600);
  p = new Pendulum(100,40);
  //p2 = new Pendulum(300,20);
}

void draw() {
  background(255);
  p2.pivot = p.blob;
  p.render();
 // p2.render();
  
}

void mouseClicked() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = true;
   }
   if(p.isHover(mouseX,mouseY)) {
    
   }
}

void mousePressed() {
  p.setDragging(true);
}

void mouseReleased() {
  p.setDragging(false);
}

  
