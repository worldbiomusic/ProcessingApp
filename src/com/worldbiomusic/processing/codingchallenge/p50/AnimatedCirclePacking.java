package com.worldbiomusic.processing.codingchallenge.p50;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.worldbiomusic.processing.ProcessingMain;

import processing.core.PImage;
import processing.core.PVector;

public class AnimatedCirclePacking extends ProcessingMain {
    List<Circle> circles;
    PImage img;
    List<PVector> spots;

    public static void main(String[] args) {
	String[] a = { AnimatedCirclePacking.class.getName(), "1932", "1524" };
	ProcessingMain.main(a);
    }

    @Override
    public void setup() {
	this.circles = new ArrayList<>();
	this.spots = new ArrayList<>();
	// C:\Users\ljh99\eclipse-workspace\ProcessingApp\src\com\worldbiomusic\processing\codingchallenge\p50\2021.png
	this.img = loadImage("src\\com\\worldbiomusic\\processing\\codingchallenge\\p50\\new2021.png");
	this.img.loadPixels();

	for (int x = 0; x < this.img.width; x++) {
	    for (int y = 0; y < this.img.height; y++) {
		int c = this.img.get(x, y);
		float b = brightness(c);
		if (b < 100) {
		    this.spots.add(new PVector(x, y));
		}
	    }
	}
    }

    @Override
    public void draw() {
	background(0);

	int total = 10;
	int count = 0;

	while (count <= total) {
	    Circle newC = this.randomNewCircle();
	    if (newC != null) {
		this.circles.add(newC);
	    }
	    count++;
	}

	for (Circle c : this.circles) {
	    if (c.checkEdges() || c.checkCollipse(this.circles)) {
		c.growing = false;
	    }
	    c.update();
	    c.show();
	}

    }

    public Circle randomNewCircle() {
	int r = (int) random(this.spots.size());
	PVector randomSpot = this.spots.get(r);
	float x = randomSpot.x;
	float y = randomSpot.y;
	Circle newC = new Circle(this, x, y);

	if (newC.checkCollipse(this.circles)) {
	    return null;
	}

	return newC;
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
