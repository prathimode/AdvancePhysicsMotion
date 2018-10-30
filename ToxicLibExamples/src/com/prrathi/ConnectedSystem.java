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

public class ConnectedSystem extends BasicGraphicWindow  implements IPRCWorldModelSource {

    VerletPhysics2D physics2D;
    ArrayList<TXLParticle> particleArrayList = new ArrayList<>();
    ArrayList<TXLSpring> springArrayList = new ArrayList<>();
    int count = 5;



    public void settings()
    {
        super.settings();
        physics2D = new VerletPhysics2D();
     //  physics2D.addBehavior(new GravityBehavior2D(new Vec2D(0,0.2f)));
        physics2D.setWorldBounds(new Rect(0,0,width, height));
        physics2D.setDrag(1f);
        setPoints(count);


        //particleArrayList.get(0).body.lock();
    }

    private void setPoints(int totalParticle) {
        for(int i =0 ;i< totalParticle ;i++) {
            particleArrayList.add(new TXLParticle(this, new Vec2D(random(100,400) ,  random(100,400)), 10));
            physics2D.addParticle(particleArrayList.get(i).body);
        }
        float v = 300;
        for(int j = 0 ;j < totalParticle - 1 ; j++ ) {
            for (int i = j +1 ; i < totalParticle  ; i++) {
                TXLSpring s = new TXLSpring(this, particleArrayList.get(i), particleArrayList.get(j), v, 0.01f);
                physics2D.addSpring(s.springBody);
                springArrayList.add(s);
            }
        }
    }

    public void draw() {
        background(255);
        physics2D.update();
      //  particleArrayList.forEach(PRCBase::render);
        springArrayList.forEach(PRCBase::render);
     //   println("yes");

    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
        particleArrayList.forEach((a)->physics2D.removeParticle(a.body));
        springArrayList.forEach((a)->physics2D.removeSpring(a.springBody));
        particleArrayList.clear();
        springArrayList.clear();
        setPoints((int)random(2, 30));
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
}
