package com.worldbiomusic.processing.noc.chapter2.ecosystem;

import java.util.List;

import com.worldbiomusic.processing.noc.chapter2.Ch2Mover;

import processing.core.PApplet;

public abstract class Obj extends Ch2Mover {
    // color
    protected int[] color;

    // alive flag
    protected boolean alive;

    public Obj(PApplet p) {
	super(p);

	// color (basic: black)
	this.color = new int[3];
	this.setColor(0, 0, 0);

	// alive
	this.alive = true;
    }

    public Obj(PApplet p, float xPos, float yPos, float mass, float r) {
	super(p, xPos, yPos, mass, r);

	// color (basic: black)
	this.color = new int[3];
	this.setColor(0, 0, 0);

	// alive
	this.alive = true;
    }

    public boolean checkAlive() {
	return this.alive;
    }

    public void checkAroundObjs(List<Obj> movers) {
    }

    @Override
    public void update() {
	this.generalUpdate();
    }

    @Override
    public void display() {
	p.stroke(0);
	p.fill(this.color[0], this.color[1], this.color[2]);

	p.ellipse(this.getLocation().x, this.getLocation().y, this.getR() * this.getMass(),
		this.getR() * this.getMass());
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

    @Override
    public void checkEdge() {
	this.checkEdgeLikePassThrough();
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
//
//
