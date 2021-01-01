package com.worldbiomusic.processing.noc.chapter2;

import processing.core.PApplet;

public class HeliumBalloon extends Mover{

    public HeliumBalloon(PApplet p) {
	super(p);
    }
    
    @Override
    public void checkEdge() {
	if (this.getLocation().x > p.width) {
	    this.getLocation().x = 0;
	} else if (this.getLocation().x < 0) {
	    this.getLocation().x = p.width;
	}

	if (this.getLocation().y > p.height) {
	    this.getLocation().y = p.height;
	} else if (this.getLocation().y < 0) {
	    this.getLocation().y = 0;
	}
    }
}
