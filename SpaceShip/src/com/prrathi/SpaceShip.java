package com.prrathi;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CLOSE;

import processing.core.PApplet;
import processing.core.PVector;

 class SpaceShip {

    private PApplet scene;
    // All of our regular motion stuff
    private PVector position;
    private PVector velocity;
    private PVector acceleration;

    private float angle = 0;
    private float r ;
    private float dampping = 0.99f;
    private boolean thrusting = false;

    SpaceShip(PApplet scene, float x, float y, float size) {
        this.scene = scene;
        position = new PVector(x,y);
        velocity = new PVector(0,0);
        acceleration = new PVector(0,0);
        r = size;
    }

    void update() {
        velocity.add(acceleration);
      //  velocity.mult(dampping);
        velocity.limit(6);
        System.out.println(velocity.x + " " + velocity.y + " " + angle + " " + velocity.get().rotate(angle).x + "  "+ velocity.get().rotate(angle).y);
        position.add(velocity);
       // System.out.println("Position : " + position.x + " " + position.y);
        acceleration.mult(0);
    }

    void applyForce(PVector force) {
        PVector f = force.get();
        System.out.println("force: " + f.x +" " + f.y);

        acceleration.add(f);
    }

    void trust(float trust) {
        PVector f = new PVector(0,trust).rotate(angle);
        applyForce(f);
        thrusting = true;

    }

    void checkBoundries() {
        if(position.x <0)
            position.x = scene.width;
        if(position.y <0)
            position.y = scene.height;
        if(position.x > scene.width)
            position.x = 0;
        if(position.y > scene.height)
            position.y = 0;
    }

    void render() {
        scene.fill(125);
        scene.strokeWeight(3);
        scene.stroke(125);
        scene.pushMatrix();
        scene.translate(position.x,position.y);
        scene.rotate(angle);
        scene.rectMode(CENTER);
        float len = r/4;
        if(thrusting)  scene.fill(255,0,0);
        scene.rect(-1* r/4, 0, len, len );
        scene.rect(r/4,  0, len, len );

        scene.fill(125);
        scene.beginShape();
        scene.vertex(-r/2,0);
        scene.vertex(r/2,0);
        scene.vertex(0,-r);
        scene.endShape(CLOSE);
        scene.popMatrix();
        thrusting = false;
    }

    void turn(float angle) {
        this.angle += angle;
    }
}
