package prrathi.commonmodel.Box2DShape.Pair;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.B2DPairBase;
import prrathi.commonmodel.Box2DShape.IB2DWorldModelSource;

public class B2DMousePair extends B2DPairBase {

    Vec2 initialPos;
    public B2DMousePair(IB2DWorldModelSource source) {
        super(null, null,source);
        initialPos = null;
    }

    public void B2DRender() {
        if(shouldRender && joint != null) {
           Vec2 a = new Vec2(0,0);
           Vec2 b = new Vec2(0,0);
           joint.getAnchorA(a);
           joint.getAnchorB(b);
           a = box2DP.coordWorldToPixels(a);
           b = box2DP.coordWorldToPixels(b);
           scene.line(a.x,a.y,b.x,b.y);
        }
    }

    public void bind( Vec2 pos, B2DObjectBase b) {
        body1 = null;
        body2 = b;
        initialPos = pos;
        joint = createJoint();
    }

    protected Joint createJoint() {
        MouseJointDef jointDef = new MouseJointDef();
        jointDef.bodyA = box2DP.getGroundBody();
        jointDef.bodyB = body2.body;


        jointDef.target.set(box2DP.coordPixelsToWorld(initialPos.x, initialPos.y));
        jointDef.maxForce = 1000.0f * body2.body.m_mass;
        jointDef.frequencyHz = 5.0f;
        jointDef.dampingRatio = 0.9f;
        return  box2DP.world.createJoint(jointDef);
    }

    public void destroy() {
        if(joint != null) {
            killJoint();
            body1 = null;
            body2 = null;
            initialPos = null;
        }
    }

    // If it exists we set its target to the mouse position
    public void update(float x, float y) {
        if (joint != null) {
            // Always convert to world coordinates!
            Vec2 mouseWorld = box2DP.coordPixelsToWorld(x,y);
            ((MouseJoint) joint).setTarget(mouseWorld);
        }
    }


    public boolean isDone() {
        if(body1.isBodyOutOfWindow() && body2.isBodyOutOfWindow())
        {
            killJoint();
            body1.killBody();
            body2.killBody();
        }
        return false;
    }
}
