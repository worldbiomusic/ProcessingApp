package com.worldbiomusic.processing.noc.chapter3;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public class Wave {

	PApplet p;

	float amplitude;
	float frequency;

	PVector pos1, pos2;

	float interval;

	float angle, distance, period;

	public Wave(PApplet p, float amplitude, float frequency, PVector pos1, PVector pos2) {
		this.p = p;
		this.amplitude = amplitude;
		this.frequency = frequency;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.interval = 20;

		float dx = pos2.x - pos1.x;
		float dy = pos2.y - pos1.y;
		this.angle = PApplet.atan2(dy, dx);

		this.distance = PVector.sub(this.pos2, this.pos1).mag();
		System.out.println("dist: " + distance);
		this.period = p.frameRate / this.frequency;
	}

	public void wave() {
		this.p.pushMatrix();

//		float dx = pos2.x - pos1.x;
//		float dy = pos2.y - pos1.y;
//		float angle = PApplet.atan2(dy, dx);
		this.p.rotate(angle);

//		float distance = PVector.sub(this.pos1, this.pos2).mag();

//		float period = p.frameRate / this.frequency;

		int frameCountPlus = 0;
		int startX = (int) this.pos1.x;
		for (int x = startX; x <= startX+distance; x += this.interval) {
			int frameCount = this.p.frameCount + frameCountPlus;
			frameCountPlus += 5;
			float y = this.amplitude * PApplet.sin(PApplet.TWO_PI * frameCount / period);
			y += this.pos1.y;
			this.p.ellipse(x, y, this.interval, this.interval);
		}
		this.p.popMatrix();
	}

}
