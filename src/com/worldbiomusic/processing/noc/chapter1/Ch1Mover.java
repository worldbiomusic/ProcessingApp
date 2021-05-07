package com.worldbiomusic.processing.noc.chapter1;

import processing.core.PApplet;

public class Ch1Mover {
    protected PApplet p;

    // accelation만 조절가능
    private PVector location;
    private PVector velocity;
    protected PVector accelation;

    // this.velocity의 최고 mag() 값
    protected float topSpeed;

    // perlin noise 움직임 위한 변수
    private float xTime, yTime;

    public Ch1Mover(PApplet p) {
	this(p, new PVector((int) (Math.random() * p.width), (int) (Math.random() * p.height)), new PVector(0, 0),
		new PVector(-0.001f, 0.01f), 10);
    }

    public Ch1Mover(PApplet p, float topspeed) {
	this(p);
	this.topSpeed = topspeed;
    }

    public Ch1Mover(PApplet p, PVector location, PVector velocity, PVector accelation, float topspeed) {
	this.p = p;
	this.location = location;
	this.velocity = velocity;
	this.accelation = accelation;
	this.topSpeed = topspeed;

	// perlin noise
	this.xTime = (float) Math.random() * 100;
	this.yTime = (float) Math.random() * 100 + 1000;
    }

    public void update() {
	this.generalUpdate();
    }

    protected void generalUpdate() {
	// 가속
	this.velocity.add(this.accelation);
	this.location.add(this.velocity);

	// 모서리 처리
	this.checkEdge();

	// 속도 제한
	this.velocity.limit(this.topSpeed);
    }

    public void perlinNoiseUpdate() {
	this.xTime += 0.02;
	this.yTime += 0.015;

	float xNoise = this.p.noise(this.xTime);
	float yNoise = this.p.noise(this.yTime);

	// noise는 0~1사이의 값을 반환하기 때문에 -0.5하면 됨
	this.accelation = new PVector(xNoise - 0.5f, yNoise - 0.5f);

	// general update
	this.generalUpdate();
    }

    public void mouseFollowingUpdate() {
	PVector mouse = new PVector(this.p.mouseX, this.p.mouseY);
	PVector dir = PVector.sub(mouse, this.location);

	// 가까워질수록 속도가 더 빠르게
	float power = PApplet.map(dir.mag(), 0, (this.p.width + this.p.height) / 2, 1, 0.001f);
	dir.normalize();
	dir.mult(power);

	this.accelation = dir;
	// general update
	this.generalUpdate();
    }

    public void display() {
	p.stroke(0);
	p.fill(0);

	p.ellipse(this.location.x, this.location.y, 20, 20);
    }

    public void checkEdge() {
	if (this.location.x > p.width) {
	    this.location.x = 0;
	} else if (this.location.x < 0) {
	    this.location.x = p.width;
	}

	if (this.location.y > p.height) {
	    this.location.y = 0;
	} else if (this.location.y < 0) {
	    this.location.y = p.height;
	}
    }

    // getter / setter ===========================================

    public PVector getLocation() {
	return this.location;
    }

    public PVector getVelocity() {
	return this.velocity;
    }

    public void teleport(float x, float y) {
	this.location.x = x;
	this.location.y = y;
    }

    public void addXTime(float time) {
	this.xTime += time;
    }

    public void addYTime(float time) {
	this.yTime += time;
    }

    public float getxTime() {
	return xTime;
    }

    public float getyTime() {
	return yTime;
    }

}
