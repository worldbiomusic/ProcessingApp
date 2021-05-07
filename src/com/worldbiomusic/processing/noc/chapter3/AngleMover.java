package com.worldbiomusic.processing.noc.chapter3;

import processing.core.PApplet;

public class AngleMover extends Ch3Mover {

	public AngleMover(PApplet p, float xPos, float yPos, float mass, float r) {
		super(p, xPos, yPos, mass, r);
	}

	@Override
	public void update() {
		this.generalUpdate();
	}

	@Override
	public void checkEdge() {
		this.checkEdgeLikePassThrough();
	}
}
