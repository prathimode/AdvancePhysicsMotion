package com.prrathi;

import java.util.ArrayList;
import org.jbox2d.dynamics.BodyType;
import processing.core.PApplet;
import prrathi.commonmodel.Box2DShape.B2DBase;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.Object.B2DBoxObject;
import prrathi.commonmodel.Box2DShape.Object.B2DCirculerObject;
import prrathi.commonmodel.Box2DShape.Pair.B2DDistancePair;
import prrathi.commonmodel.Box2DShape.Object.B2DShapeConfig;
import prrathi.commonmodel.Box2DShape.IWorldModelSource;
import shiffman.box2d.*;
import org.jbox2d.common.*;
import prrathi.commonmodel.BasicGraphicWindow;

public class GraphicWindowPair extends BasicGraphicWindow implements IWorldModelSource {

    Box2DProcessing box2d;
    ArrayList<B2DDistancePair> list;
    B2DBoxObject plank;


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
        float w = random(width);
        float h = random(height);
        B2DObjectBase b1 = new B2DCirculerObject(new Vec2(w,h ), 15, this);
        B2DObjectBase b2 = new B2DCirculerObject(new Vec2(w+ random(-1,1), h + random(-1,1)), 15, this);

       // B2DBoxObject b2 = new B2DBoxObject(new Vec2(w+ random(-1,1), h + random(-1,1)), 15,15, this);

        list.add(new B2DDistancePair(b1,b2,20, this));
        // }
        list.forEach(B2DBase::render);
        list.removeIf(B2DDistancePair::isDone);
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
        String[] args = {"GraphicWindowPair"};
        GraphicWindowPair gw = new GraphicWindowPair();
        PApplet.runSketch(args,gw);
    }
}
