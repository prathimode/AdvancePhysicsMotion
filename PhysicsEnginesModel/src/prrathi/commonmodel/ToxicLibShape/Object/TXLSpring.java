package prrathi.commonmodel.ToxicLibShape.Object;

import prrathi.commonmodel.ToxicLibShape.ITXLWorldModelSource;
import prrathi.commonmodel.ToxicLibShape.TXL2DBase;
import toxi.physics2d.VerletSpring2D;

public class TXLSpring extends TXL2DBase {

    public VerletSpring2D springBody;

    public TXLSpring(ITXLWorldModelSource scene, TXLParticle p1, TXLParticle p2, float restLength, float strength) {
        super(scene);
        springBody = new VerletSpring2D(p1.body, p2.body, restLength, strength);
        world.addSpring(springBody);
    }

    @Override
    protected void TXLRender() {
        scene.line(springBody.a.x, springBody.a.y,
            springBody.b.x, springBody.b.y);
    }
}
