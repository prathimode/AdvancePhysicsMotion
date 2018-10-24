package com.prrathi;

import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.Example.WindMill;
import prrathi.commonmodel.Box2DShape.Object.B2DParticle;
import prrathi.commonmodel.Box2DShape.IWorldModelSource;
import shiffman.box2d.Box2DProcessing;

public class WindMillExample extends BasicGraphicWindow implements IWorldModelSource {

    Box2DProcessing box2d;
    ArrayList<B2DObjectBase> list;
    WindMill windMill;

    @Override
    public void settings() {
        super.settings();
        box2d = new Box2DProcessing(this);
        box2d.createWorld();
        list = new ArrayList<>();
        windMill = new WindMill(new Vec2(width/2, height-100),this);
    }

    @Override
    public void draw() {
        super.draw();
        background(255);
        box2d.step();
        if(mousePressed) {
             windMill.toggleMotor();
        }
        list.add(new B2DParticle(new Vec2(random(width), random(height)), random(2,5), false, this));
        windMill.render();
        // }
        list.forEach(B2DObjectBase::render);
        list.removeIf(B2DObjectBase::isDone);
//        plank.render();

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
        String[] args = {"WindMillExample"};
        WindMillExample gw = new WindMillExample();
        PApplet.runSketch(args,gw);
    }
}
