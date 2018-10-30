package com.prrathi;

import java.util.ArrayList;
import org.jbox2d.dynamics.BodyType;
import processing.core.PApplet;

import prrathi.commonmodel.Box2DShape.B2DBase;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.Object.B2DBoxObject;
import prrathi.commonmodel.Box2DShape.Pair.B2DDistancePair;
import prrathi.commonmodel.Box2DShape.Object.B2DParticle;
import prrathi.commonmodel.Box2DShape.IWorldModelSource;
import shiffman.box2d.*;
import org.jbox2d.common.*;
import prrathi.commonmodel.BasicGraphicWindow;

public class GraphicWindowBridgeExample extends BasicGraphicWindow implements IWorldModelSource {

    Box2DProcessing box2d;
    ArrayList<B2DDistancePair> list;
    ArrayList<B2DObjectBase> boxes;
    B2DBoxObject plank;


    @Override
    public void settings() {
        super.settings();
        box2d = new Box2DProcessing(this);
        box2d.createWorld();
        list = new ArrayList<>();
        B2DObjectBase b1 = new B2DParticle(new Vec2(0, 300), 10, true,this);
        b1.body.setType(BodyType.STATIC);
        for (int i = 0; i < width; i = i + 10) {

            if (list.size() >= 1) {
                b1 = list.get(list.size() - 1).body2;
            }
            B2DObjectBase b2 = new B2DParticle(new Vec2(i + 10, 300), 5, false,this);
            if (i + 10 >= width) {
                b2.body.setType(BodyType.STATIC);
            }
            list.add(new B2DDistancePair(b1, b2, 0.1f, this));
        }
        boxes = new ArrayList<>();
    }


    @Override
    public void draw() {
        super.draw();
        background(255);
        box2d.step();
        if(mousePressed) {
            boxes.add(new B2DParticle(new Vec2(mouseX,mouseY), 20, false,this));
        }

        boxes.forEach(B2DBase::render);

        list.forEach(B2DBase::render);
//        list.removeIf(B2DDistancePair::isDone);


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
        String[] args = {"GraphicWindowPair"};
        GraphicWindowBridgeExample gw = new GraphicWindowBridgeExample();
        PApplet.runSketch(args,gw);
    }
}
