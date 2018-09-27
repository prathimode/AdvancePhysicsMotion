class Plane {
  
  ArrayList<PVector> points;
  Body body;
  
  Plane()
  {
    points = new ArrayList<PVector>();
    float angle = 100;
    for(int i=0 ;i<width ;i++) {
    //points.add(new PVector(i, height- map(sin(angle),-1,1,20, height)/3));
        points.add(new PVector(i, height- noise(angle)*height/2));

     angle =  angle + 0.012;
    }
    
    BodyDef def = new BodyDef();
    body = box2d.createBody(def);
    
    ChainShape ps = new ChainShape();
    Vec2[] pointV = new Vec2[points.size()];
    for(int i = 0; i< points.size(); i++) {
      pointV[i] = box2d.coordPixelsToWorld(points.get(i));
    }
    ps.createChain(pointV, pointV.length);
    
    //FixtureDef fd = new FixtureDef();
    //fd.density = 1;
    //fd.friction = 0.2;
    //fd.restitution = 0.9;
    //fd.setShape(ps);
    
    body.createFixture(ps,1);
  }
  
  void render() {
    pushMatrix();
    
    stroke(0);
    beginShape();
    for(PVector p : points) {
      vertex(p.x,p.y);
    }
    vertex(width,height);
    vertex(0,height);
    vertex(points.get(0).x,points.get(0).y);
    endShape(CLOSE);
    popMatrix();
  }
  
}
