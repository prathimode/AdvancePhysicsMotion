package prrathi.commonmodel;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public interface IWorldModelSource {
    public PApplet getPApplet();
    public Box2DProcessing getBox2DProcessing();
}
