package com.worldbiomusic.processing.chapter1;

import processing.core.PApplet;

public class FishFoodMover extends Mover{

    public FishFoodMover(PApplet p, float topspeed) {
	super(p, topspeed);
    }

    @Override
    public void update() {
	this.perlinNoiseUpdate();
    }

}
