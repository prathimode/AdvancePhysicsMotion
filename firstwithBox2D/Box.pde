
class Box {
  
  float x,y;
  float w,h;
  Body body;
  
  public Box(float x_, float y_)
  {
    x = x_;
    y = y_;
    w = 10; 
    h =10;
    
    //Step 1: define the Body
    BodyDef def = new BodyDef();
    def.type = BodyType.DYNAMIC;
    def.position.set(box2d.coordPixelsToWorld(x,y));
    
    //Step 2: Create the body
    body = box2d.createBody(def);
    
    //Step 3:  Create the shape
    PolygonShape ps = new PolygonShape();
    float box2dw = box2d.scalarPixelsToWorld(w/2);
    float box2dh = box2d.scalarPixelsToWorld(h/2);
    ps.setAsBox(box2dw, box2dh);
    
    //Step 3:  Create fixture
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.friction = 0.3;
    fd.density =1;
    fd.restitution = 0.5;
    
    body.createFixture(fd);
  }
  
  public void render() {
     Vec2 pos  = box2d.getBodyPixelCoord(body);
     float a = body.getAngle();
     pushMatrix();
    translate(pos.x,pos.y);
    //rotate by negative as both the world have flipe y axis
    rotate(-a);
    fill(125);
    stroke(255);
    rectMode(CENTER);
    rect(0,0,w,h);
    popMatrix();
  }
}
