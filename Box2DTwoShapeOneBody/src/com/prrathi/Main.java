package com.prrathi;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String type;
        type = "CollisonExample";

        if (type.compareToIgnoreCase("bridge") == 0) {
            GraphicWindowBridgeExample.main();
        }
        else if(type.compareToIgnoreCase("windmill") == 0) {
            WindMillExample.main();
        } else if(type.compareToIgnoreCase("carOnSurface") == 0) {
            CarOnSurface.main();
        } else if(type.compareToIgnoreCase("MouseJointExample") == 0) {
            MouseJointExample.main();
        } else if(type.compareToIgnoreCase("CollisonExample") == 0) {
            CollisonExample.main();
        }
    }
}
