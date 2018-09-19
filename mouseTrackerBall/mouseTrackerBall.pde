boolean isSave = false;
Mover m;

void setup() {
  size(800,600);
 m = new Mover(width/2,height/2);
}

void draw() {
 background(0); //<>//
 m.update(mouseX,mouseY);
 m.render();
}

void mouseClicked() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = false;
   }
}

  
