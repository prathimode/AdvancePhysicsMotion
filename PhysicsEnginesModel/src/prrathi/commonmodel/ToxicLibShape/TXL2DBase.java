package prrathi.commonmodel.ToxicLibShape;

import prrathi.commonmodel.PRCBase;
import toxi.physics2d.VerletPhysics2D;

public abstract class TXL2DBase extends PRCBase {

    protected VerletPhysics2D world;

    public TXL2DBase(ITXLWorldModelSource scene) {
        super(scene);
        world = scene.getVerletWorld2D();
    }

    @Override
    protected void PRCRender() {
        TXLRender();
    }

    protected abstract void TXLRender();
}
