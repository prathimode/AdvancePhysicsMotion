package com.prrathi;


import com.prrathi.Objects.SquareObject;
import com.prrathi.Style.Style;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import processing.core.PApplet;
import processing.core.PVector;


@Data
public class Mover{
    @Getter(AccessLevel.PRIVATE) PApplet mScene;
    PVector accelaration, velocity;
    private float mass;
    SquareObject object;

    public Mover(PApplet scene, float x, float y, float mass)
    {
        this.mScene = scene;
        this.accelaration = new PVector(0,0);
        this.velocity = new PVector(0,0);
        this.mass = mass;
        object = new SquareObject(scene, x,y,mass*20);
        object.setStyle(new Style(100.0f,200.0f,1.0f));
    }

    public void rotate(float angle) {
        object.setRotation(angle);
    }
    // update the speed and acceleration. Should be called when update needed in position.
    // normally in each draw call
    public void update() {
        velocity.add(accelaration);
        object.position.add(velocity);
        accelaration.mult(0);
    }

    // Check for boundaries so that to update the movement direction
    public void checkBoudaries() {
        if(object.position.x > mScene.width )
        {
            object.position.x = mScene.width ;
            velocity.x *= -1;
        } else if( object.position.x <0 )
        {
            object.position.x = 0;
            velocity.x *=-1;
        }
        if(object.position.y > mScene.height) {
            velocity.y *=-1;
            object.position.y = mScene.height;
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
        accelaration.add(f.div(mass));
    }


    // Render the mover on the scene
    public void render() {
     object.render();
    }
}

