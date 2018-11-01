package prrathi.commonmodel.ToxicLibShape;

import processing.core.PApplet;
import prrathi.commonmodel.IPRCWorldModelSource;
import toxi.physics2d.VerletPhysics2D;

public interface ITXLWorldModelSource extends IPRCWorldModelSource {
    VerletPhysics2D getVerletWorld2D();
}
