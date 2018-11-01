package prrathi.commonmodel.ToxicLibShape.Object;

import java.util.ArrayList;
import prrathi.commonmodel.PRCBase;
import prrathi.commonmodel.ToxicLibShape.ITXLWorldModelSource;
import prrathi.commonmodel.ToxicLibShape.TXL2DBase;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletMinDistanceSpring2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;

public class TXLCluster extends TXL2DBase {

    int numOfParticle;
    float radius;
    VerletPhysics2D world;
    boolean isParticleVisible = true;
    boolean isConnectionVisible = true;
    ArrayList<TXLParticle> particleArrayList = new ArrayList<>();
    ArrayList<TXLSpring> springArrayList = new ArrayList<>();

    public TXLCluster(ITXLWorldModelSource scene, Vec2D center, int numOfParticle, float radius) {
        super(scene);
        this.numOfParticle = numOfParticle;
        this.world = scene.getVerletWorld2D();
        this.radius = radius;
        for(int i = 0; i< numOfParticle ;i++) {
            particleArrayList.add(new TXLParticle(scene, center.add(Vec2D.randomVector()), 10));
            world.addParticle(particleArrayList.get(i).body);
        }
        for(int j = 0 ;j < numOfParticle - 1 ; j++ ) {
            for (int i = j +1 ; i < numOfParticle  ; i++) {
                TXLSpring s = new TXLSpring(scene, particleArrayList.get(i), particleArrayList.get(j), radius, 0.01f);
                world.addSpring(s.springBody);
                springArrayList.add(s);
            }
        }
    }

    public void toggleShowParticle() {
        isParticleVisible = !isParticleVisible;
    }

    public void toggleShowConnection() {
        isConnectionVisible = !isConnectionVisible;
    }

    // This functons connects one cluster to another
    // Each point of one cluster connects to each point of the other cluster
    // The connection is a "VerletMinDistanceSpring"
    // A VerletMinDistanceSpring is a spring which only enforces its rest length if the
    // current distance is less than its rest length. This is handy if you just want to
    // ensure objects are at least a certain distance from each other, but don't
    // care if it's bigger than the enforced minimum.
    void connect(TXLCluster other) {
        ArrayList<TXLParticle> otherNodes = other.getNodes();
        for (int i = 0; i < particleArrayList.size(); i++) {
            VerletParticle2D pi =particleArrayList.get(i).body;
            for (int j = 0; j < otherNodes.size(); j++) {
                VerletParticle2D pj = otherNodes.get(j).body;
                // Create the spring
                world.addSpring(new VerletMinDistanceSpring2D(pi,pj,(radius+other.radius)*0.5f,0.05f));
            }
        }
    }

    // Draw all the connections between this Cluster and another Cluster
    void showConnections(TXLCluster other) {
        ArrayList<TXLParticle> otherNodes = other.getNodes();
        for (int i = 0; i < particleArrayList.size(); i++) {
            VerletParticle2D pi = particleArrayList.get(i).body;
            for (int j = 0; j < otherNodes.size(); j++) {
                VerletParticle2D pj = otherNodes.get(j).body;
                if(isConnectionVisible) scene.line(pi.x,pi.y,pj.x,pj.y);
            }
        }
    }

    ArrayList<TXLParticle> getNodes() {
        return particleArrayList;
    }

    @Override
    protected void TXLRender() {
        if(isParticleVisible)
            particleArrayList.forEach(PRCBase::render);
        if(isConnectionVisible)
            springArrayList.forEach(PRCBase::render);
    }
}
