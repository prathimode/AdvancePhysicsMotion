package prrathi.commonmodel.Box2DShape;

import prrathi.commonmodel.IPRCWorldModelSource;
import shiffman.box2d.Box2DProcessing;

public interface IB2DWorldModelSource extends IPRCWorldModelSource {
    Box2DProcessing getBox2DProcessing();
}
