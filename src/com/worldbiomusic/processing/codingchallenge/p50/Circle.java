package com.worldbiomusic.processing.codingchallenge.p50;

import java.util.List;

import processing.core.PApplet;

public class Circle {
    public PApplet p;
    public float x, y;
    public float r;
    public boolean growing;
    int color;

    public Circle(PApplet p, float x, float y, int color) {
	this.p = p;
	this.x = x;
	this.y = y;
	this.r = 1;
	this.growing = true;
	this.color = color;
    }

    public void update() {
	// edge에 안부딫혔을때 계속 성장
	this.grow();

    }

    public void show() {
	this.p.fill(this.color);
//	this.p.stroke(255);
	this.p.noStroke();
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
	/*
	 * 서로 충돌했는지 확인 (포함도 충돌로 처리)
	 */
	// 동일 객체이면 충돌체크 제외(x, y, r로)
	if (this.equals(other))
	    return false;
	@SuppressWarnings("static-access")
	float dist = this.p.dist(this.x, this.y, other.x, other.y);
	if (dist  < this.r + other.r) {
	    return true;
	}

	return false;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Circle) {
	    Circle otherCircle = (Circle) obj;
	    return (this.x == otherCircle.x && this.y == otherCircle.y && this.r == otherCircle.r);
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
