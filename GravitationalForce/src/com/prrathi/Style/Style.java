package com.prrathi.Style;

import lombok.Data;

@Data
public class Style {
    float fillColorFloat;
    float strokeColorFloat;
    float strokeWidth;

    public Style(float v, float v1, float v2) {
        fillColorFloat = v;
        strokeColorFloat = v1;
        strokeWidth = v2;
    }
}
