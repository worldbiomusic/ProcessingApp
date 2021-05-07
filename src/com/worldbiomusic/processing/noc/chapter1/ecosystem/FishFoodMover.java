package com.worldbiomusic.processing.noc.chapter1.ecosystem;

import processing.core.PApplet;

public class FishFoodMover extends Life{

    public FishFoodMover(PApplet p, float topspeed) {
	super(p, topspeed);
    }

    @Override
    public void update() {
	this.perlinNoiseUpdate();
    }

}
