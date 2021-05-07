package com.worldbiomusic.processing.noc.chapter1.ecosystem;

import java.util.List;

import com.worldbiomusic.processing.noc.chapter1.Ch1Mover;
import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public abstract class Life extends Ch1Mover{
    // color
    protected int[] color;
    
    // alive flag
    protected boolean alive;

    public Life(PApplet p) {
	this(
		p,
		new PVector((int) (Math.random() * p.width),
		(int) (Math.random() * p.height)),
		new PVector(0, 0),
		new PVector(-0.001f, 0.01f),
		10);
    }

    public Life(PApplet p, float topspeed) {
	this(p);
	this.topSpeed = topspeed;
    }

    public Life(PApplet p, PVector location, PVector velocity, PVector accelation, float topspeed) {
	super(p, location, velocity, accelation, topspeed);

	// color (basic: black)
	this.color = new int[3];
	this.setColor(0, 0, 0);
	
	// alive
	this.alive = true;
    }
    
    public boolean checkAlive() {
	return this.alive;
    }
    
    public void checkAroundMovers(List<Life> movers) {
    }

    public abstract void update();
    
    @Override
    public void display() {
	p.stroke(0);
	p.fill(this.color[0], this.color[1], this.color[2]);

	p.ellipse(this.getLocation().x, this.getLocation().y, 20, 20);
    }

    public void setColor(int r, int g, int b) {
	this.color[0] = r;
	this.color[1] = g;
	this.color[2] = b;
    }
    
    public void teleport(float x, float y) {
	this.getLocation().x = x;
	this.getLocation().y = y;
    }
}

//public abstract class Life{
//    protected PApplet p;
//    
//    // accelation만 조절가능
//    private PVector location; 
//    private PVector velocity;
//    protected PVector accelation;
//    
//    // this.velocity의 최고 mag() 값
//    protected float topSpeed;
//
//    // perlin noise 움직임 위한 변수
//    private float xTime, yTime;
//    
//    // color
//    protected int[] color;
//    
//    // alive flag
//    protected boolean alive;
//
//    public Life(PApplet p) {
//	this(
//		p,
//		new PVector((int) (Math.random() * p.width),
//		(int) (Math.random() * p.height)),
//		new PVector(0, 0),
//		new PVector(-0.001f, 0.01f),
//		10);
//    }
//
//    public Life(PApplet p, float topspeed) {
//	this(p);
//	this.topSpeed = topspeed;
//    }
//
//    public Life(PApplet p, PVector location, PVector velocity, PVector accelation, float topspeed) {
//	this.p = p;
//	this.location = location;
//	this.velocity = velocity;
//	this.accelation = accelation;
//	this.topSpeed = topspeed;
//
//	// perlin noise
//	this.xTime = (float) Math.random() * 100;
//	this.yTime = (float) Math.random() * 100 + 1000;
//	
//	// color (basic: black)
//	this.color = new int[3];
//	this.setColor(0, 0, 0);
//	
//	// alive
//	this.alive = true;
//    }
//    
//    public boolean checkAlive() {
//	return this.alive;
//    }
//    
//    public void checkAroundMovers(List<Life> movers) {
//    }
//
//    public abstract void update();
//
//    protected void generalUpdate() {
//	// 가속
//	this.velocity.add(this.accelation);
//	this.location.add(this.velocity);
//
//	// 모서리 처리
//	this.checkEdge();
//
//	// 속도 제한
//	this.velocity.limit(this.topSpeed);
//    }
//
//    public void perlinNoiseUpdate() {
//	this.xTime += 0.02;
//	this.yTime += 0.015;
//
//	float xNoise = this.p.noise(this.xTime);
//	float yNoise = this.p.noise(this.yTime);
//
//	this.accelation = new PVector(xNoise - 0.5f, yNoise - 0.5f);
//
//	// general update
//	this.generalUpdate();
//    }
//
//    public void mouseFollowingUpdate() {
//	PVector mouse = new PVector(this.p.mouseX, this.p.mouseY);
//	PVector dir = PVector.sub(mouse, this.location);
//
//	// 가까워질수록 속도가 더 빠르게
//	float power = PApplet.map(dir.mag(), 0, (this.p.width + this.p.height) / 2, 1, 0.001f);
//	dir.normalize();
//	dir.mult(power);
//
//	this.accelation = dir;
//	this.velocity.add(this.accelation);
//	;
//	this.location.add(this.velocity);this.getVelocity()서리 처리
//	this.checkEdge();
//
//	// 속도 제한
//	this.velocity.limit(this.topSpeed);
//    }
//
//    public void display() {
//	p.stroke(0);
//	p.fill(this.color[0], this.color[1], this.color[2]);
//
//	p.ellipse(this.location.x, this.location.y, 20, 20);
//    }
//
//    public void checkEdge() {
//	if (this.location.x > p.width) {
//	    this.location.x = 0;
//	} else if (this.location.x < 0) {
//	    this.location.x = p.width;
//	}
//
//	if (this.location.y > p.height) {
//	    this.location.y = 0;
//	} else if (this.location.y < 0) {
//	    this.location.y = p.height;
//	}
//    }
//
//    protected PVector getLocation() {
//	return this.location;
//    }
//    
//    protected PVector getVelocity() {
//	return this.velocity;
//    }
//    
//    protected void setColor(int r, int g, int b) {
//	this.color[0] = r;
//	this.color[1] = g;
//	this.color[2] = b;
//    }
//    
//    public void teleport(float x, float y) {
//	this.location.x = x;
//	this.location.y = y;
//    }
//}
