package prrathi.commonmodel.Box2DShape.Example;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import prrathi.commonmodel.Box2DShape.B2DBase;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.B2DPairBase;
import prrathi.commonmodel.Box2DShape.Object.B2DBoxObject;
import prrathi.commonmodel.Box2DShape.Object.B2DParticle;
import prrathi.commonmodel.Box2DShape.Pair.B2DRevolutePair;
import prrathi.commonmodel.IWorldModelSource;

public class Car extends B2DBase {
    B2DBoxObject carBody;
    B2DParticle tyre1;
    B2DParticle tyre2;
    B2DRevolutePair t1Pair;
    B2DRevolutePair t2Pair;


    public Car(Vec2 pos, IWorldModelSource source) {
        super(source);
        carBody = new B2DBoxObject(pos,100,30,source);
        carBody.setEnableStyle(true);
        carBody.getStyle().setFillColorFloat(160);

        Vec2 t1pos = new Vec2(pos.x-28, pos.y+16);
        Vec2 t2pos = new Vec2(pos.x+28, pos.y+16);

        tyre1 = new B2DParticle(t1pos, 12, false, source);
        tyre2 = new B2DParticle(t2pos, 12, false, source);

        t1Pair = new B2DRevolutePair(tyre1, carBody, source);
        t2Pair = new B2DRevolutePair(tyre2, carBody, source);
    }

    @Override
    protected void renderMain() {
       t1Pair.render();
       t2Pair.render();
    }
}
