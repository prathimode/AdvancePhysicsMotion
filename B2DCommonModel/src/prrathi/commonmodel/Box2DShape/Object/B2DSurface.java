package prrathi.commonmodel.Box2DShape.Object;

import java.util.ArrayList;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import prrathi.commonmodel.Box2DShape.B2DObjectBase;
import prrathi.commonmodel.Box2DShape.IWorldModelSource;

public class B2DSurface extends B2DObjectBase{

    // We'll keep track of all of the surface points
    ArrayList<Vec2> surface;

    public B2DSurface(IWorldModelSource var1) {
        super(var1);
        float theta = 0;
        surface = new ArrayList<>();
        // Here we keep track of the screen coordinates of the chain
        for(float x=scene.width+10; x>-10; x-=5) {
            float y = scene.map(scene.sin(theta), -1, 1, scene.height-50, scene.height-80);
            theta += 0.15;
            surface.add(new Vec2(x, y));
        }
        createBox2DSurface();
    }

    public B2DSurface(ArrayList<Vec2> data, IWorldModelSource var1) {
        super(var1);
        this.surface = data;
        createBox2DSurface();
    }

    private void createBox2DSurface() {
        // This is what box2d uses to put the surface in its world
        ChainShape chain = new ChainShape();

        // We can add 3 vertices by making an array of 3 Vec2 objects
        Vec2[] vertices = new Vec2[surface.size()];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = box2DP.coordPixelsToWorld(surface.get(i));
        }

        chain.createChain(vertices, vertices.length);
        // The edge chain is now a body!
        BodyDef bd = new BodyDef();
        body = box2DP.world.createBody(bd);
        // Shortcut, we could define a fixture if we
        // want to specify frictions, restitution, etc.
        this.body.createFixture(chain, 1);
    }
    @Override
    protected void renderMain() {
        scene.beginShape();
        for (Vec2 vec2 : surface) {
            scene.vertex(vec2.x,vec2.y);
        }
        scene.endShape();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
