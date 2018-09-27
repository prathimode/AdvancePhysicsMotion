package com.prrathi.Objects;

import com.prrathi.Style.Style;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

@Data
public class CircularObject extends BasicObject {
    float radius = 10;

    public CircularObject(PApplet scene, float xx, float yy, float r) {
        super(scene, new PVector(xx,yy));
        this.scene = scene;
        this.position = new PVector(xx,yy);
        this.radius = r;
    }

    public CircularObject(PApplet scene, float xx, float yy) {
        super(scene, new PVector(xx,yy));
        this.scene = scene;
        this.position = new PVector(xx,yy);
    }


    public void render() {
        if(enableStyle) {
            scene.pushStyle();
            applyStyle();
        }
        scene.ellipseMode(PConstants.CENTER);
        scene.ellipse(position.x, position.y,radius, radius);
        if(enableStyle) {
            scene.popStyle();
        }
    }
}
