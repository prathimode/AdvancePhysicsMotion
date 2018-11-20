package prrathi.commonmodel.Style;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class P3Style {
    float fillColorFloat;
    float strokeColorFloat;
    float strokeWidth;

    public P3Style(float fillColor, float strokeColor, float strokeWidth) {
        this.fillColorFloat = fillColor;
        this.strokeColorFloat = strokeColor;
        this.strokeWidth = strokeWidth;
    }

}
