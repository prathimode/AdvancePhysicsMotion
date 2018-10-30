package prrathi.commonmodel.Box2DShape;

import lombok.Getter;
import prrathi.commonmodel.PRCBase;
import shiffman.box2d.Box2DProcessing;

public abstract class B2DBase extends PRCBase {

    @Getter
    protected Box2DProcessing box2DP;
    protected IB2DWorldModelSource source;

    public B2DBase(IB2DWorldModelSource var1) {
        super(var1);
        if (var1.getBox2DProcessing() == null)
            throw new AssertionError("Box2DProcessing is not set");
        else
            box2DP = var1.getBox2DProcessing();
        source =  var1;
    }

    public void PRCRender() {
        B2DRender();
    }
    protected abstract void B2DRender();
}
