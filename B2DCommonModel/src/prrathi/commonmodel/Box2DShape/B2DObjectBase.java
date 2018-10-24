package prrathi.commonmodel.Box2DShape;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

public abstract class B2DObjectBase extends B2DBase {

    public Body body;

    public B2DObjectBase(IWorldModelSource var1) {
        super(var1);
    }

    // This function removes the particle from the box2d world
    public void killBody() {
        box2DP.destroyBody(body);
        body = null;

    }

    public boolean isBodyOutOfWindow() {
        // Let's find the screen position of the particle
        Vec2 pos = box2DP.getBodyPixelCoord(body);
        // Is it off the bottom of the screen?
        if (pos.y > scene.height) {
            return true;
        }
        return false;
    }

    public  boolean contains(float x, float y){
        Vec2 worldPoint = box2DP.coordPixelsToWorld(x, y);
        Fixture f = body.getFixtureList();
        boolean inside = false;
        while(f != null) {
            inside |= f.testPoint(worldPoint);
            if(inside) break;
            f = f.getNext();
        }
        return inside;
    }
    public abstract boolean isDone();
}