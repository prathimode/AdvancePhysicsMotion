package prrathi.commonmodel.P3Shape.Example;

import processing.core.PApplet;
import processing.core.PVector;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.PRCBase;

public class P3ForceField extends PRCBase {

    int col;
    int row;
    int resolution;
    PVector[][] data;

    public P3ForceField(IPRCWorldModelSource world, int resolution) {
        super(world);
        this.resolution = resolution;
        this.row = scene.width/resolution;
        this.col = scene.height/resolution;
        data = new PVector[row][col];
        create(10000);

    }

    private void create(float value) {
        // Reseed noise so we get a new flow field every time
        scene.noiseSeed((int)scene.random(value));
        float xx= 0.0f;
        for(int i= 0 ; i<row; i++) {
            float yy = 0.0f;
            for (int j = 0; j < col; j++) {
                float theta = scene.map(scene.noise(xx,yy),0,1,0,scene.TWO_PI);
                // Polar to cartesian coordinate transformation to get x and y components of the vector
                data[i][j] = new PVector(scene.cos(theta),scene.sin(theta));
//                data[i][j] = PVector.fromAngle(scene.noise(xx,yy));
                yy +=0.1f;
            }
            xx +=0.1f;
        }
    }

    public void reset() {
        create(scene.random(1000,1000));
    }

    public PVector lookup(PVector pos) {
        int r =  PApplet.constrain((int) pos.x / resolution, 0, row-1);
        int c =  PApplet.constrain( (int) pos.y / resolution, 0 , col-1);
        return data[r][c].copy();
    }

    @Override
    protected void PRCRender() {
        for(int i= 0 ; i<row; i++) {
            for(int j=0; j<col ;j++) {
                scene.pushMatrix();
                scene.translate(i* resolution, j* resolution);
                scene.rotate(data[i][j].heading());
                scene.line(0,0, resolution -3, 0);
                scene.popMatrix();
            }
        }
    }
}
