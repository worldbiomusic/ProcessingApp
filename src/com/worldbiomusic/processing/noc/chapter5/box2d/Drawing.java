package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public abstract class Drawing {
	PApplet p;
	Box2DProcessing box2d;
	public Body body;

	public Drawing(PApplet p, Box2DProcessing box2d) {
		this.p = p;
		this.box2d = box2d;
	}

	public void setUserData() {
		if (this.body != null) {
			this.body.setUserData(this);
		}
	}

	public abstract void display();

	public void kill(List<Drawing> drawings) {
		this.box2d.destroyBody(this.body);
	}

	public void attract(Drawing other) {
		// other를 이거로 끌어당기는 메소드
		Vec2 drawing = this.body.getWorldCenter();
		Vec2 otherDrawing = other.body.getWorldCenter();

		Vec2 force = drawing.sub(otherDrawing);
		float distance = force.length();
		float min = this.box2d.scalarPixelsToWorld(5);
		float max = this.box2d.scalarPixelsToWorld(10);
		distance = PApplet.constrain(distance, min, max);

		float power = (this.body.m_mass * other.body.m_mass) / (distance * distance);

		force.normalize();
		force.mulLocal(power);

		other.body.applyForceToCenter(force);
	}
}
