package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class CirclePair extends Drawing {
	Circle circleA, circleB;

	public CirclePair(PApplet p, Box2DProcessing box2d) {
		super(p,box2d);
		this.circleA = new Circle(this.p, this.box2d, this.p.mouseX, this.p.mouseY, 10, BodyType.DYNAMIC);
		this.circleB = new Circle(this.p, this.box2d, this.p.mouseX + this.p.random(-10, 10),
				this.p.mouseY + this.p.random(-10, 10), 10, BodyType.DYNAMIC);

		DistanceJointDef djd = new DistanceJointDef();
		djd.bodyA = this.circleA.body;
		djd.bodyB = this.circleB.body;
		djd.length = this.box2d.scalarPixelsToWorld(30);
		djd.frequencyHz = 2.5f;
		djd.dampingRatio = 0.002f;

		DistanceJoint joint = (DistanceJoint) this.box2d.createJoint(djd);
	}

	@Override
	public void display() {
		this.circleA.display();
		this.circleB.display();
		Vec2 circleAPos = this.box2d.getBodyPixelCoord(this.circleA.body);
		Vec2 circleBPos = this.box2d.getBodyPixelCoord(this.circleB.body);
		this.p.pushMatrix();
		this.p.strokeWeight(1);
		this.p.line(circleAPos.x, circleAPos.y, circleBPos.x, circleBPos.y);
		this.p.popMatrix();
	}

	@Override
	public void kill(List<Drawing> drawings) {
		this.box2d.destroyBody(this.circleA.body);
		this.box2d.destroyBody(this.circleB.body);
//		drawings.remove(this.circleA);
//		drawings.remove(this.circleB);
	}

}
