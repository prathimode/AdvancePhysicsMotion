package prrathi.commonmodel.P3Shape.Object;

import processing.core.PConstants;
import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.P3Shape.BasicObject;

public class P3SquareObject extends BasicObject {

    float side = 10;

    public P3SquareObject(IPRCWorldModelSource scene, float x, float y, float side) {
        super(scene, new PVector(x,y));
        this.side = side;
    }


    @Override
    public void PRCRender() {
        scene.translate(position.x, position.y);
        scene.rectMode(PConstants.CENTER);
        scene.rect(0, 0, side, side);
    }

    @Override
    public boolean contains(float x, float y) {
        float xLeft = position.x - side/2;
        float xRight  = position.x + side/2;
        float yUp = position.y - side/2;
        float yDown = position.y + side/2;

        return (x >= xLeft && x <= xRight && y >= yUp && y<= yDown );
    }
}
