package com.worldbiomusic.processing.noc.chapter4;

import java.util.ArrayList;
import java.util.List;

import com.worldbiomusic.processing.ProcessingMain;
import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PImage;
import processing.event.MouseEvent;

public class Ch4Main extends ProcessingMain {

	List<ParticleSystem> ps;
	PVector gravity = new PVector(0, 0.5f);
	
	Repeller repeller;
	
	PImage img;

	public static void main(String[] args) {
		ProcessingMain.main(new String[] { Ch4Main.class.getName(), "1000", "600" });
	}

	@Override
	public void setup() {
		this.ps = new ArrayList<ParticleSystem>();
		this.repeller = new Repeller(this, width / 2, height / 2, 50);
		this.img = loadImage("glowing2.png");
	}
	
	@Override
	public void draw() {
		background(0);
		
		float windPower = map(mouseX, 0, width, -1f, 1f);
		PVector wind = new PVector(windPower, 0);

		for (ParticleSystem system : this.ps) {
			system.applyForce(wind);
//			system.applyRepeller(this.repeller);
			system.run();
			system.addParticle();
		}

//		this.repeller.display();
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if(event.getButton() == LEFT) {
			this.ps.add(new ParticleSystem(this, new PVector(mouseX, mouseY), this.img));
		} else {
			this.ps.clear();
		}
	}

}
