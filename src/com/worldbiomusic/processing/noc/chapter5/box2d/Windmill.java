package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.util.List;
import java.util.Random;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Windmill extends Drawing {
	Circle circle;
	Box box;
	float r;
	float w, h;
	Joint joint;

	Random random;
	int red, green, blue, alpha;

	public Windmill(PApplet p, Box2DProcessing box2d) {
		super(p, box2d);

		// color
		this.random = new Random();
		red = this.random.nextInt(255);
		green = this.random.nextInt(255);
		blue = this.random.nextInt(255);
		alpha = this.random.nextInt(255);

		// size
		this.r = 15;
		this.w = 10;
		this.h = 80;

		this.circle = new Circle(p, box2d, this.p.mouseX, this.p.mouseY, this.r, BodyType.DYNAMIC);
		this.circle.body.setType(BodyType.STATIC);
		this.box = new Box(p, box2d, this.p.mouseX, this.p.mouseY, this.w, this.h);
		RevoluteJointDef rjd = new RevoluteJointDef();
		rjd.initialize(this.circle.body, this.box.body, this.circle.body.getWorldCenter());
		rjd.enableMotor = true;
		rjd.motorSpeed = PApplet.PI * 5;
		rjd.maxMotorTorque = 10000;
		this.joint = this.box2d.createJoint(rjd);
	}

	public void display() {
		Vec2 boxPos = this.box2d.getBodyPixelCoord(this.box.body);
		float angle = this.box.body.getAngle();

		this.p.pushMatrix();

		this.p.translate(boxPos.x, boxPos.y);
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
		this.p.ellipse(0, 0, this.r * 2, this.r * 2);

		this.p.popMatrix();

	}

	@Override
	public void kill(List<Drawing> drawings) {
		this.box2d.destroyBody(this.box.body);
		this.box2d.destroyBody(this.circle.body);
//		this.box2d.world.destroyJoint(this.joint);
//		this.circle.kill(drawings);
//		this.box.kill(drawings);
	}

}
