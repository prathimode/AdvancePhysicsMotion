boolean isSave = false;
ArrayList<Mover> mList;

void setup() {
  size(800,600);
  mList= new ArrayList<Mover>();
  for(int i =0;i<10;i++)
  mList.add(new Mover(width/random(1,5),height/random(1,5), random(0.5,2)));
}

void draw() {
 background(0);
 
 for(Mover m :mList) {
    m.applyForce(new PVector(noise(mouseX,mouseY),0));
    //Equal granivty force as we multiply by mass
    PVector v2 = new PVector(0,1);
    v2.mult(m.m);
    m.applyForce(v2);
  
    m.update(mouseX,mouseY);
    m.checkBoudaries();
    m.render();
 }
}

void mouseClicked() {
  if(!isSave)
   {
     saveFrame("prathimode-#######.png");
     isSave = true;
   }
}

 

  
