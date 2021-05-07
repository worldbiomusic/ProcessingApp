package com.worldbiomusic.processing.codingchallenge.p50;

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
	String[] a = { AnimatedCirclePacking.class.getName(), "1089", "629" };
	ProcessingMain.main(a);
    }

    @Override
    public void setup() {
	this.circles = new ArrayList<>();
	this.spots = new ArrayList<>();
	// C:\Users\ljh99\eclipse-workspace\ProcessingApp\src\com\worldbiomusic\processing\codingchallenge\p50\2021.png
	this.img = loadImage("src\\com\\worldbiomusic\\processing\\codingchallenge\\p50\\hn.png");
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

	// 한번 반복에 생성되는 circle갯수 제한
	int total = 20;
	int count = 0;

	while (count <= total) {
	    Circle newC = this.randomNewCircle();
	    if (newC != null) {
		// newC가 성공적일때
		this.circles.add(newC);
		count++;
	    }
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
	Circle newC = new Circle(this, x, y, (int)random(255));

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
