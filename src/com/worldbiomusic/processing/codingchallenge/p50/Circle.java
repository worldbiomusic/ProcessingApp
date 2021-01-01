package com.worldbiomusic.processing.codingchallenge.p50;

import java.util.List;

import processing.core.PApplet;

public class Circle {
    PApplet p;
    float x, y;
    float r;
    boolean growing;

    public Circle(PApplet p, float x, float y) {
	this.p = p;
	this.x = x;
	this.y = y;
	this.r = 1;
	this.growing = true;
    }

    public void update() {
	// edge에 안부딫혔을때 계속 성장
	this.grow();

    }

    public void show() {
	this.p.noFill();
	this.p.stroke(255);
	this.p.ellipse(this.x, this.y, this.r * 2, this.r * 2);
    }

    public void grow() {
	if (this.growing) {
	    this.r += 0.5;
	}
    }

    public boolean checkEdges() {
	return (this.x + this.r > this.p.width || this.x - this.r < 0 || this.y + this.r > this.p.height
		|| this.y - this.r < 0);
    }

    public boolean checkCollipse(List<Circle> others) {
	for (Circle other : others) {
	    if (this.checkCollipse(other)) {
		return true;
	    }
	}

	return false;
    }

    public boolean checkCollipse(Circle other) {
	if (this.equals(other))
	    return false;
	@SuppressWarnings("static-access")
	float dist = this.p.dist(this.x, this.y, other.x, other.y);
	if (dist - 2 < this.r + other.r) {
	    return true;
	}

	return false;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Circle) {
	    Circle otherCircle = (Circle) obj;
	    return (this.x == otherCircle.x && this.y == otherCircle.y);
	} else {
	    return false;
	}
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
