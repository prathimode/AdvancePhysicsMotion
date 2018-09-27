package com.prrathi.Objects;

import com.prrathi.Style.Style;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PVector;

@Getter
@Setter
public abstract class BasicObject {

    @Getter(AccessLevel.PRIVATE)
    PApplet scene;
    public PVector position;
    boolean enableStyle = true;
    public Style style;
    float rotation = 0;

    public BasicObject(PApplet scene, PVector pos) {
        this.scene = scene;
        this.position = pos;
    }

    public void setRotation(float angle) {
        this.rotation = angle;
    }
    public void applyStyle() {
        scene.fill(style.getFillColorFloat());
        scene.stroke(style.getStrokeColorFloat());
        scene.strokeWeight(style.getStrokeWidth());
    }

    public void setStyle(Style style) {
        this.style = style;
    }
    abstract public void render();
}
