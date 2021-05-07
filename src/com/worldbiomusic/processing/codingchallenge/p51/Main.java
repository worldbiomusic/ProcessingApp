package com.worldbiomusic.processing.codingchallenge.p51;

import java.util.ArrayList;
import java.util.List;

import com.worldbiomusic.processing.ProcessingMain;
import com.worldbiomusic.processing.codingchallenge.p50.Circle;

import processing.core.PImage;

public class Main extends ProcessingMain {
    List<Circle> circles;
    PImage img;

    public static void main(String[] args) {
	String[] a = { Main.class.getName(), "675", "380" };
	ProcessingMain.main(a);
    }

    @Override
    public void setup() {
	this.circles = new ArrayList<>();
	// C:\Users\ljh99\eclipse-workspace\ProcessingApp\src\com\worldbiomusic\processing\codingchallenge\p50\2021.png
	this.img = loadImage("src\\com\\worldbiomusic\\processing\\codingchallenge\\p51\\mc1.jpg");
	this.img.loadPixels();
	
    }

    @Override
    public void draw() {
	background(0);

	// 한번 반복에 생성되는 circle갯수 제한
	// 갯수가 커지면 동시에 생성되는 circle이 많아지므로 대체적으로 크기가 작음
	// 갯수가 작으면 동시에 생성되는 circle갯수가 적으므로 대체적으로 크기가 큼
	int total = 30;
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
	int x = (int) random(width);
	int y = (int) random(height);
	int color = this.img.get(x, y);

	Circle newC = new Circle(this, x, y, color);

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
