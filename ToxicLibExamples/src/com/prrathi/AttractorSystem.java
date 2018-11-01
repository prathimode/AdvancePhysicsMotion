package com.prrathi;

import java.util.ArrayList;
import processing.core.PApplet;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.PRCBase;
import prrathi.commonmodel.ToxicLibShape.Example.TXLAttractor;
import prrathi.commonmodel.ToxicLibShape.ITXLWorldModelSource;
import prrathi.commonmodel.ToxicLibShape.Object.TXLParticle;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;

public class AttractorSystem extends BasicGraphicWindow implements ITXLWorldModelSource {

    VerletPhysics2D physics2D;
    TXLAttractor attractor;
    ArrayList<TXLParticle> particleArrayList = new ArrayList<>();

    public void settings()
    {
        super.settings();
        physics2D = new VerletPhysics2D();
        //  physics2D.addBehavior(new GravityBehavior2D(new Vec2D(0,0.2f)));
        physics2D.setWorldBounds(new Rect(0,0,width, height));
        physics2D.setDrag (0.01f);
        attractor = new TXLAttractor(this, new Vec2D(width/2, height/2), 48, width, 0.1f);
        for(int i = 0 ; i <50; i ++) {
            TXLParticle particle = new TXLParticle(this, Vec2D.randomVector(), 16);
            particle.addBehavior(16*2, -1);
            particleArrayList.add(particle);
        }
    }

    public void draw() {
        background(255);
        physics2D.update();
        fill(0);
        attractor.render();
        fill(125);
        particleArrayList.forEach(PRCBase::render);
        if(mousePressed) {
                attractor.body.lock();
                attractor.body.x = mouseX;
                attractor.body.y = mouseY;
        } else {
            attractor.body.unlock();
        }
    }

    @Override
    public VerletPhysics2D getVerletWorld2D() {
        return physics2D;
    }

    @Override
    public PApplet getPApplet() {
        return this;
    }

    public static void main() {
        String[] args = {"AttractorSystem"};
        AttractorSystem gw = new AttractorSystem();
        PApplet.runSketch(args,gw);
    }
}
