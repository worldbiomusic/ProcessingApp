package com.worldbiomusic.processing.noc.chapter2.ecosystem;

import com.worldbiomusic.processing.ProcessingMain;

public class EcoSystemMain extends ProcessingMain {
    EcoSystem eco;
    
    public static void main(String[] args) {
	ProcessingMain.main(new String[] { EcoSystemMain.class.getName(), "1000", "600" });
    }

    @Override
    public void setup() {
	println("start");

	this.eco = new EcoSystem(this);

//	// anchovy
	for (int i = 0; i < 20; i++) {
	    this.eco.addObj(this.getNewAnchovy());
	}

	// sharks
	for (int i = 0; i < 3; i++) {
	    this.eco.addObj(this.getNewShark());
	}

    }

    @Override
    public void draw() {
	background(255);

	this.eco.update();
	this.eco.display();
    }

    public Anchovy getNewAnchovy() {
	Anchovy anc = new Anchovy(this);
	anc.setTopSpeed(2);
	anc.setR(2);
	anc.setColor(0, 255, 0);
	return anc;
    }

    public Shark getNewShark() {
	Shark sha = new Shark(this);
	sha.setTopSpeed(3);
	sha.setR(3);
	sha.setColor(255, 0, 0);
	return sha;
    }

    @Override
    public void mousePressed() {
	Anchovy anc = getNewAnchovy();
	anc.teleport(mouseX, mouseY);
	this.eco.addObj(anc);
    }

    @Override
    public void mouseDragged() {
	Anchovy anc = getNewAnchovy();
	anc.teleport(mouseX, mouseY);
	this.eco.addObj(anc);
    }

}
