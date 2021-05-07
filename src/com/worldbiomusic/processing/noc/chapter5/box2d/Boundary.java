package com.worldbiomusic.processing.noc.chapter5.box2d;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Boundary {
	PApplet p;
	Box2DProcessing box2d;

	float x, y, w, h;

	Body b;

	public Boundary(PApplet p, Box2DProcessing box2d, float x, float y, float w, float h) {
		this.p = p;
		this.box2d = box2d;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		BodyDef bd = new BodyDef();
		bd.position.set(this.box2d.coordPixelsToWorld(this.x, this.y));
		bd.type = BodyType.STATIC;
		this.b = this.box2d.createBody(bd);

		float boxWidth = this.box2d.scalarPixelsToWorld(this.w / 2);
		float boxHeight = this.box2d.scalarPixelsToWorld(this.h / 2);

		PolygonShape ps = new PolygonShape();
		ps.setAsBox(boxWidth, boxHeight);

		this.b.createFixture(ps, 1);

	}

	public void display() {
		this.p.fill(0);
		this.p.stroke(0);
		this.p.rectMode(PApplet.CENTER);
		this.p.rect(this.x, this.y, this.w, this.h);
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
