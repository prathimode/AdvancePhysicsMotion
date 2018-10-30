package prrathi.commonmodel;

import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import prrathi.commonmodel.Style.Style;

public abstract class PRCBase {

    protected PApplet scene;
    @Setter
    @Getter
    public Style style;
    @Setter @Getter
    boolean enableStyle = false;

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
