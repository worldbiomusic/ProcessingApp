package com.worldbiomusic.processing.noc.chapter2.ecosystem;

import processing.core.PApplet;

public class Anchovy extends Life{
    // 멸치

    public Anchovy(PApplet p) {
	super(p);
	
	this.addEnemyList(Shark.class);
    }
    
    
}
