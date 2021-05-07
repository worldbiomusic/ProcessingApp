package com.worldbiomusic.processing.noc.chapter2;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public class Ch2Mover {
	protected PApplet p;

	// 직접 값 설정 불가능, applyForce로 힘을 주어야 함
	private PVector location;
	private PVector velocity;
	private PVector accelation;
	protected float mass;

	// this.velocity의 최고 mag() 값
	protected float topSpeed;

	// perlin noise 움직임 위한 변수
	private float xTime, yTime;

	float r;

	public Ch2Mover(PApplet p) {
		this(p, new PVector((int) (Math.random() * p.width), (int) (Math.random() * p.height)), new PVector(0, 0),
				new PVector(0, 0), 10, 10, 1);
	}

	public Ch2Mover(PApplet p, float xPos, float yPos) {
		this(p);
		this.location.x = xPos;
		this.location.y = yPos;
	}

	public Ch2Mover(PApplet p, float xPos, float yPos, float mass, float r) {
		this(p, xPos, yPos);
		this.mass = mass;
		this.r = r;
	}

	public Ch2Mover(PApplet p, PVector location, PVector velocity, PVector accelation, float topspeed, float mass,
			float r) {
		this.p = p;
		this.location = location;
		this.velocity = velocity;
		this.accelation = accelation;
		this.mass = mass;
		this.topSpeed = topspeed;

		// perlin noise
		this.xTime = (float) Math.random() * 100;
		this.yTime = (float) Math.random() * 100 + 1234;

		this.r = r;
	}

	public void update() {
		this.generalUpdate();
	}

	public boolean checkCollide(Ch2Mover other) {
		double dist = PApplet.dist(this.location.x, this.location.y, other.getLocation().x, other.getLocation().y);
		double thisR = (this.mass * this.r) / 2;
		double otherR = (other.mass * other.r) / 2;
		if (dist < (thisR + otherR)) {
			return true;
		} else {
			return false;
		}
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
		p.fill(255);

		p.ellipse(this.location.x, this.location.y, this.r * this.mass, this.r * this.mass);
	}

	public void checkEdge() {
		checkEdgeLikeBounce();
	}

	public void noCheckEdge() {

	}

	public void checkEdgeLikeBounce() {
		if (this.location.x < 0) {
			this.location.x = 0;
			this.velocity.x *= -1;
		} else if (this.location.x > this.p.width) {
			this.location.x = this.p.width;
			this.velocity.x *= -1;
		}

		if (this.location.y > this.p.height) {
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

	public void checkEdgeAllBlock() {
		if (this.location.x > p.width) {
			this.location.x = p.width;
		} else if (this.location.x < 0) {
			this.location.x = 0;
		}

		if (this.location.y > p.height) {
			this.location.y = p.height;
		} else if (this.location.y < 0) {
			this.location.y = 0;
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

	public boolean inInside(Fluid f) {
		if (this.location.x > f.x && this.location.x < f.x + f.w) {
			if (this.location.y > f.y && this.location.y < f.y + f.h) {
				return true;
			}
		}
		return false;
	}

	// 유체저항(점성력 or 저항력)
	public void drag(Fluid f) {
		float dragMagnitude = f.c * this.velocity.mag() * this.velocity.mag();

		PVector drag = this.velocity.get();
		drag.normalize();
		drag.mult(-1);
		drag.mult(dragMagnitude);

		this.applyForce(drag);
	}

	public void attractOther(Ch2Mover other) {
		// other를 자신에게 끌어당기는 메소드
		PVector force = PVector.sub(this.location, other.location);

		// 두 Mover사이의 거리는 최소 10pixel ~ 최대 100pixel까지의 거리로 가정한다
		float distance = PApplet.constrain(force.mag(), 30, 100);

		force.normalize();

		float gravityMagitude = PVector.G * this.mass * other.mass;
		gravityMagitude /= (distance * distance);

		force.mult(gravityMagitude);

		other.applyForce(force);
	}

	public void attractedToOther(Ch2Mover other) {
		// 자신을 other에게 끌리는 메소드
		PVector force = PVector.sub(other.location, this.location);

		// 두 Mover사이의 거리는 최소 5pixel ~ 최대 100pixel까지의 거리로 가정한다
		float distance = PApplet.constrain(force.mag(), 10, 30);

		force.normalize();

		float gravityMagitude = PVector.G * this.mass * other.mass;
		gravityMagitude /= (distance * distance);

		force.mult(gravityMagitude);

		this.applyForce(force);
	}

	public void attractedToOther(Ch2Mover other, float power) {
		// 자신을 other에게 끌리는 메소드
		PVector force = PVector.sub(other.location, this.location);

		// 두 Mover사이의 거리는 최소 5pixel ~ 최대 100pixel까지의 거리로 가정한다
		float distance = PApplet.constrain(force.mag(), 10, 30);

		force.normalize();

		float gravityMagitude = PVector.G * this.mass * other.mass;
		gravityMagitude /= (distance * distance);

		force.mult(gravityMagitude);
		force.mult(power);

		this.applyForce(force);
	}

	public void repulseOther(Ch2Mover other) {
		// 척력
		// other를 자신으로 부터 밀어내는 메소드
		PVector force = PVector.sub(this.location, other.location);

		// 두 Mover사이의 거리는 최소 5pixel ~ 최대 100pixel까지의 거리로 가정한
		float distance = PApplet.constrain(force.mag(), 10, 30);

		force.normalize();

		float gravityMagitude = PVector.G * this.mass * other.mass;
		gravityMagitude /= (distance * distance);

		force.mult(gravityMagitude);
		force.mult(-1);

		other.applyForce(force);
	}

	public void repulsedFromOther(Ch2Mover other) {
		// 척력
		// 자신이 other로붙 밀리는 메소드
		PVector force = PVector.sub(other.location, this.location);

		// 두 Mover사이의 거리는 최소 5pixel ~ 최대 100pixel까지의 거리로 가정한
		float distance = PApplet.constrain(force.mag(), 5, 100);

		force.normalize();

		float gravityMagitude = PVector.G * this.mass * other.mass;
		gravityMagitude /= (distance * distance);

		force.mult(gravityMagitude);
		force.mult(-1);

		this.applyForce(force);
	}

	public void repulsedFromOther(Ch2Mover other, float power) {
		// 척력
		// 자신이 other로붙 밀리는 메소드
		PVector force = PVector.sub(other.location, this.location);

		// 두 Mover사이의 거리는 최소 5pixel ~ 최대 100pixel까지의 거리로 가정한
		float distance = PApplet.constrain(force.mag(), 5, 100);

		force.normalize();

		float gravityMagitude = PVector.G * this.mass * other.mass;
		gravityMagitude /= (distance * distance);

		force.mult(gravityMagitude);
		force.mult(-1);
		force.mult(power);

		this.applyForce(force);
	}

	// getter / setter ===========================================

	public PVector getLocation() {
		return this.location;
	}

	public PVector getVelocity() {
		return this.velocity;
	}

	public PVector getAccelation() {
		return this.accelation;
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

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(float topSpeed) {
		this.topSpeed = topSpeed;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

}
