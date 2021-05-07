package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Spring extends Drawing{
	Drawing target;
	MouseJoint mouseJoint;

	public Spring(PApplet p, Box2DProcessing box2d, Drawing drawing) {
		super(p,box2d);
		this.target = drawing;

		MouseJointDef md = new MouseJointDef();
		md.bodyA = this.box2d.getGroundBody();
		md.bodyB = this.target.body;

//		md.target.set(this.box2d.coordPixelsToWorld(p.width / 2, p.height / 2));

		md.maxForce = 100000;
		md.frequencyHz = 5;
		md.dampingRatio = 0.3f;

		this.mouseJoint = (MouseJoint) box2d.createJoint(md);
	}

	public void spring(int x, int y) {
		Vec2 targetLocation = this.box2d.coordPixelsToWorld(x, y);
//		this.mouseJoint.setTarget(targetLocation);
		this.mouseJoint.getBodyA().getPosition().set(targetLocation);
		this.mouseJoint.setTarget(this.mouseJoint.getBodyA().getPosition());
	}

	public void display() {
		// 끈으로 표시
		
		Vec2 v1 = this.box2d.getBodyPixelCoord(this.mouseJoint.getBodyA());
		Vec2 v2 = this.box2d.getBodyPixelCoord(this.mouseJoint.getBodyB());

		System.out.println("bodyA: " + v1.x + ":" + v1.y);
		System.out.println("bodyB: " + v2.x + ":" + v2.y);

		this.p.stroke(0);
		this.p.strokeWeight(2);

		this.p.line(v1.x, v1.y, v2.x, v2.y);
	}

	@Override
	public void kill(List<Drawing> drawings) {
		this.box2d.world.destroyJoint(this.mouseJoint);
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
