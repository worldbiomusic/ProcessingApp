package com.worldbiomusic.processing.noc.chapter0;

import java.util.Random;

import processing.core.PApplet;

public class Walker {
    Random r;
    float x, y;
    float xt, yt;
    PApplet main;

    public Walker(PApplet main, int x, int y) {
	this.main = main;
	this.x = x;
	this.y = y;
	r = new Random();
	this.xt = 0;
	this.yt = 100000;
    }
    
    public void stepWithPerlinNoise() {
	this.x = PApplet.map(this.main.noise(this.xt), 0, 1, 0, this.main.width);
	this.y = PApplet.map(this.main.noise(this.yt), 0, 1, 0, this.main.height);
	
	this.xt += 0.01;
	this.yt += 0.01;
	
	this.main.fill(0, 50);
	this.main.ellipse(this.x, this.y, 30, 30);
    }

    public void stepWithLeviFlight() {
	// levi flight
	double r = this.r.nextDouble();
	if (r < 0.01) {
	    int xFlightSize = (int) (this.montecarlo() * 100);
	    int yFlightSize = (int) (this.montecarlo() * 100);
	    
	    int rx = (int)(Math.random());
	    int ry = (int)(Math.random());
	    
	    // 절반의 확률로 반대 방향
	    if(rx < 0.5) {
		xFlightSize *= -1;
	    }
	    if(ry < 0.5) {
		yFlightSize *= -1;
	    }

	    this.x += xFlightSize;
	    this.y += yFlightSize;

	} else {
	    int dx = this.r.nextInt(3) - 1;
	    int dy = this.r.nextInt(3) - 1;

	    this.x += dx;
	    this.y += dy;
	}
    }

    public void display() {
	this.main.stroke(0);

	this.main.point(this.x, this.y);
    }

    public float montecarlo() {
	while (true) {
	    float r1 = this.main.random(1);
	    float r2 = this.main.random(1);

	    if (r2 < r1) {
		return r1;
	    }
	}
    }
}
