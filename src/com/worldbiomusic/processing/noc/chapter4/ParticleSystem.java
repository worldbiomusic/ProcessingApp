package com.worldbiomusic.processing.noc.chapter4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;
import processing.core.PImage;

public class ParticleSystem {
	PApplet p;
	List<Particle> particles;

	PVector loc;

	PImage img;

	public ParticleSystem(PApplet p, PVector loc, PImage img) {
		this.p = p;
		this.loc = loc;
		this.particles = new ArrayList<Particle>();
		this.img = img;
	}

	public void addParticle() {
		double r = Math.random();
//		if (r < 0.5) {
			this.particles.add(new Particle(this.p, this.loc, this.img));
//		} else {
//			this.particles.add(new Confetti(this.p, this.loc));
//		}
	}

	public void run() {
		Iterator<Particle> iterator = this.particles.iterator();
		while (iterator.hasNext()) {
			Particle p = iterator.next();
			p.run();

			if (p.isDead()) {
				iterator.remove();
			}
		}
	}

	public void applyForce(PVector force) {
		for (Particle p : this.particles) {
			p.applyForce(force);
		}
	}

	public void applyRepeller(Repeller repeller) {
		for (Particle p : this.particles) {
			PVector force = repeller.getRepelVector(p);
			p.applyForce(force);
		}
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
