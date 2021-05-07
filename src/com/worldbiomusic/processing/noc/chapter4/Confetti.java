package com.worldbiomusic.processing.noc.chapter4;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public class Confetti extends Particle {

	Confetti(PApplet p, PVector loc) {
		super(p, loc, null);
	}

	@Override
	public void display() {

		float theta = PApplet.map(this.location.x, 0, this.p.width, 0, PApplet.TWO_PI * 2);

		this.p.pushMatrix();
		this.p.rectMode(PApplet.CENTER);
		this.p.fill(175, this.lifeSpan);
		this.p.stroke(0);
		this.p.translate(this.location.x, this.location.y);
		this.p.rotate(theta);
		this.p.rect(0, 0, 8, 8);
		this.p.popMatrix();
	}
}
