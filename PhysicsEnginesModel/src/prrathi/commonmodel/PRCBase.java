package prrathi.commonmodel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import prrathi.commonmodel.Style.P3Style;


public abstract class PRCBase {

    @Getter(AccessLevel.PROTECTED)
    protected PApplet scene;
    @Setter
    @Getter
    public P3Style style;
    @Setter @Getter
    private boolean enableStyle = false;


    public PRCBase(IPRCWorldModelSource scene) {
        if (scene.getPApplet() == null)
            throw new AssertionError("PApplet is not set");
        else
            this.scene = scene.getPApplet();
    }

    public void render()
    {
        scene.pushMatrix();
        if(enableStyle) {
            scene.pushStyle();
            applyStyle();
        }
        PRCRender();
        if(enableStyle) {
            scene.popStyle();
        }
        scene.popMatrix();
    }

    private void applyStyle() {
        scene.fill(style.getFillColorFloat());
        scene.stroke(style.getStrokeColorFloat());
        scene.strokeWeight(style.getStrokeWidth());
    }
    protected abstract void PRCRender();
}
