boolean isSave = false;
ArrayList<ParticleSystem> ps;
void setup() {
  size(800,600);
   ps = new ArrayList<ParticleSystem>();
   ps.add(new ParticleSystem(new PVector(width/2, 10),10));
}

void draw() {
 background(255);
 for(ParticleSystem p : ps) {
   p.intersection();
  
   p.run(random(1)<0.5);
 }
 if(mousePressed) {
   for(ParticleSystem p : ps) {
     p.applyForce(new PVector(0.3,0.01));
   }
 }
}


void mouseClicked() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = false;
   }
   ps.add(new ParticleSystem(new PVector(mouseX, mouseY),10));
 }

  
