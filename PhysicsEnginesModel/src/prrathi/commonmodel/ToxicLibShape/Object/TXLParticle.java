package prrathi.commonmodel.ToxicLibShape.Object;

import java.util.ArrayList;
import lombok.Setter;
import prrathi.commonmodel.ToxicLibShape.ITXLWorldModelSource;
import prrathi.commonmodel.ToxicLibShape.TXL2DBase;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.behaviors.AttractionBehavior2D;

public class TXLParticle extends TXL2DBase {
    public VerletParticle2D body;
    public ArrayList<AttractionBehavior2D> behaviorList = new ArrayList<>();
    @Setter
    float radius;

    public TXLParticle(ITXLWorldModelSource scene, Vec2D loc) {
        super(scene);
        body = new VerletParticle2D(loc);
        radius = 16;
        world.addParticle(body);
    }

    public TXLParticle(ITXLWorldModelSource scene, Vec2D loc, float r) {
        super(scene);
        body = new VerletParticle2D(loc);
        radius = r;
        world.addParticle(body);
    }

    public void addBehavior(float radius, float magnitude ) {
        AttractionBehavior2D ba = new AttractionBehavior2D(this.body, radius, magnitude);
        world.addBehavior(ba);
        behaviorList.add(ba);
    }

    public void removeAllBehavior() {
       behaviorList.forEach((element) -> {world.removeBehavior(element);});
       behaviorList.clear();
    }

    public boolean contains(float x, float y) {
        Vec2D pos = new Vec2D(x,y);
        if(pos.isInCircle(new Vec2D(body.x, body.y),radius)) {
            return true;
        } else {
            return false;
        }

    }


    @Override
    protected void TXLRender() {
        scene.ellipse(body.x,body.y,radius, radius);
    }
}
