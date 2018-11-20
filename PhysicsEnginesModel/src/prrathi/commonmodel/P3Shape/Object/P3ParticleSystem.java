package prrathi.commonmodel.P3Shape.Object;

import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.PRCBase;

class P3ParticleSystem extends PRCBase {

    ArrayList<P3Particle> list;
    PVector origin;
    float radius;
    IPRCWorldModelSource w;

    public P3ParticleSystem(IPRCWorldModelSource world, PVector pos, float radius) {
        super(world);
        origin = pos.copy();
        list = new ArrayList<>();
        this.radius = radius;
        w = world;
    }

    public void addParticle(boolean isRandom) {
        if(!isRandom) {list.add(new P3Particle(w, origin, radius)); }
        else {
            list.add(new P3Particle(w, new PVector(scene.random(scene.width), scene.random(scene.height)), radius));
        }
    }

    public void applyForce(PVector f)
    {
        for( P3Particle p : list)
        {
            p.applyForce(f);
        }
    }

    void intersection() {
        for (P3Particle p : list) {
            p.intersact(list);
        }
    }

    public void run(Boolean isRandom)
    {
        Iterator<P3Particle> it= list.iterator();
        while(it.hasNext()) {
            P3Particle p = it.next(); //<>//
            p.run();
            p.applyForce(new PVector(0,0.0001f));
            if (p.isDead()) {
                it.remove();
                // println("Particle dead!");
            }
        }
        addParticle(isRandom);
    }

    @Override
    protected void PRCRender() {
        run(true);
    }
}