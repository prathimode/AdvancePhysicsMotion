package prrathi.commonmodel.Box2DShape;

import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import prrathi.commonmodel.Style.Style;
import shiffman.box2d.Box2DProcessing;

public abstract class B2DBase  {

    @Getter
    protected Box2DProcessing box2DP;
    protected PApplet scene;
    public IWorldModelSource source;
    @Setter @Getter
    public Style style;
    @Setter @Getter
    boolean enableStyle = false;

    public B2DBase(IWorldModelSource var1) {
        super();
        style = new Style(100,0f,2.0f);
        if (var1.getBox2DProcessing() == null)
            throw new AssertionError("Box2DProcessing is not set");
        else
            box2DP = var1.getBox2DProcessing();
        if (var1.getPApplet() == null)
            throw new AssertionError("PApplet is not set");
        else
            scene = var1.getPApplet();
        source =  var1;
    }

    public void render() {
        if(enableStyle) {
            scene.pushStyle();
            applyStyle();
        }
        scene.pushMatrix();
        renderMain();
        scene.popMatrix();

        if(enableStyle) {
            scene.popStyle();
        }
    }
    private void applyStyle() {
        scene.fill(style.getFillColorFloat());
        scene.stroke(style.getStrokeColorFloat());
        scene.strokeWeight(style.getStrokeWidth());
    }

    protected abstract void renderMain();

}
