package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.DistanceJointDef;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class CirclePairBridge extends Drawing {
	// DistanceJoint로 연결된 다리 (y좌표 고정, x값만 증가시키면서 일자로 소환시키면 알아서 다리처럼 내려갈듯
	List<Circle> circles;

	public CirclePairBridge(PApplet p, Box2DProcessing box2d) {
		super(p,box2d);
		this.circles = new ArrayList<Circle>();

		int r = 15;

		for (int x = 0; x < this.p.width; x += r * 2) {
			this.circles.add(new Circle(this.p, this.box2d, x, this.p.height / 4, r, BodyType.DYNAMIC));
		}
		circles.get(0).body.setType(BodyType.STATIC);
		circles.get(this.circles.size() - 1).body.setType(BodyType.STATIC);

		for (int i = 0; i < this.circles.size() - 1; i++) {
			DistanceJointDef djd = new DistanceJointDef();
			djd.bodyA = this.circles.get(i).body;
			djd.bodyB = this.circles.get(i + 1).body;
			djd.length = this.box2d.scalarPixelsToWorld(r * 2);
			djd.frequencyHz = 20f;
			djd.dampingRatio = 0f;

			this.box2d.createJoint(djd);
		}
	}

	@Override
	public void display() {
		for (Circle c : this.circles) {
			c.display();
		}

		for (int i = 0; i < this.circles.size() - 1; i++) {
			Vec2 circleAPos = this.box2d.getBodyPixelCoord(this.circles.get(i).body);
			Vec2 circleBPos = this.box2d.getBodyPixelCoord(this.circles.get(i + 1).body);
			this.p.pushMatrix();
			this.p.strokeWeight(1);
			this.p.line(circleAPos.x, circleAPos.y, circleBPos.x, circleBPos.y);
			this.p.popMatrix();
		}

	}

	@Override
	public void kill(List<Drawing> drawings) {
		for (Circle c : this.circles) {
			c.kill(drawings);
		}
	}

}
