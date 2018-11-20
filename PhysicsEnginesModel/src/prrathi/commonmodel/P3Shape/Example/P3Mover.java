package prrathi.commonmodel.P3Shape.Example;



import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.P3Shape.BasicObject;
import prrathi.commonmodel.P3Shape.Object.P3SquareObject;
import prrathi.commonmodel.Style.P3Style;


@Data
public class P3Mover extends BasicObject {
    
    protected PVector acceleration, velocity;
    protected float mass;
    @Setter
    protected BasicObject object;

    public P3Mover(IPRCWorldModelSource scene, float x, float y, float mass)
    {
        super(scene, new PVector(x,y));
        this.acceleration = new PVector(0,0);
        this.velocity = new PVector(0,0);
        this.mass = mass;
        object = new P3SquareObject(scene, x,y,mass*20);
        object.setStyle(new P3Style(100.0f,200.0f,1.0f));
    }

    public P3Mover(IPRCWorldModelSource scene, float x, float y)
    {
        this(scene,x,y,-1);
    }

    // update the speed and acceleration. Should be called when update needed in position.
    // normally in each draw call
    public void update() {
        velocity.add(acceleration);
        object.position.add(velocity);
        acceleration.mult(0);
    }

    // Check for boundaries so that to update the movement direction
    public void checkBoudaries() {
        if(object.position.x > scene.width )
        {
            object.position.x = scene.width ;
            velocity.x *= -1;
        } else if( object.position.x <0 )
        {
            object.position.x = 0;
            velocity.x *=-1;
        }
        if(object.position.y > scene.height) {
            velocity.y *=-1;
            object.position.y = scene.height;
        } else if(object.position.y<0)
        {
            velocity.y *=-1;
            object.position.y =0;
        }
    }

    // Apply Force on the Mover
    public void applyForce(PVector f)
    {
       // System.out.println(f.x +" " + f.y);
        if(mass > 0) {
            acceleration.add(f.div(mass));
        } else {
            acceleration.add(f);
        }
    }



    // Render the mover on the scene
    protected void PRCRender() {
     object.render();
    }

    @Override
    public boolean contains(float x, float y) {
        return object.contains(x,y);
    }
}

