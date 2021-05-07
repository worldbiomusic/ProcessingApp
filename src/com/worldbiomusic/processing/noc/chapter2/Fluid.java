package com.worldbiomusic.processing.noc.chapter2;

import processing.core.PApplet;

public class Fluid {
    PApplet p;
    public float x;
    public float y;
    public float w;
    public float h;
    public float c;

    public Fluid(PApplet p, float x, float y, float w, float h, float c) {
	this.p = p;
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
	this.c = c;
    }

    public void display() {
	this.p.noStroke();
	this.p.fill(175);
	this.p.rect(this.x, this.y, this.w, this.h);
    }
}
