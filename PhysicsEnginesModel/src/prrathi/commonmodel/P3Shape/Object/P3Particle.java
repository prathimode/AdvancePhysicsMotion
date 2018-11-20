package prrathi.commonmodel.P3Shape.Object;

import java.util.ArrayList;
import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.PRCBase;

class P3Particle extends PRCBase {

    PVector position;
    PVector vel;
    PVector acc;
    int lifeSpan;
    float radius;

    public P3Particle(IPRCWorldModelSource source, PVector loc, float radius) {
        super(source);
        this.position= loc.copy();
        this.vel = new PVector(scene.random(-1,1),scene.random(0,1));
        this.acc = new PVector();
        this.radius = radius;
        this.lifeSpan = 255;
    }

    public void applyForce(PVector f)
    {
        acc.add(f);
    }

    public void PRCRender()
    {
        scene.fill(0,lifeSpan);
        scene.stroke(0,lifeSpan);
        scene.ellipse(position.x, position.y, radius,radius);
    }

    public void intersact(ArrayList<P3Particle> list) {
        for(P3Particle p : list) {
            if( p != this ) {
                PVector dis = PVector.sub(position, p.position);
                if(dis.mag() < radius*2) {
                    dis.setMag(1);
                    applyForce(dis);
                }
            }
        }
    }

    public void update() {
        vel.add(acc);
        position.add(vel);
        lifeSpan -= 1.0;
        acc.mult(0);
    }

    public void run()
    {
        update();
        render();
    }

    public boolean isDead() {
        return lifeSpan<0;
    }

}
