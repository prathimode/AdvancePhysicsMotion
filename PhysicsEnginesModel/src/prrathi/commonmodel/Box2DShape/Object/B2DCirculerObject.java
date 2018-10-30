package prrathi.commonmodel.Box2DShape.Object;

import lombok.Getter;
import lombok.Setter;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.IB2DWorldModelSource;

@Getter
@Setter
public  class B2DCirculerObject extends B2DObjectBase {
    private float r;


    public B2DCirculerObject(Vec2 pos, float r, B2DShapeConfig config, IB2DWorldModelSource var1) {
        super(var1);
        this.r = r;

        this.body = createBody(pos, config);
    }

    public B2DCirculerObject(Vec2 pos, float r , IB2DWorldModelSource var1) {
        super(var1);
        this.r = r;
        B2DShapeConfig b2DShapeConfig = new B2DShapeConfig(BodyType.DYNAMIC, 0.3f, 1, 0.1f);
        this.body = createBody(pos, b2DShapeConfig);
    }

    @Override
    public boolean isDone() {
        // Let's find the screen position of the particle
        Vec2 pos = box2DP.getBodyPixelCoord(body);
        // Is it off the bottom of the screen?
        if (pos.y > scene.height) {
            killBody();
            return true;
        }
        return false;
    }

    @Override
    protected void B2DRender() {
        Vec2 pos = box2DP.getBodyPixelCoord(body);
        scene.translate(pos.x,pos.y);
        scene.rotate(-1 * body.getAngle());
        scene.ellipseMode(scene.CENTER);
        scene.ellipse(0, 0, r, r);
    }

    private Body createBody(Vec2 pos, B2DShapeConfig shapeConfig) {
        //Create Body defination
        BodyDef bdef = new BodyDef();
        bdef.setType(shapeConfig.getBodyType());
        bdef.setPosition(box2DP.coordPixelsToWorld(pos));

        // Build Body
        body = box2DP.createBody(bdef);

        // Define a polygon (this is what we use for a rectangle)
        CircleShape shape = new CircleShape();
        shape.m_radius = box2DP.scalarPixelsToWorld(r);

        // Define a fixture
        FixtureDef fs = new FixtureDef();
        fs.setDensity(shapeConfig.getDensity());
        fs.setFriction(shapeConfig.getFriction());
        fs.setRestitution(shapeConfig.getRestitution());
        fs.setShape(shape);

        // Attach Fixture to Body
        body.createFixture(fs);
        return body;
    }



}
