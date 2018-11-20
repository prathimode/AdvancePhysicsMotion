package prrathi.commonmodel.P3Shape.Example;


import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.P3Shape.BasicObject;
import prrathi.commonmodel.P3Shape.Object.P3CircularObject;
import prrathi.commonmodel.Style.P3Style;

public class P3Attractor extends BasicObject {

    PVector accelaration, velocity;
    float mass;
    BasicObject object;
    private boolean rollOver = false;
    private boolean dragging = false;
    private float draggingOffsetx = 0;
    private float draggingOffsety = 0;

    public P3Attractor(IPRCWorldModelSource scene, float x, float y, float mass)
    {
        super(scene, new PVector(x,y));
        this.accelaration = new PVector(0,0);
        this.velocity = new PVector(0,0);
        this.mass = mass;
        object = new P3CircularObject(scene, x,y,mass*20);
        object.setStyle(new P3Style(100,0,3));
    }

    public PVector attract(P3Mover mover) {
        PVector force = PVector.sub(object.position, mover.getObject().position);
        float dist = scene.constrain(force.mag(),5, 10);
        float forceMag = mover.getMass() * mass / (dist*dist);
        force.normalize();
        return force.mult(forceMag);
    }

    public void isClicked(int x, int y) {
        if(object.contains(x,y)) {
            dragging = true;
            draggingOffsetx = object.position.x - x;
            draggingOffsety = object.position.y - y;
        } else {
            dragging = false;
        }
    }

    public void drag(int mx, int my) {
        if (dragging) {
           object.position.x = mx + draggingOffsetx;
           object.position.y = my + draggingOffsety;
        }
    }

    public void hover(int mx, int my) {
        if( object.contains(mx,my)) {
            rollOver = false;
        } else {
            rollOver = true;
        }
    }

    public void stopDragging()
    {
        dragging = false;
    }
    // Render the mover on the scene
    public void PRCRender() {
        if(rollOver) {
            object.getStyle().setFillColorFloat(20);
        } else if(dragging) {
            object.getStyle().setFillColorFloat(5);
        } else {
            object.style.setFillColorFloat(100);
        }
        object.render();
    }

    @Override
    public boolean contains(float x, float y) {
        return object.contains(x,y);
    }
}
