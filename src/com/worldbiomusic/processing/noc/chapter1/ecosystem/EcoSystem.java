package com.worldbiomusic.processing.noc.chapter1.ecosystem;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class EcoSystem {
    List<Life> movers;
    PApplet p;

    public EcoSystem(PApplet p) {
	this.p = p;
	this.movers = new ArrayList<>();
    }
    
    public void addMover(Life mover) {
	this.movers.add(mover);
    }
    
    public void removeMover(Life mover) {
	this.movers.remove(mover);
    }
    
    public void update() {
	// check movers dead
	this.checkMoversAlive();
	
	// update movers
	for (int i = 0; i < this.movers.size(); i++) {
	    this.movers.get(i).checkAroundMovers(this.movers);
	    this.movers.get(i).update();
	}
    }
    
    public void checkMoversAlive() {
	for (int i = this.movers.size() - 1; i >= 0; i--) {
	    if(!this.movers.get(i).checkAlive()) {
		this.movers.remove(i);
	    }
	}
    }
    
    public void display() {
	for (int i = 0; i < this.movers.size(); i++) {
	    this.movers.get(i).display();
	}
    }
}
