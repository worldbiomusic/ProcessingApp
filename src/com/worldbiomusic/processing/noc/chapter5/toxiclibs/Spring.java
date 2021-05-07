package com.worldbiomusic.processing.noc.chapter5.toxiclibs;

import processing.core.PApplet;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletSpring2D;

public class Spring extends Drawing {
	public VerletSpring2D spring;
	Particle p1, p2;

	public Spring(PApplet p, VerletPhysics2D physics, Particle p1, Particle p2, float len, float power) {
		super(p, physics);
		this.spring = new VerletSpring2D(p1.particle, p2.particle, len, power);
		this.p = p;
		this.p1 = p1;
		this.p2 = p2;
		this.addObjectToPhysics();
	}

	@Override
	public void display() {
		// line
		float x1 = p1.particle.x;
		float y1 = p1.particle.y;
		float x2 = p2.particle.x;
		float y2 = p2.particle.y;

		this.p.strokeWeight(2);
		this.p.stroke(0);
		this.p.line(x1, y1, x2, y2);

		// particles
		this.p1.display();
		this.p2.display();
	}

	@Override
	public void addObjectToPhysics() {
		this.physics.addSpring(this.spring);
	}

}
