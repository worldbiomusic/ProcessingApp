package com.worldbiomusic.processing.noc.chapter4;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;
import processing.core.PImage;

public class Repeller {

	PApplet p;
	PVector loc;
	float r;

	public Repeller(PApplet p, float x, float y, float r) {
		this.p = p;
		this.loc = new PVector(x, y);
		this.r = r;
	}

	public void display() {
		try {
			this.p.fill(150);
			this.p.stroke(70);
			this.p.ellipse(this.loc.x, this.loc.y, this.r, this.r);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public PVector getRepelVector(Particle p) {
		PVector force = PVector.sub(p.location, this.loc);

		float distance = PApplet.constrain(force.mag(), 5, 100);

		force.normalize();

		float repelForce = PVector.G * 1000 / (distance * distance);

		force.mult(repelForce);

		return force;
	}
}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
