package com.worldbiomusic.processing.noc.chapter5.box2d;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Circle extends Drawing {
	float r;
	int[] color;

	public Circle(PApplet p, Box2DProcessing box2d, float x, float y, float r, BodyType bodyType) {
		super(p, box2d);

		// 반지름 설정
		this.r = r;

		this.color = new int[3];
		this.changeColor();

		// body
		BodyDef bd = new BodyDef();
		bd.type = bodyType;
		bd.position.set(this.box2d.coordPixelsToWorld(x, y));
		this.body = this.box2d.createBody(bd);

		// shape
		CircleShape ps = new CircleShape();
		float radius = this.box2d.scalarPixelsToWorld(this.r);
		ps.setRadius(radius);

		// fixture
		FixtureDef fd = new FixtureDef();
		fd.shape = ps;

		fd.density = 1;
		fd.friction = 0.3f;
		fd.restitution = 0.8f;

		// attach fixture to body
		body.createFixture(fd);
	}

	public void display() {

		Vec2 pos = this.box2d.getBodyPixelCoord(this.body);
		float angle = this.body.getAngle();

		this.p.pushMatrix();

		this.p.translate(pos.x, pos.y);
		this.p.rotate(-angle);

		this.p.fill(this.color[0], this.color[1], this.color[2]);
		this.p.stroke(0);
		this.p.ellipseMode(PApplet.CENTER);
		this.p.ellipse(0, 0, this.r * 2, this.r * 2);

		this.p.popMatrix();
	}

	public void changeColor() {
		this.color[0] = (int) p.random(255);
		this.color[1] = (int) p.random(255);
		this.color[2] = (int) p.random(255);
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
