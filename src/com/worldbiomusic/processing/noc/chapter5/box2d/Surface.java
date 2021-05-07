package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;

import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Surface extends Drawing {
	enum Type {
		FLAT, TRIGONOMETRIC, PERLIN_NOISE;
	}

	List<Vec2> surface;

	public Surface(PApplet p, Box2DProcessing box2d, Type type) {
		super(p, box2d);

		this.surface = new ArrayList<>();
		// setup surface type
		this.setupType(type);

		ChainShape chain = new ChainShape();

		Vec2[] verticles = new Vec2[surface.size()];
		for (int i = 0; i < verticles.length; i++) {
			verticles[i] = this.box2d.coordPixelsToWorld(surface.get(i));
		}

		chain.createChain(verticles, verticles.length);

		BodyDef bd = new BodyDef();
		this.body = box2d.createBody(bd);
		body.createFixture(chain, 1);
	}

	private void setupType(Type type) {
		if (type == Type.FLAT) {
			surface.add(new Vec2(0, this.p.height / 2));
			surface.add(new Vec2(this.p.width, this.p.height / 2));
		} else if (type == Type.TRIGONOMETRIC) {
			// sin
			float a = 0;
			for (int x = 0; x < this.p.width; x += 1, a += 0.01) {
				float y = (PApplet.sin(a) * this.p.height / 4) + (this.p.height / 2);
				this.surface.add(new Vec2(x, y));
			}
		} else if (type == Type.PERLIN_NOISE) {
			// perlin noise
			float x = 0, yVal = (float) (Math.random() * 999);
			float offset = PApplet.map((float) Math.random(), 0, 0.999f, 0.01f, 0.1f);

			for (; x < this.p.width; x += 10) {
				float yNoise = this.p.noise(yVal);
				int y = (int) PApplet.map(yNoise, 0, 1, 200, this.p.height);
				surface.add(new Vec2(x, y));
				yVal += offset;
			}
		}
	}

	@Override
	public void display() {
		this.p.strokeWeight(1);
		this.p.stroke(0);
		this.p.noFill();

		// processing의 vertex() 함수로 box2d의 ChainShape 나타내기
		this.p.beginShape();
		for (Vec2 v : this.surface) {
			this.p.vertex(v.x, v.y);
		}
		this.p.endShape();
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
