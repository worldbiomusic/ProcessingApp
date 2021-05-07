package com.worldbiomusic.processing.noc.chapter5.toxiclibs;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;

public abstract class Particle extends Drawing {
	public VerletParticle2D particle;

	public Particle(PApplet p, VerletPhysics2D physics, Vec2D loc) {
		super(p, physics);
		this.particle = new VerletParticle2D(loc);
		this.addObjectToPhysics();
	}

	@Override
	public void addObjectToPhysics() {
		this.physics.addParticle(this.particle);
	}
}