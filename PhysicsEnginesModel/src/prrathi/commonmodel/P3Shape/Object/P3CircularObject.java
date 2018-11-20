package prrathi.commonmodel.P3Shape.Object;


import lombok.Data;
import processing.core.PConstants;
import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.P3Shape.BasicObject;

@Data
public class P3CircularObject extends BasicObject {
    float radius = 10;

    public P3CircularObject(IPRCWorldModelSource scene, float xx, float yy, float r) {
        super(scene, new PVector(xx,yy));
        this.position = new PVector(xx,yy);
        this.radius = r;
    }

    public P3CircularObject(IPRCWorldModelSource scene, float xx, float yy) {
        super(scene, new PVector(xx,yy));
        this.position = new PVector(xx,yy);
    }


    public void PRCRender() {
        scene.ellipseMode(PConstants.CENTER);
        scene.ellipse(position.x, position.y,radius, radius);
    }

    @Override
    public boolean contains(float x, float y) {
        PVector temp = new PVector(x,y);
        if(PVector.dist(temp,position) < radius)
            return true;
        else
            return false;
    }
}
