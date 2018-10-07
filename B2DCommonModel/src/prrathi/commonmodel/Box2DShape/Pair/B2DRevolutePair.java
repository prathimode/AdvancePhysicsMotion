package prrathi.commonmodel.Box2DShape.Pair;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.B2DPairBase;
import prrathi.commonmodel.IWorldModelSource;

public class B2DRevolutePair extends B2DPairBase {

    public B2DRevolutePair(B2DObjectBase body1, B2DObjectBase body2, IWorldModelSource source) {
        super(body1, body2, source);
        joint = createJoint();

    }

    public B2DRevolutePair(B2DObjectBase body1, B2DObjectBase body2, boolean enbaleMotor, float motorSpeed, float maxTorque, IWorldModelSource source) {
        super(body1, body2, source);
        joint = createJoint();
        ((RevoluteJoint)joint).enableMotor(enbaleMotor);
        ((RevoluteJoint)joint).setMotorSpeed(motorSpeed);
        ((RevoluteJoint)joint).setMaxMotorTorque(maxTorque);
    }

    protected void renderMain() {
        body1.render();
        body2.render();
    }

    protected Joint createJoint() {
        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.initialize(body1.body,body2.body,body1.body.getWorldCenter());

        jointDef.motorSpeed = scene.PI*2;
        jointDef.enableMotor = true;
        jointDef.maxMotorTorque = 1000;
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
