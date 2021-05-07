package com.worldbiomusic.processing.noc.chapter2.ecosystem;

import processing.core.PApplet;

public class FishFoodMover extends Obj{

    public FishFoodMover(PApplet p) {
	super(p);
    }
    
    public FishFoodMover(PApplet p, float xPos, float yPos, float mass, float r) {
	super(p, xPos, yPos, mass, r);
    }

    @Override
    public void update() {
	this.perlinNoiseUpdate();
    }

}
