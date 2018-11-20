package prrathi.commonmodel.P3Shape;


import lombok.Getter;
import lombok.Setter;
import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.PRCBase;

@Getter
@Setter
public abstract class BasicObject extends PRCBase {

    public PVector position;
    float rotation = 0;

    public BasicObject(IPRCWorldModelSource scene, PVector pos) {
        super(scene);
        this.position = pos;
    }

    public abstract boolean contains(float x, float y);

    public void setRotation(float angle) {
        this.rotation = angle;
    }
}
