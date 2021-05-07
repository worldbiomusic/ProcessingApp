package com.worldbiomusic.processing.noc.chapter5.toxiclibs;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;

public class Circle extends Particle {

	public Circle(PApplet p, VerletPhysics2D physics, Vec2D loc) {
		super(p, physics, loc);
	}

	@Override
	public void display() {
		this.p.fill(100);
		this.p.stroke(0);
		this.p.ellipse(this.particle.x, this.particle.y, 20, 20);
	}

}
