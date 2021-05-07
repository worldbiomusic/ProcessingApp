package com.worldbiomusic.processing.noc.chapter2.ecosystem;

import processing.core.PApplet;

public class Shark extends Life{

    public Shark(PApplet p) {
	super(p);
	
	this.addFoodList(Anchovy.class);
    }
}
