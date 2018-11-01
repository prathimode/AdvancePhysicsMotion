package prrathi.commonmodel.ToxicLibShape.Example;

import prrathi.commonmodel.ToxicLibShape.ITXLWorldModelSource;
import prrathi.commonmodel.ToxicLibShape.Object.TXLParticle;
import toxi.geom.Vec2D;

public class TXLAttractor extends TXLParticle {

    public TXLAttractor(ITXLWorldModelSource scene, Vec2D loc, float forceRange, float mag) {
        super(scene, loc);
        addBehavior(forceRange, mag);
    }

    public TXLAttractor(ITXLWorldModelSource scene, Vec2D loc, float r, float forceRange, float mag) {
        super(scene, loc, r);
        addBehavior(forceRange, mag);
    }

}
