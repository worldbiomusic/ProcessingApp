package com.worldbiomusic.processing.noc.chapter2;

import processing.core.PApplet;

public class GravityMover extends Ch2Mover{

    

    public GravityMover(PApplet p, float xPos, float yPos, float mass, float r) {
	super(p, xPos, yPos, mass, r);
    }

    @Override
    public void checkEdge() {
	this.noCheckEdge();
    }
}
