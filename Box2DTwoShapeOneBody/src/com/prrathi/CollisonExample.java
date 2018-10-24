package com.prrathi;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PApplet;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.Box2DShape.B2DBase;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.Object.B2DBoxObject;
import prrathi.commonmodel.Box2DShape.Object.B2DParticle;
import prrathi.commonmodel.Box2DShape.Object.B2DShapeConfig;
import prrathi.commonmodel.Box2DShape.Pair.B2DMousePair;
import prrathi.commonmodel.Box2DShape.IWorldModelSource;
import prrathi.commonmodel.Style.Style;
import shiffman.box2d.Box2DProcessing;

public class CollisonExample  extends BasicGraphicWindow implements IWorldModelSource {

    Box2DProcessing box2d;
    ArrayList<B2DObjectBase> boundaries;
    ArrayList<B2DObjectBase> particles;
    B2DMousePair mousePair;

    @Override
    public void settings() {
        super.settings();
        box2d = new Box2DProcessing(this);
        box2d.createWorld();
        box2d.listenForCollisions();
        // Add a bunch of fixed boundaries
        boundaries = new ArrayList();
        B2DShapeConfig shapeConfig = new B2DShapeConfig();
        shapeConfig.setBodyType(BodyType.STATIC);
        boundaries.add(new B2DBoxObject(new Vec2(0,height-5), 2*width,5, shapeConfig, this));
        particles = new ArrayList<>();

    }

    public void beginContact(Contact cp) {
        Fixture a = cp.getFixtureA();
        Fixture b =  cp.getFixtureB();

        Object objA = a.getBody().getUserData();
        Object objB = b.getBody().getUserData();

        if(objA == null || objB == null) {
            println("error");
        }

        if(objA.getClass() == B2DParticle.class && objB.getClass() == B2DParticle.class) {
            ((B2DParticle) objA).style.setFillColorFloat(0);
            ((B2DParticle) objB).style.setFillColorFloat(0);
        } else if( objA.getClass() == B2DParticle.class && objB.getClass() == B2DBoxObject.class) {
            ((B2DParticle) objA).style.setFillColorFloat(255);
        } else if(objA.getClass() == B2DBoxObject.class && objB.getClass() == B2DParticle.class) {
            ((B2DParticle) objB).style.setFillColorFloat(255);
        }
    }

    public void endContact(Contact cp) {

    }
    @Override
    public void draw() {
        super.draw();
        strokeWeight(0);
        stroke(0);

        fill(125);
        background(255);
        box2d.step();

        boundaries.forEach(B2DBase::render);


        if(mousePressed) {
            B2DParticle p = new B2DParticle(new Vec2(mouseX, mouseY), 10, false, this);
            p.setEnableStyle(true);
            p.setStyle(new Style(125,0,1));
            p.body.setLinearVelocity(new Vec2(random(-5, 5), random(2, 5)));
            p.body.setAngularVelocity(random(-5, 5));
            particles.add(p);
        }

        particles.forEach(B2DObjectBase::render);
        particles.removeIf(B2DObjectBase::isDone);

    }




    @Override
    public PApplet getPApplet() {
        return this;
    }

    @Override
    public Box2DProcessing getBox2DProcessing() {
        return box2d;
    }

    public static void main() {
        String[] args = {"CollisonExample"};
        CollisonExample gw = new CollisonExample();
        PApplet.runSketch(args,gw);
    }
}
