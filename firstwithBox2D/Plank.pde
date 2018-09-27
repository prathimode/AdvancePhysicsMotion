class Plank {
  
  float x,y;
  float w,h;
  Body body;
  
  Plank(int x_, int y_)
  {
    x = x_;
    y = y_;
    h = 40;
    w = 300;
    
    BodyDef def = new BodyDef();
    def.setType(BodyType.STATIC);
    def.setPosition(box2d.coordPixelsToWorld(x,y));
    body = box2d.createBody(def);
    
    PolygonShape ps = new PolygonShape();
    float boxw = box2d.scalarPixelsToWorld(w/2);
    float boxh = box2d.scalarPixelsToWorld(h/2);
    ps.setAsBox(boxw, boxh);
    
    FixtureDef fd = new FixtureDef();
    fd.density = 1;
    fd.friction = 0.2;
    fd.restitution = 0.9;
    fd.setShape(ps);
    
    body.createFixture(fd);
  }
  
  void render() {
    pushMatrix();
    fill(127);
    stroke(255);
    translate(x,y);
    rotate(body.getAngle());
    rect(0,0,w,h);
    popMatrix();
  }
  
}
