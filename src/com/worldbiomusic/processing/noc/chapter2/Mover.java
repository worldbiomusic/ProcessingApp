package com.worldbiomusic.processing.noc.chapter2;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public class Mover {
    protected PApplet p;

    // accelation만 조절가능
    private PVector location;
    private PVector velocity;
    protected PVector accelation;
    protected float mass;

    // this.velocity의 최고 mag() 값
    protected float topSpeed;

    // perlin noise 움직임 위한 변수
    private float xTime, yTime;

    public Mover(PApplet p) {
	this(p, new PVector((int) (Math.random() * p.width), (int) (Math.random() * p.height)), new PVector(0, 0),
		new PVector(0, 0), 10, 10);
    }

    public Mover(PApplet p, float topspeed) {
	this(p);
	this.topSpeed = topspeed;
    }

    public Mover(PApplet p, PVector location, PVector velocity, PVector accelation, float topspeed, float mass) {
	this.p = p;
	this.location = location;
	this.velocity = velocity;
	this.accelation = accelation;
	this.mass = mass;
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

	// 가속도 초기화
	this.accelation.mult(0);
    }

    public void perlinNoiseUpdate() {
	this.xTime += 0.02;
	this.yTime += 0.015;

	float xNoise = this.p.noise(this.xTime);
	float yNoise = this.p.noise(this.yTime);

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

	p.ellipse(this.location.x, this.location.y, 16 * this.mass, 16 * this.mass);
    }

    public void checkEdge() {
	checkEdgeLikeBounce();
    }

    public void checkEdgeLikeBounce() {
	if (this.location.x < 0) {
	    this.location.x = 0;
	    this.velocity.x *= -1;
	} else if (this.location.x > this.p.width) {
	    this.location.x = this.p.width;
	    this.velocity.x *= -1;
	}
	
	if(this.location.y > this.p.height) {
	    this.location.y = this.p.height;
	    this.velocity.y *= -1;
	}
    }

    public void checkEdgeLikePassThrough() {
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

    public void applyForce(PVector force) {
	/*
	 * 힘은 가속도를 변화시키는 것이다
	 * 
	 * 그리고 가속도는 힘과 비례한다
	 * 
	 * 그런데 가속도는 질량과 반비례한다
	 * 
	 * 그러므로 가속도 = 가해지는 힘 / 질량이다
	 * 
	 * 그러면 가해지는 힘을 질량으로 나눈값을 가속도에 더해야 한다
	 */
	PVector addtionalAccelation = PVector.div(force, this.mass);
	this.accelation.add(addtionalAccelation);
    }

    // getter / setter ===========================================

    protected PVector getLocation() {
	return this.location;
    }

    protected PVector getVelocity() {
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
