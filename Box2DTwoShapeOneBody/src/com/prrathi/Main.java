package com.prrathi;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String type;
        type = "carOnSurface";

        if (type.compareToIgnoreCase("bridge") == 0) {
            GraphicWindowBridgeExample.main();
        }
        else if(type.compareToIgnoreCase("windmill") == 0) {
            WindMillExample.main();
        } else if(type.compareToIgnoreCase("carOnSurface") == 0) {
            CarOnSurface.main();
        }
    }
}
