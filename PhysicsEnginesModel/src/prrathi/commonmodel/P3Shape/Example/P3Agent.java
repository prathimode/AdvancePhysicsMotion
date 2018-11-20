package prrathi.commonmodel.P3Shape.Example;

import static processing.core.PConstants.CLOSE;

import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.P3Shape.Example.P3Mover;


public class P3Agent extends P3Mover {

    float r;
    float maxforce;    // Maximum steering force
    float maxspeed;    // Maximum speed


    public P3Agent(IPRCWorldModelSource world, float x, float y) {
        super(world, x,y);
        acceleration = new PVector(0,0);
        velocity = new PVector(0,-2);
        r = 6;
        maxspeed = 4;
        maxforce = 0.1f;
    }

    public P3Agent(IPRCWorldModelSource world, float x, float y, float maxspeed, float maxforce) {
        super(world, x,y);
        acceleration = new PVector(0,0);
        velocity = new PVector(0,-2);
        r = 6;
        this.maxspeed = maxspeed;
        this.maxforce = maxforce;
    }

    // Method to update position
    public void update() {
        // Update velocity
        velocity.add(acceleration);
        // Limit speed
        velocity.limit(maxspeed);
        position.add(velocity);
        // Reset accelerationelertion to 0 each cycle
        acceleration.mult(0);
    }
public void run() {
    update();
    borders();
    render();
}

    public void arrive(PVector target) {
        PVector desired = PVector.sub(target,position);  // A vector pointing from the position to the target
        float dist = PVector.dist(target, position);
        if(dist < 150) {
            float m = scene.map(dist,0,150,0, maxspeed);
            desired.setMag(m);
        } else {
            desired.setMag(maxspeed);
        }
        // Steering = Desired minus velocity
        PVector arrive = PVector.sub(desired,velocity);
        arrive.limit(maxforce);  // Limit to maximum steering force
        applyForce(arrive);
    }

    public void follow(P3ForceField ff) {
        PVector newVel = ff.lookup(position);
        newVel.mult(maxspeed);
        PVector f = PVector.sub(newVel,velocity);
        f.limit(maxforce);
        applyForce(f);
    }

    // A method that calculates a steering force towards a target
    // STEER = DESIRED MINUS VELOCITY
    public void seek(PVector target) {
        PVector desired = PVector.sub(target,position);  // A vector pointing from the position to the target

        // Scale to maximum speed
        desired.setMag(maxspeed);

        // Steering = Desired minus velocity
        PVector steer = PVector.sub(desired,velocity);
        steer.limit(maxforce);  // Limit to maximum steering force

        applyForce(steer);
    }

    // Wraparound
    public void borders() {
        if (position.x < -r) position.x = scene.width + r;
        if (position.y < -r) position.y = scene.height+r;
        if (position.x > scene.width+r) position.x = -r;
        if (position.y > scene.height+r) position.y = -r;
    }

    @Override
    protected void PRCRender() {
        // Draw a triangle rotated in the direction of velocity
        float theta = velocity.heading() + scene.PI / 2;

        scene.translate(position.x, position.y);
        scene.rotate(theta);
        scene.beginShape();
        scene.vertex(0, -r * 2);
        scene.vertex(-r, r * 2);
        scene.vertex(r, r * 2);
        scene.endShape(CLOSE);
    }




    public P3Agent(IPRCWorldModelSource scene, PVector lpos) {
        this(scene, lpos.x, lpos.y);
    }

    @Override
    public boolean contains(float x, float y) {
        return false;
    }

}
