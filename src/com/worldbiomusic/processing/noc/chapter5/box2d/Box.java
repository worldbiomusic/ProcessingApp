package com.worldbiomusic.processing.noc.chapter5.box2d;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Box extends Drawing {
	float w, h;

	public Box(PApplet p, Box2DProcessing box2d, float x, float y, float w, float h) {
		super(p, box2d);
		this.w = w;
		this.h = h;

		// body
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position.set(this.box2d.coordPixelsToWorld(x, y));
		this.body = this.box2d.createBody(bd);

		// shape
		PolygonShape ps = new PolygonShape();
		float bodyWidth = this.box2d.scalarPixelsToWorld(w / 2);
		float bodyHeight = this.box2d.scalarPixelsToWorld(h / 2);
		ps.setAsBox(bodyWidth, bodyHeight);

		// fixture
		FixtureDef fd = new FixtureDef();
		fd.shape = ps;

		fd.density = 100;
		fd.friction = 0.3f;
		fd.restitution = 0.5f;

		// attach fixture to body
		body.createFixture(fd);
	}

	public void display() {
		Vec2 pos = this.box2d.getBodyPixelCoord(this.body);
//		System.out.println("x: " + pos.x + "/y: " + pos.y);
		float angle = this.body.getAngle();

		this.p.pushMatrix();

		this.p.translate(pos.x, pos.y);
		this.p.rotate(-angle);

		this.p.fill(175);
		this.p.stroke(0);
		this.p.rectMode(PApplet.CENTER);
		this.p.rect(0, 0, this.w, this.h);

		this.p.popMatrix();
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
