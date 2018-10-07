package prrathi.commonmodel.Box2DShape;

import org.jbox2d.dynamics.joints.Joint;
import prrathi.commonmodel.IWorldModelSource;

public abstract class B2DPairBase extends B2DBase {

    public B2DObjectBase body1;
    public B2DObjectBase body2;
    public Joint joint;


    public B2DPairBase(B2DObjectBase body1, B2DObjectBase body2, IWorldModelSource var1) {
        super(var1);
        this.body1 = body1;
        this.body2 = body2;
    }

    protected abstract Joint createJoint();
    public abstract boolean isDone();
}