package com.worldbiomusic.processing.noc.chapter2.ecosystem;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class EcoSystem {
    List<Obj> movers;
    PApplet p;

    public EcoSystem(PApplet p) {
	this.p = p;
	this.movers = new ArrayList<>();
    }
    
    public void addObj(Obj mover) {
	this.movers.add(mover);
    }
    
    public void removeObj(Obj mover) {
	this.movers.remove(mover);
    }
    
    public void update() {
	// check movers dead
	this.checkObjsAlive();
	
	// update movers
	for (int i = 0; i < this.movers.size(); i++) {
	    this.movers.get(i).checkAroundObjs(this.movers);
	    this.movers.get(i).update();
	}
    }
    
    public void checkObjsAlive() {
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
	
//	this.movers.forEach(life -> life.display());
    }
}
