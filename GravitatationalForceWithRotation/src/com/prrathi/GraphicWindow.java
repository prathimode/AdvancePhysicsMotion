package com.prrathi;

import java.util.ArrayList;
import processing.core.PApplet;
public class GraphicWindow extends PApplet {
    boolean isSave = false;
    ArrayList<Mover> mList = new ArrayList<>();
    Attractor attractor;
    float angle = 0.01f;

    public void settings() {
        size(800,600);
        mList= new ArrayList<>();
        attractor = new Attractor(this,width/2, height/2, 5);
        for(int i =0;i<1;i++)
            mList.add(new Mover(this,random(10,300),random(10,300), 2.0f));
    }

    public void draw() {
        background(255);
        attractor.drag(mouseX, mouseY);
        attractor.hover(mouseX, mouseY);
        attractor.render();
        for(Mover mover :mList) {
            mover.applyForce(attractor.attract(mover));
            mover.rotate(angle);
            mover.update();
            mover.checkBoudaries();
            mover.render();
        }
        angle +=0.05;
    }

    @Override
    public void mousePressed() {
        super.mousePressed();
        attractor.isClicked(mouseX,mouseY);
    }

    @Override
    public void mouseReleased() {
        super.mouseReleased();
        attractor.stopDragging();
    }

        @Override
    public void mouseClicked() {
        super.mousePressed();
        if(!isSave)
        {
            saveFrame("output/prathimode-#######.png");
            isSave = false;
        }
        mList.add(new Mover(this,random(10,300),random(10,300), random(1,3)));
    }

    public static void main() {
        String[] args = {"GraphicWindow"};
        GraphicWindow gw = new GraphicWindow();
        PApplet.runSketch(args,gw);
    }
}
