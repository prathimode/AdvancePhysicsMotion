package prrathi.commonmodel;

import processing.core.PApplet;

public class BasicGraphicWindow extends PApplet {
    boolean isSave = false;

    public void settings() {
        size(800,600);
    }


//    @Override
//    public void mouseClicked() {
//        super.mousePressed();
//        if (!isSave) {
//            saveFrame("output/prathimode-#######.png");
//            isSave = false;
//        }
//
//    }

    public static void main() {
        String[] args = {"GraphicWindow"};
        BasicGraphicWindow gw = new BasicGraphicWindow();
        PApplet.runSketch(args,gw);
    }
}
