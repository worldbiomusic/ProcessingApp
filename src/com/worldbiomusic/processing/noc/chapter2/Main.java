package com.worldbiomusic.processing.noc.chapter2;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public class Main extends PApplet {
    Mover[] m;
    PVector wind;
    PVector gravity;

    public static void main(String[] args) {
	PApplet.main(Main.class.getName());
    }

    // only size()
    @Override
    public void settings() {
	size(1000, 500);
    }

    @Override
    public void setup() {
	println("start");

	PVector.P = this;

	this.m = new Mover[10];

	for (int i = 0; i < m.length; i++) {
	    this.m[i] = new Mover(this, new PVector(random(10, 300), random(10, 100)), new PVector(0, 0),
		    new PVector(0, 0), 10, random(1, 5));
	}

	this.wind = new PVector(0.01f, 0);
	this.gravity = new PVector(0, 0.1f);
    }

    @Override
    public void draw() {
	background(255);

	for (Mover allM : this.m) {
	    allM.applyForce(this.wind);
	    allM.applyForce(this.gravity);

	    allM.update();
	    allM.display();
	}

    }

//    @Override
//    public void mousePressed() {
//	// TODO Auto-generated method stub
//	super.mousePressed();
//	this.m.applyForce(new PVector(0.5f, 0));
//    }

}
