package com.worldbiomusic.processing.noc.chapter4;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;
import processing.core.PImage;

public class Particle {
	PApplet p;
	PVector location, velocity, accelation;
	float mass;

	float lifeSpan;

	PImage img;

	Particle(PApplet p, PVector loc, PImage img) {
		this.p = p;

		this.location = loc.get();
		this.velocity = new PVector(this.p.random(-2, 2), this.p.random(-2, 0));
		this.accelation = new PVector(0, 0.05f);
		this.mass = 10;

		this.lifeSpan = 255;
		this.img = img;
	}

	public void run() {
		this.update();
//		this.display();
		this.render();
	}

	public void update() {
		this.velocity.add(this.accelation);
		this.location.add(this.velocity);
		this.lifeSpan -= 2.0;

		this.accelation.mult(0);
	}

	public void display() {
		this.p.stroke(2, this.lifeSpan);
		this.p.fill(0, this.lifeSpan);
		this.p.ellipse(this.location.x, this.location.y, 8, 8);
	}

	public boolean isDead() {
		if (this.lifeSpan < 0) {
			return true;
		} else {
			return false;
		}
	}

	public void applyForce(PVector forceVector) {
		// A = F / M
		PVector force = forceVector.get();
		force.div(this.mass);
		this.accelation.add(force);
	}

	public void render() {
		this.p.blendMode(PApplet.ADD);
		this.p.imageMode(PApplet.CENTER);
//		this.p.tint(0, 153, 204, this.lifeSpan);
		this.p.tint(this.p.mouseX % 256, this.p.mouseY % 256, (this.p.mouseX * this.p.mouseY) % 256, this.lifeSpan);
		this.p.image(this.img, this.location.x, this.location.y);
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
//
//
//
//
