package com.worldbiomusic.processing.noc.chapter5.toxiclibs;

import java.util.ArrayList;
import java.util.List;

import com.worldbiomusic.processing.ProcessingMain;

import processing.event.MouseEvent;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.GravityBehavior2D;

public class Ch5Main_Toxiclibs extends ProcessingMain {

	VerletPhysics2D physics;

	List<Particle> particles;

	Drawing spring;

	Lead lead;

	public static void main(String[] args) {
		ProcessingMain.main(new String[] { Ch5Main_Toxiclibs.class.getName(), "1000", "600" });
	}

	@Override
	public void setup() {
		this.physics = new VerletPhysics2D();
		this.physics.setWorldBounds(new Rect(0, 0, width, height));
		this.physics.addBehavior(new GravityBehavior2D(new Vec2D(0, 0.5f)));

		// Particle
		this.particles = new ArrayList<>();
		this.particles.add(new Circle(this, this.physics, new Vec2D(100, 100)));
		this.particles.add(new Circle(this, this.physics, new Vec2D(200, 100)));
		this.particles.add(new Circle(this, this.physics, new Vec2D(300, 100)));

		// Spring
		this.spring = new Spring(this, this.physics, this.particles.get(0), this.particles.get(1), 80, 0.01f);

		// Lead
		this.lead = new Lead(this, physics);
	}

	@Override
	public void draw() {
		background(255);

		this.physics.update();

		for (Drawing p : this.particles) {
			p.display();
		}

		this.spring.display();

		this.lead.display();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
//		Particle d = this.particles.get(0);
//		VerletParticle2D p = d.particle;
//		p.lock();
//		p.setX(e.getX());
//		p.setY(e.getY());
//		p.unlock();

//		// lead
		VerletParticle2D p = this.lead.getLastCircle().particle;
		p.setX(e.getX());
		p.setY(e.getY());
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
