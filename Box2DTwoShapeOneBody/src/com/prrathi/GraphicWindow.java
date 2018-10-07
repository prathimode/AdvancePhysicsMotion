package com.prrathi;

import java.util.ArrayList;
import org.jbox2d.dynamics.BodyType;
import processing.core.PApplet;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.Object.B2DBoxObject;
import prrathi.commonmodel.Box2DShape.Pair.B2DDistancePair;
import prrathi.commonmodel.Box2DShape.Object.B2DShapeConfig;
import prrathi.commonmodel.IWorldModelSource;
import shiffman.box2d.*;
import org.jbox2d.common.*;
import prrathi.commonmodel.BasicGraphicWindow;

public class GraphicWindow extends BasicGraphicWindow implements IWorldModelSource {

    Box2DProcessing box2d;
    ArrayList<B2DBoxObject> list;
    B2DBoxObject plank;
    B2DDistancePair pair;

    @Override
    public void settings() {
        super.settings();
        box2d = new Box2DProcessing(this);
        box2d.createWorld();
        list = new ArrayList<>();
        plank = new B2DBoxObject(new Vec2(200, 400), 400, 10, new B2DShapeConfig(BodyType.STATIC, 0.5f, 1, 0.3f), this );
    }

    @Override
    public void draw() {
        super.draw();
        background(255);
        box2d.step();
       // if(mousePressed) {
            list.add(new B2DBoxObject(new Vec2(random(width), random(height)), 20, 8, this));
       // }
        list.forEach(B2DObjectBase::render);
        list.removeIf(B2DBoxObject::isDone);
        plank.render();

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
        String[] args = {"GraphicWindow"};
        GraphicWindow gw = new GraphicWindow();
        PApplet.runSketch(args,gw);
    }
}
