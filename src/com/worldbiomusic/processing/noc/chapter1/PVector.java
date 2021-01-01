package com.worldbiomusic.processing.noc.chapter1;

import processing.core.PApplet;

public class PVector {
    public static PApplet P;
    public float x, y;
    public float xTime, yTime;

    public PVector(float x, float y) {
	this.x = x;
	this.y = y;

	// perlin noise
	this.xTime = (float) Math.random() * 50;
	this.yTime = (float) Math.random() * 100 + 500;
    }

    public void add(PVector v) {
	this.x += v.x;
	this.y += v.y;
    }

    public static PVector add(PVector v1, PVector v2) {
	return new PVector(v1.x + v2.x, v1.y + v2.y);
    }

    public void sub(PVector v) {
	this.x -= v.x;
	this.y -= v.y;
    }

    public static PVector sub(PVector v1, PVector v2) {
	return new PVector(v1.x - v2.x, v1.y - v2.y);
    }

    public void mult(float n) {
	this.x *= n;
	this.y *= n;
    }

    public static PVector mult(PVector v1, float n) {
	return new PVector(v1.x * n, v1.y * n);
    }

    public void div(float n) {
	this.x /= n;
	this.y /= n;
    }

    public static PVector div(PVector v1, float n) {
	return new PVector(v1.x / n, v1.y / n);
    }

    public float mag() {
	return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void normalize() {
	this.div(this.mag());
    }

    public void limit(float n) {
	if (this.mag() > n) {
//	    this.normalize();
//	    this.mult(n);
	    this.mult(n / this.mag());
	}
    }

    public static PVector random2D() {
	PVector v = new PVector((float) Math.random() - 0.5f, (float) Math.random() - 0.5f);
	v.normalize();
	return v;
    }

    public void changeWithPerlinNoise() {
	this.xTime += 0.02;
	this.yTime += 0.015;

	float xNoise = P.noise(this.xTime) - 0.5f;
	float yNoise = P.noise(this.yTime) - 0.5f;

	this.x = xNoise;
	this.y = yNoise;
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
