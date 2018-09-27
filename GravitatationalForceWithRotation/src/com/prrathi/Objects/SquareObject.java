package com.prrathi.Objects;

import processing.core.PApplet;
import processing.core.PVector;

public class SquareObject extends BasicObject {

    float side = 10;


    public SquareObject(PApplet scene, float x, float y, float side) {
        super(scene, new PVector(x,y));
        this.side = side;
    }


    @Override
    public void render() {
        if(enableStyle) {
            scene.pushStyle();
            applyStyle();
        }
        scene.pushMatrix();
        scene.translate(position.x, position.y);
        scene.rotate(rotation);
        scene.rect(0,0, side, side);
        scene.popMatrix();
        if(enableStyle)
            scene.popStyle();
    }

}
