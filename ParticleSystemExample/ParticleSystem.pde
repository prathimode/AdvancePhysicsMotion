import java.util.Iterator;
class ParticleSystem {
  
  ArrayList<Particle> list;
  PVector origin;
  float radius;
  
  public ParticleSystem(PVector pos, float radius) {
    origin = pos.get();
    list = new ArrayList<Particle>();
    this.radius = radius;
  }
  
  public void addParticle(boolean isRandom) {
     if(!isRandom) {list.add(new Particle(origin, radius)); } 
     else {
    list.add(new Particle(new PVector(random(width), random(height)), radius));
     }
    //println("addingParticle");
  }
  
  public void applyForce(PVector f)
  {
    for( Particle p : list)
    {
      p.applyForce(f);
    }
  }
  
  void intersection() {
    for (Particle p : list) {
      p.intersact(list); 
  }
  }
  
  
  public void run(Boolean isRandom)
  {
    Iterator<Particle> it= list.iterator();
    while(it.hasNext()) {
     Particle p = it.next(); //<>// //<>//
     p.run();
     p.applyForce(new PVector(0,0.0001));
     if (p.isDead()) {
        it.remove();
       // println("Particle dead!");
      }
    }
    addParticle(isRandom);  
  }
}
