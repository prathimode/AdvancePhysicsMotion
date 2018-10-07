package prrathi.commonmodel.Box2DShape;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import prrathi.commonmodel.Box2DShape.B2DBase;
import prrathi.commonmodel.IWorldModelSource;

public abstract class B2DObjectBase extends B2DBase {

    public Body body;

    public B2DObjectBase(IWorldModelSource var1) {
        super(var1);
    }

    // This function removes the particle from the box2d world
    public void killBody() {
        box2DP.destroyBody(body);
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

    public abstract boolean isDone();
}