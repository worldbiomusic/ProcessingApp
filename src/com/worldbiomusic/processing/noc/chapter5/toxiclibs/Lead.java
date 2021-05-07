package com.worldbiomusic.processing.noc.chapter5.toxiclibs;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletSpring2D;

public class Lead extends Drawing {
	List<VerletSpring2D> springs;
	List<Particle> particles;

	public Lead(PApplet p, VerletPhysics2D physics) {
		super(p, physics);

		this.springs = new ArrayList<VerletSpring2D>();
		this.particles = new ArrayList<Particle>();

		for (int y = 0; y < this.p.height / 2; y += 10) {
			this.particles.add(new Circle(p, physics, new Vec2D(this.p.width / 2, y)));
		}
		// 맨 위의 것 고정
		this.particles.get(0).particle.lock();

		for (int i = 1; i < this.particles.size(); i++) {
			Particle p1 = this.particles.get(i - 1);
			Particle p2 = this.particles.get(i);
			this.springs.add(new VerletSpring2D(p1.particle, p2.particle, 10, 0.1f));
		}

		this.addObjectToPhysics();
	}

	@Override
	public void display() {
		// p 설정
		this.p.stroke(0);
		this.p.strokeWeight(1);
		this.p.noFill();
		
		this.p.beginShape();
		for (Particle p : this.particles) {
			float x = p.particle.x;
			float y = p.particle.y;
			this.p.vertex(x, y);
		}
		this.p.endShape();

		// 마지막 달린 원 그리기
		this.getLastCircle().display();
	}

	@Override
	public void addObjectToPhysics() {
		for (VerletSpring2D s : this.springs) {
			this.physics.addSpring(s);
		}
	}

	public Particle getLastCircle() {
		return this.particles.get(this.particles.size() - 1);
	}

}
