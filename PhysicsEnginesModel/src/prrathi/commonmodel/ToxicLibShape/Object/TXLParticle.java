package prrathi.commonmodel.ToxicLibShape.Object;

import lombok.Setter;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.PRCBase;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;

public class TXLParticle extends PRCBase {
    public VerletParticle2D body;
    @Setter
    float radius;

    public TXLParticle(IPRCWorldModelSource scene, Vec2D loc) {
        super(scene);
        body = new VerletParticle2D(loc);
        radius = 16;
    }

    public TXLParticle(IPRCWorldModelSource scene, Vec2D loc, float r) {
        super(scene);
        body = new VerletParticle2D(loc);
        radius = r;
    }


    @Override
    protected void PRCRender() {
        scene.ellipse(body.x,body.y,radius, radius);
    }
}
