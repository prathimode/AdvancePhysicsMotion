package com.prrathi;

import java.util.ArrayList;
import processing.core.PApplet;

public class GraphicWindow extends PApplet {
    boolean isSave = false;
    float angle = 0.01f;
    SpaceShip sp;

    public void settings() {
        size(800,600);

        sp = new SpaceShip(this, width/2, height/2, 50);

    }

    public void draw() {
        background(255);
        sp.update();
        sp.checkBoundries();
        sp.render();
        if(keyPressed) {
            if(key == CODED && keyCode == LEFT) {
                sp.turn(-0.03f);
            } else if(key == CODED && keyCode == RIGHT) {
                sp.turn(0.03f);
            }  else if(key == 'z' || key == 'Z' ) {
                sp.trust(-0.3f);
            }
        }
    }

    @Override
    public void mousePressed() {
        super.mousePressed();
    }

    @Override
    public void mouseReleased() {
        super.mouseReleased();
    }

        @Override
    public void mouseClicked() {
        super.mousePressed();
        if(!isSave)
        {
            saveFrame("output/prathimode-#######.png");
            isSave = false;
        }

    }



    public static void main() {
        String[] args = {"GraphicWindow"};
        GraphicWindow gw = new GraphicWindow();
        PApplet.runSketch(args,gw);
    }



}
