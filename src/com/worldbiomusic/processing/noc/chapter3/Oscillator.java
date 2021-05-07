package com.worldbiomusic.processing.noc.chapter3;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public class Oscillator {
	PApplet P;
	PVector angle, velocity, amplitude;

	Oscillator(PApplet P) {
		this.P = P;
		this.angle = new PVector(0, 0);
		this.velocity = new PVector(0.03f, 0.00f);
		this.amplitude = new PVector(P.width / 2, P.height / 2);
	}

	void oscillator() {
		this.angle.add(this.velocity);
	}

	void display() {

		float x = this.amplitude.x * PApplet.sin(this.angle.x);

		float y = this.amplitude.y * PApplet.sin(this.angle.y);

		P.pushMatrix();

		P.translate(P.width / 2, P.height / 2);
		P.stroke(0);
		P.fill(175);
		P.ellipseMode(PApplet.CENTER);

		P.line(0, -P.height / 2, x, y);
		P.ellipse(x, y, 20, 20);

		P.popMatrix();
	}
}
