package com.prrathi.Objects;

import com.prrathi.Style.Style;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

@Data
public class CircularObject {

    @Getter(AccessLevel.PRIVATE) PApplet scene;
    PVector position;
    float radius = 10;

    public void setStyle(Style style) {
        this.style = style;
    }

    Style style;
    boolean enableStyle = true;

    public CircularObject(PApplet scene, float xx, float yy, float r) {
        this.scene = scene;
        this.position = new PVector(xx,yy);
        this.radius = r;
    }


    public void render() {
        if(enableStyle) {
            scene.pushStyle();
            scene.fill(style.getFillColorFloat());
            scene.stroke(style.getStrokeColorFloat());
            scene.strokeWeight(style.getStrokeWidth());
        }
        scene.ellipseMode(PConstants.CENTER);
        scene.ellipse(position.x, position.y,radius, radius);
        if(enableStyle) {
            scene.popStyle();
        }
    }
}
