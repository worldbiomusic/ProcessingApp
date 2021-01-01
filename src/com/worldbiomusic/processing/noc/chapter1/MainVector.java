package com.worldbiomusic.processing.noc.chapter1;

import java.util.List;

import processing.core.PApplet;

public class MainVector extends PApplet {
    EcoSystem eco;

    List<FishMover> fish, foods;

    public static void main(String[] args) {
	PApplet.main(MainVector.class.getName());
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
	smooth();

	this.eco = new EcoSystem(this);

	// fish
	for (int i = 0; i < 10; i++) {
	    FishMover fish = new FishMover(this, (int) (Math.random() * 5) + 1);
	    this.eco.addMover(fish);
	}

	// foods
	for (int i = 0; i < 10; i++) {
	    FishFoodMover food = new FishFoodMover(this, 0.3f);
	    food.setColor(255, 0, 0);
	    this.eco.addMover(food);
	}

    }

    @Override
    public void draw() {
	background(255);

	this.eco.update();
	this.eco.display();
    }
    
    @Override
    public void mousePressed() {
        super.mousePressed();
        
        FishFoodMover food = new FishFoodMover(this, 0.3f);
	food.setColor(255, 0, 0);
	food.teleport(mouseX, mouseY);
	this.eco.addMover(food);
    }
    
    @Override
    public void mouseDragged() {
        // TODO Auto-generated method stub
        super.mouseDragged();
        
        FishFoodMover food = new FishFoodMover(this, 0.3f);
	food.setColor(255, 0, 0);
	food.teleport(mouseX, mouseY);
	this.eco.addMover(food);
    }
    
    @Override
    public void keyPressed() {
        super.keyPressed();
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
