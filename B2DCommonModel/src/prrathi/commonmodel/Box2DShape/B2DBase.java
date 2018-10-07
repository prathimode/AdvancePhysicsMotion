package prrathi.commonmodel.Box2DShape;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import processing.core.PApplet;
import prrathi.commonmodel.IWorldModelSource;
import prrathi.commonmodel.Style.Style;
import shiffman.box2d.Box2DProcessing;

public abstract class B2DBase  {

    @Getter
    protected Box2DProcessing box2DP;
    protected PApplet scene;
    @Setter @Getter
    public Style style;
    @Setter @Getter
    boolean enableStyle = true;

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
            scene.pushStyle();
        }
    }
    private void applyStyle() {
        scene.fill(style.getFillColorFloat());
        scene.stroke(style.getStrokeColorFloat());
        scene.strokeWeight(style.getStrokeWidth());
    }

    protected abstract void renderMain();

}
