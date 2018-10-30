package com.prrathi;

import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.Example.Car;
import prrathi.commonmodel.Box2DShape.IB2DWorldModelSource;
import prrathi.commonmodel.Box2DShape.Object.B2DSurface;
import shiffman.box2d.Box2DProcessing;

public class CarOnSurface extends BasicGraphicWindow implements IB2DWorldModelSource {

    Box2DProcessing box2d;
    ArrayList<B2DObjectBase> list;
    B2DSurface surface;
    Car car;

    @Override
    public void settings() {
        super.settings();
        box2d = new Box2DProcessing(this);
        box2d.createWorld();
        list = new ArrayList<>();
        surface = new B2DSurface(this);
        surface.setEnableStyle(false);
        car = new Car(new Vec2(width/2, height/2), this);
    }

    @Override
    public void draw() {
        super.draw();
        strokeWeight(1);
        stroke(0);
        background(255);
        box2d.step();
        noFill();
        surface.render();
        car.render();

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
        String[] args = {"CarOnSurface"};
        CarOnSurface gw = new CarOnSurface();
        PApplet.runSketch(args,gw);
    }
}
