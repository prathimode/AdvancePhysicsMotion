class Pendulum {
  
  PVector pivot;
  PVector blob;
  PVector vel;
  PVector acc;
  float l;
  float r;
  boolean isDragging = false;
  float angle = 0;
  
  public Pendulum(float l, float r)
  {
    pivot = new PVector( width/2, 0);
    blob = new PVector( width/2, r);
    vel = new PVector(0,0);
    acc = new PVector(0,0);
    this.l = l;
    this.r = r;
    angle = PI/4;
  }
  public void setDragging(boolean set) {
    isDragging = set;
  }
  
  public boolean isHover(float x, float y) {
   if( pow(x-blob.x,2) + pow(y-blob.y,2) - r*r <0) {
    return true;
   }
    return false;
  }
  
  public void drag(float x, float y)
  {
    if(isDragging) {
    float dist = PVector.dist(pivot, new PVector(x,y));
    blob = PVector.sub(new PVector(x,y), pivot);
    l = dist;
    angle = PI /2 - blob.heading();
    }
  }
  
  public void update() {
    
     acc = (new PVector(0,-0.1*sin(angle)/l));
     vel.add(acc);
     vel.mult(0.998);
     angle += vel.y;
    
  }
  
  public void render() {
    stroke(0);
    
    if(!isDragging) {
    update();
    fill(125);
    } else {
      fill(0);
    drag(mouseX, mouseY);
    }
    blob = new PVector( width/2 + l* sin(angle), l* cos(angle));
    line(pivot.x, pivot.y, blob.x, blob.y);
    ellipse(blob.x , blob.y , r,r);
    
  }  
}
