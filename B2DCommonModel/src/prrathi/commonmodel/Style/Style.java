package prrathi.commonmodel.Style;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Style {
    float fillColorFloat;
    float strokeColorFloat;
    float strokeWidth;

    public Style(float fillColor, float strokeColor, float strokeWidth) {
        this.fillColorFloat = fillColor;
        this.strokeColorFloat = strokeColor;
        this.strokeWidth = strokeWidth;
    }
}
