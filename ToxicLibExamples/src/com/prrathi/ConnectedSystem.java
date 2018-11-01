package com.prrathi;

import processing.core.PApplet;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.ToxicLibShape.ITXLWorldModelSource;
import prrathi.commonmodel.ToxicLibShape.Object.TXLCluster;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;

public class ConnectedSystem extends BasicGraphicWindow  implements ITXLWorldModelSource {

    VerletPhysics2D physics2D;
    TXLCluster cluster;

    public void settings()
    {
        super.settings();
        physics2D = new VerletPhysics2D();
     //  physics2D.addBehavior(new GravityBehavior2D(new Vec2D(0,0.2f)));
        physics2D.setWorldBounds(new Rect(0,0,width, height));
        physics2D.setDrag(1f);
        setPoints(10);
    }

    public void setPoints(int a) {
        cluster = new TXLCluster(this, Vec2D.randomVector(), a,200);
    }


    public void draw() {
        background(255);
        physics2D.update();
        cluster.render();

    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
        setPoints((int)random(2, 30));
        if(frameCount%2 == 0) {
            cluster.toggleShowConnection();
        } else  {
            cluster.toggleShowParticle();
        }
    }

    @Override
    public PApplet getPApplet() {
        return this;
    }

    public static void main() {
        String[] args = {"simplePendlum"};
        ConnectedSystem gw = new ConnectedSystem();
        PApplet.runSketch(args,gw);
    }

    @Override
    public VerletPhysics2D getVerletWorld2D() {
        return physics2D;
    }
}
