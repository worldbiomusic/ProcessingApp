package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.util.Random;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class CircleHoop extends Drawing {
	Circle circle;
	Box box;
	float r;
	float w, h;

	Random random;
	int red, green, blue, alpha;

	public CircleHoop(PApplet p, Box2DProcessing box2d) {
		super(p,box2d);

		// color
		this.random = new Random();
		red = this.random.nextInt(255);
		green = this.random.nextInt(255);
		blue = this.random.nextInt(255);
		alpha = this.random.nextInt(255);

		// size
		this.r = 15;

		this.circle = new Circle(p, box2d, this.p.mouseX, this.p.mouseY, this.r, BodyType.DYNAMIC);
		RevoluteJointDef rjd = new RevoluteJointDef();
		rjd.initialize(this.circle.body, this.circle.body, this.circle.body.getWorldCenter());
		rjd.enableMotor = true;
		rjd.motorSpeed = PApplet.PI * 5;
		rjd.maxMotorTorque = 10000;
		this.box2d.createJoint(rjd);
	}

	public void display() {
		Vec2 boxPos = this.box2d.getBodyPixelCoord(this.circle.body);
		float angle = this.circle.body.getAngle();

		this.p.pushMatrix();

		this.p.translate(boxPos.x, boxPos.y);
		this.p.rotate(-angle);

		this.p.stroke(0);
		this.p.fill(red, green, blue, alpha);
		this.p.ellipseMode(PApplet.CENTER);
		this.p.ellipse(0, 0, this.r * 2, this.r * 2);

		this.p.popMatrix();

	}


}