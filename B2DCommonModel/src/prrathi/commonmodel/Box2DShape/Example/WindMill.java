package prrathi.commonmodel.Box2DShape.Example;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import prrathi.commonmodel.Box2DShape.B2DBase;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.B2DPairBase;
import prrathi.commonmodel.Box2DShape.Object.B2DBoxObject;
import prrathi.commonmodel.Box2DShape.Pair.B2DRevolutePair;
import prrathi.commonmodel.IWorldModelSource;

public class WindMill extends B2DBase {
    B2DObjectBase body1;
    B2DObjectBase body2;
    B2DPairBase connector;
    boolean motorstatus = true;

    public WindMill(B2DObjectBase body1, B2DObjectBase body2, IWorldModelSource source) {
        super(source);
        this.body1 = body1;
        this.body2 = body2;
        joinBody();
    }

    public WindMill(Vec2 pos, IWorldModelSource source) {
        super(source);
        this.body1 = new B2DBoxObject(pos,120,10, source);
        this.body2 = new B2DBoxObject(pos,10,40, source);
        body2.body.setType(BodyType.STATIC);
        joinBody();
    }

    private void joinBody() {
        connector = new B2DRevolutePair(body1,body2,source);
    }

    public void toggleMotor() {
        RevoluteJoint joint = ((RevoluteJoint) connector.joint);
        joint.isMotorEnabled();
        joint.enableMotor(!motorstatus);
    }


    @Override
    protected void renderMain() {
        body1.render();
        body2.render();
    }
}
