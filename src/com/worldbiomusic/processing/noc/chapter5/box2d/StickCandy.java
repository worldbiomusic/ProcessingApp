package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.util.Random;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class StickCandy extends Drawing {
	float r;
	float w, h;
	float yOffset;


	Random random;
	int red, green, blue, alpha;

	public StickCandy(PApplet p, Box2DProcessing box2d, Vec2 center) {
		super(p,box2d);
		
		// color
		this.random = new Random();
		red = this.random.nextInt(255);
		green = this.random.nextInt(255);
		blue = this.random.nextInt(255);
		alpha = this.random.nextInt(255);

		// size
		this.r = 10;
		this.w = 10;
		this.h = 20;
		this.yOffset = -this.h / 2;

		// body
		BodyDef bd = new BodyDef();
		bd.setType(BodyType.DYNAMIC);
		bd.setPosition(this.box2d.coordPixelsToWorld(center));
		this.body = this.box2d.createBody(bd);

		// 1st shape
		PolygonShape ps = new PolygonShape();
		float psWidth = box2d.scalarPixelsToWorld(this.w / 2);
		float psHeight = box2d.scalarPixelsToWorld(this.h / 2);
		ps.setAsBox(psWidth, psHeight);

		// 2nd shape
		CircleShape cs = new CircleShape();
		cs.setRadius(this.box2d.scalarPixelsToWorld(this.r));

		// shape 위치 조정
		Vec2 offset = new Vec2(0, this.yOffset);
		offset = box2d.vectorPixelsToWorld(offset);
		// m_p의 상화 위치 좌표가 box2d좌표계와 +, - 가 반전되게 적용됨
		cs.m_p.set(offset);

		this.body.createFixture(ps, 1);
		this.body.createFixture(cs, 1);

	}

	public void display() {
		Vec2 pos = this.box2d.getBodyPixelCoord(this.body);
		float angle = this.body.getAngle();

		this.p.pushMatrix();

		this.p.translate(pos.x, pos.y);
		this.p.rotate(-angle);

		this.p.stroke(0);

		this.p.fill(red, green, blue, alpha);
		this.p.rectMode(PApplet.CENTER);
		this.p.ellipseMode(PApplet.CENTER);

//		Fixture fixture = this.body.getFixtureList();
//		while (fixture != null) {
//			Shape s = fixture.getShape();
//			if (s instanceof PolygonShape) {
//			} else if (s instanceof CircleShape) {
//
//			}
//
//			fixture = fixture.getNext();
//		}

		this.p.rect(0, 0, this.w, this.h);
		this.p.ellipse(0, this.yOffset, this.r * 2, this.r * 2);

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
