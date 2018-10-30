package prrathi.commonmodel.Box2DShape.Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jbox2d.dynamics.BodyType;

@Getter
@Setter
@AllArgsConstructor
public class B2DShapeConfig {
    BodyType bodyType;
    float friction ;
    float density ;
    float restitution ;

    public B2DShapeConfig() {
        friction = 0.5f;
        density = 1.0f;
        restitution = 0.3f;
    }
}
