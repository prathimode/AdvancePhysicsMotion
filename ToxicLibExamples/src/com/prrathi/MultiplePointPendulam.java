package com.prrathi;

import java.util.ArrayList;
import processing.core.PApplet;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.PRCBase;
import prrathi.commonmodel.ToxicLibShape.Object.TXLParticle;
import prrathi.commonmodel.ToxicLibShape.Object.TXLSpring;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.GravityBehavior2D;

public class MultiplePointPendulam extends BasicGraphicWindow implements IPRCWorldModelSource {

    VerletPhysics2D physics2D;
    ArrayList<TXLParticle> particleArrayList = new ArrayList<>();
    ArrayList<TXLSpring> springArrayList = new ArrayList<>();
    float totalLength = 180;
    float number = 20;
    float unitLength = totalLength/number;

    public void settings()
    {
        super.settings();
        physics2D = new VerletPhysics2D();
        physics2D.addBehavior(new GravityBehavior2D(new Vec2D(0,0.2f)));
        physics2D.setDrag(0.1f);
        physics2D.setWorldBounds(new Rect(0,0,width, height));

        for(int i =0 ;i< number ;i++) {
            particleArrayList.add(new TXLParticle(this, new Vec2D(width / 2 ,  i * unitLength), 10));
        }
        for(int i =0 ;i<particleArrayList.size() - 1 ;i++) {
            springArrayList.add(new TXLSpring(this, particleArrayList.get(i) , particleArrayList.get(i+1), unitLength, 0.01f));
        }
        particleArrayList.get(0).body.lock();
        particleArrayList.get((int)number-1).setRadius(40);

        for(int i =0 ;i<number;i++) {
            physics2D.addParticle(particleArrayList.get(i).body);
        }
        for(int i =0 ;i<number-1;i++) {
            physics2D.addSpring(springArrayList.get(i).springBody);
        }
    }

    public void draw() {
        background(255);
        physics2D.update();

        particleArrayList.get(0).render();

       beginShape();
       for(int j=0 ;j< number ;j++) {
           vertex(particleArrayList.get(j).body.x, particleArrayList.get(j).body.y);
       }
       endShape();
       particleArrayList.get((int)number-1).render();
        if (mousePressed) {
            particleArrayList.get(particleArrayList.size()-1).body.lock();
            particleArrayList.get(particleArrayList.size()-1).body.x = mouseX;
            particleArrayList.get(particleArrayList.size()-1).body.y = mouseY;
            particleArrayList.get(particleArrayList.size()-1).body.unlock();
        }
    }


    @Override
    public PApplet getPApplet() {
        return this;
    }

    public static void main() {
        String[] args = {"simplePendlum"};
        MultiplePointPendulam gw = new MultiplePointPendulam();
        PApplet.runSketch(args,gw);
    }
}
