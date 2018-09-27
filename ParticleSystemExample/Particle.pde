class Particle {
 
  PVector position; 
  PVector vel;
  PVector acc;
  int lifeSpan;
  float radius;
  
  public Particle(PVector loc, float radius) {
    this.position= loc.get();
    this.vel = new PVector(random(-1,1),random(0,1));
    this.acc = new PVector();
    this.radius = radius;
    this.lifeSpan = 255;
  }
  
  public void applyForce(PVector f)
  {
    acc.add(f);
  }
  
  void render()
  {
    fill(0,lifeSpan);
    stroke(0,lifeSpan);
    ellipse(position.x, position.y, radius,radius);
  }
  
  public void intersact(ArrayList<Particle> list) {
    for(Particle p : list) {
     if( p != this ) {
       PVector dis = PVector.sub(position, p.position);
       if(dis.mag() < radius*2) {
         dis.setMag(1);
         applyForce(dis);
       }
     }
  }
}
  
  void update() {
    vel.add(acc);
    position.add(vel);
    lifeSpan -= 1.0;
    acc.mult(0);
  }
  
  void run()
  {
    update();
    render();
  }
  
  boolean isDead() {
    return lifeSpan<0;
  }
  
}
