Walker w;
void setup() {
  size(800,600);
  w = new PerlinNoiseWalker(width/2, height/2);
}

void draw() {
  fill(0,4);
  rect(0,0,width, height);
  w.move();
  w.render();
}

void mouseClicked() {
  saveFrame("prathimode-#######.png");
}
  
