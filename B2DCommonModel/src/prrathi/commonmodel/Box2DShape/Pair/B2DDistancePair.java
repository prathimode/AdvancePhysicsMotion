package prrathi.commonmodel.Box2DShape.Pair;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.Joint;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.B2DPairBase;
import prrathi.commonmodel.IWorldModelSource;

public class B2DDistancePair extends B2DPairBase {


    public B2DDistancePair(B2DObjectBase body1, B2DObjectBase body2, float length, IWorldModelSource source) {
        super(body1,body2, source);
        joint = createJoint();
        ((DistanceJoint)joint).setLength(length);
    }

    public void renderMain() {
        body1.render();
        body2.render();
        Vec2 b1 = box2DP.getBodyPixelCoord(body1.body);
        Vec2 b2 = box2DP.getBodyPixelCoord(body2.body);
        scene.line(b1.x,b1.y,b2.x,b2.y);
    }


    protected Joint createJoint() {
        DistanceJointDef jointDef = new DistanceJointDef();
        jointDef.bodyA = body1.body;
        jointDef.bodyB = body2.body;

        jointDef.dampingRatio = 0.0f;
        jointDef.length = box2DP.scalarPixelsToWorld(10);
        jointDef.frequencyHz = 0;
        return  box2DP.world.createJoint(jointDef);
    }

    public boolean isDone() {
        if(body1.isBodyOutOfWindow() && body2.isBodyOutOfWindow())
        {
            body1.killBody();
            body2.killBody();
            return true;
        }
        return false;
    }
}
