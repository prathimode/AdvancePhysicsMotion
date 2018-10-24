package com.prrathi;

import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import processing.core.PApplet;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.Box2DShape.B2DBase;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.Object.B2DBoxObject;
import prrathi.commonmodel.Box2DShape.Object.B2DShapeConfig;
import prrathi.commonmodel.Box2DShape.Pair.B2DMousePair;
import prrathi.commonmodel.Box2DShape.IWorldModelSource;
import shiffman.box2d.Box2DProcessing;

public class MouseJointExample  extends BasicGraphicWindow implements IWorldModelSource {

    Box2DProcessing box2d;
    ArrayList<B2DObjectBase> boundaries;
    B2DObjectBase box;
    B2DMousePair mousePair;

    @Override
    public void settings() {
        super.settings();
        box2d = new Box2DProcessing(this);
        box2d.createWorld();
        // Add a bunch of fixed boundaries
        boundaries = new ArrayList();
        B2DShapeConfig shapeConfig = new B2DShapeConfig();
        shapeConfig.setBodyType(BodyType.STATIC);
        boundaries.add(new B2DBoxObject(new Vec2(width/2,height-5), width,20, shapeConfig, this));
        boundaries.add(new B2DBoxObject(new Vec2(width/2,5),width,20,shapeConfig, this));
        boundaries.add(new B2DBoxObject(new Vec2(width-5,height/2),20,height,shapeConfig, this));
        boundaries.add(new B2DBoxObject(new Vec2(5,height/2),20,height,shapeConfig, this));

        box = new B2DBoxObject(new Vec2(width/2,height/2), 40,40, this);
        // Give it some initial random velocity
        box.body.setLinearVelocity(new Vec2(random(-5, 5), random(2, 5)));
        box.body.setAngularVelocity(random(-5, 5));
        mousePair = new B2DMousePair(this);
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
        fill(255);
        strokeWeight(1);
        box.render();

        mousePair.update(mouseX,mouseY);
        mousePair.render();
    }

    @Override
    public void mousePressed() {
        super.mousePressed();
        if(box.contains(mouseX,mouseY))
        {
            println("yes");
            mousePair.bind(new Vec2(mouseX, mouseY), box);
        }
    }

    @Override
    public void mouseReleased() {
        super.mouseReleased();
        mousePair.destroy();
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
        String[] args = {"MouseJointExample"};
        MouseJointExample gw = new MouseJointExample();
        PApplet.runSketch(args,gw);
    }
}
