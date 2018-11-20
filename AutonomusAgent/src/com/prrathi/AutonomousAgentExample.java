package com.prrathi;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;
import prrathi.commonmodel.BasicGraphicWindow;
import prrathi.commonmodel.IPRCWorldModelSource;
import prrathi.commonmodel.P3Shape.Example.P3Agent;
import prrathi.commonmodel.P3Shape.Example.P3ForceField;

public class AutonomousAgentExample extends BasicGraphicWindow implements IPRCWorldModelSource {

    ArrayList<P3Agent> agent;
    P3ForceField ff;
    boolean flag = false;

    public void settings() {
        super.settings();

        agent = new ArrayList<>();
        for(int i=0;i<200; i++) {
            agent.add(new P3Agent(this, random(width), random(height), random(1,5), random(0.1f,00.4f)));
        }
        ff = new P3ForceField(this,20);

    }

    public void draw() {
        background(255);
        agent.forEach((f)->{f.follow(ff);});
        agent.forEach(P3Agent::run);
        if(mousePressed) { ff.reset(); flag = !flag; }
        if(flag) ff.render();
    }

    public static void main() {
        String[] args = {"AutonomousAgentExample"};
        AutonomousAgentExample gw = new AutonomousAgentExample();
        PApplet.runSketch(args,gw);
    }

    @Override
    public PApplet getPApplet() {
        return this;
    }
}
