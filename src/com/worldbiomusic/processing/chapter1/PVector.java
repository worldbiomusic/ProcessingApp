package com.worldbiomusic.processing.chapter1;

import processing.core.PApplet;

public class PVector {
    static PApplet P;
    static float xtime = (float) (Math.random() * 0.01), ytime = (float) (Math.random() * 0.0001);
    float x, y;

    public PVector(float x, float y) {
	this.x = x;
	this.y = y;
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
	return new PVector(v1.x * n,v1.y * n);
    }

    public void div(float n) {
	this.x /= n;
	this.y /= n;
    }
    
    public static PVector div(PVector v1, float n) {
   	return new PVector(v1.x / n,v1.y / n);
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
	PVector v = new PVector((float)Math.random() - 0.5f, (float)Math.random() - 0.5f);
	v.normalize();
	return v;
    }
    
    public static PVector perlinNoise2D() {
	xtime += 0.020;
	ytime += 0.025;

	float x = P.noise(xtime);
	float y = P.noise(ytime);

	// noise는 0~1사이의 값을 반환하기 때문에 -0.5하면 됨
	PVector v = new PVector(x - 0.5f, y - 0.5f);
	v.normalize();
	return v;
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
