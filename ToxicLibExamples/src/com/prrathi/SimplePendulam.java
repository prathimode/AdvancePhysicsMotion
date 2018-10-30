package com.prrathi;
import java.util.Base64;
import processing.core.PApplet;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.ToxicLibShape.Object.TXLParticle;
import prrathi.commonmodel.ToxicLibShape.Object.TXLSpring;
import toxi.physics2d.*;
import toxi.geom.*;
import toxi.physics2d.behaviors.GravityBehavior2D;

public class SimplePendulam extends BasicGraphicWindow implements IPRCWorldModelSource {

    VerletPhysics2D physics2D;
    TXLParticle obj1;
    TXLParticle obj2;
    TXLSpring spring;

    public void settings()
    {
        super.settings();
        physics2D = new VerletPhysics2D();
        physics2D.addBehavior(new GravityBehavior2D(new Vec2D(0,0.5f)));
        physics2D.setWorldBounds(new Rect(0,0,width, height));

        obj1 = new TXLParticle(this, new Vec2D(width/2, 20), 10);
        obj2 = new TXLParticle(this, new Vec2D(width/2, 100), 30);
        spring = new TXLSpring(this,obj1, obj2, 160, 0.01f);
        obj1.body.lock();

        physics2D.addParticle(obj1.body);
        physics2D.addParticle(obj2.body);
        physics2D.addSpring(spring.springBody);
    }

    public void draw() {
        background(255);
        physics2D.update();
        obj1.render();
        obj2.render();
        spring.render();
        if (mousePressed) {
            obj2.body.lock();
            obj2.body.x = mouseX;
            obj2.body.y = mouseY;
            obj2.body.unlock();
        }
    }


    @Override
    public PApplet getPApplet() {
        return this;
    }

    public static void main() {
        String[] args = {"simplePendlum"};
        SimplePendulam gw = new SimplePendulam();
        PApplet.runSketch(args,gw);
    }
}
