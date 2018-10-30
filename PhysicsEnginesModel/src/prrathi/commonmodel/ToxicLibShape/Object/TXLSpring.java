package prrathi.commonmodel.ToxicLibShape.Object;

import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.PRCBase;
import toxi.physics2d.VerletSpring2D;

public class TXLSpring extends PRCBase {

    public VerletSpring2D springBody;

    public TXLSpring(IPRCWorldModelSource scene, TXLParticle p1, TXLParticle p2, float restLength, float strength) {
        super(scene);
        springBody = new VerletSpring2D(p1.body, p2.body, restLength, strength);
    }

    @Override
    protected void PRCRender() {
        scene.line(springBody.a.x, springBody.a.y,
            springBody.b.x, springBody.b.y);
    }
}
