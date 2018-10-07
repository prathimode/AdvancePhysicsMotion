package prrathi.commonmodel.Box2DShape.Object;


import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.IWorldModelSource;

public class B2DParticle extends B2DObjectBase {

    // We need to keep track of a Body and a radius
    float r;

    public B2DParticle(Vec2 pos, float r_, boolean fixed, IWorldModelSource model) {
        super(model);
        r = r_;

        // Define a body
        BodyDef bd = new BodyDef();
        if (fixed) bd.type = BodyType.STATIC;
        else bd.type = BodyType.DYNAMIC;

        // Set its position
        bd.position = box2DP.coordPixelsToWorld(pos.x,pos.y);
        body = box2DP.world.createBody(bd);

        // Make the body's shape a circle
        // Make the body's shape a circle
        CircleShape cs = new CircleShape();
        cs.m_radius = box2DP.scalarPixelsToWorld(r);

        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        // Parameters that affect physics
        fd.density = 1;
        fd.friction = 0.3f;
        fd.restitution = 0.5f;

        body.createFixture(fd);
    }



    // Is the particle ready for deletion?
    public boolean isDone() {
        // Let's find the screen position of the particle
        Vec2 pos = box2DP.getBodyPixelCoord(body);
        // Is it off the bottom of the screen?
        if (pos.y > scene.height+r*2) {
            killBody();
            return true;
        }
        return false;
    }

    //
    public void renderMain() {
        // We look at each body and get its screen position
        Vec2 pos = box2DP.getBodyPixelCoord(body);
        // Get its angle of rotation
        float a = body.getAngle();
        scene.translate(pos.x,pos.y);
        scene.rotate(a);
        scene.ellipse(0,0,r*2,r*2);
        // Let's add a line so we can see the rotation
        scene.line(0,0,r,0);

    }


}