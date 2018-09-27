package com.prrathi;

import com.prrathi.Objects.CircularObject;
import com.prrathi.Style.Style;
import processing.core.PApplet;
import processing.core.PVector;

public class Attractor {
    PApplet mScene;
    PVector accelaration, velocity;
    float mass;
    CircularObject object;
    private boolean rollOver = false;
    private boolean dragging = false;
    private float draggingOffsetx = 0;
    private float draggingOffsety = 0;

    public Attractor(PApplet scene, float x, float y, float mass)
    {
        this.mScene = scene;
        this.accelaration = new PVector(0,0);
        this.velocity = new PVector(0,0);
        this.mass = mass;
        object = new CircularObject(scene, x,y,mass*20);
        object.setStyle(new Style(100,0,3));
    }

    public PVector attract(Mover mover) {
        PVector force = PVector.sub(object.position, mover.getObject().position);
        float dist = mScene.constrain(force.mag(),5, 10);
        float forceMag = mover.getMass() * mass / (dist*dist);
        force.normalize();
        return force.mult(forceMag);
    }

    public void isClicked(int x, int y) {
        float dist = PVector.dist(object.position,new PVector(x,y));
        if(dist < object.getRadius()) {
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
        float dist = PVector.dist(object.position,new PVector(mx,my));
        if(dist > object.getRadius()) {
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
    public void render() {
        if(rollOver) {
            object.getStyle().setFillColorFloat(20);
        } else if(dragging) {
            object.getStyle().setFillColorFloat(5);
        } else {
            object.style.setFillColorFloat(100);
        }
        object.render();
    }
}
