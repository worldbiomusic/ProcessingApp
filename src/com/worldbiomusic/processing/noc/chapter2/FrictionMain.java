package com.worldbiomusic.processing.noc.chapter2;

import java.util.ArrayList;
import java.util.List;

import com.worldbiomusic.processing.ProcessingMain;
import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.event.MouseEvent;

public class FrictionMain extends ProcessingMain {
    List<Ch2Mover> m;
    PVector wind;
    PVector gravity;
    Fluid fluid;
    
    int mass;

    public static void main(String[] args) {
	ProcessingMain.main(new String[] {FrictionMain.class.getName(), "1500", "600"});
    }

    @Override
    public void setup() {
	println("start");

	PVector.P = this;

	this.m = new ArrayList<>();

	for (int i = 0; i < 1; i++) {
	    this.m.add(new Ch2Mover(this, random(10, 300), 0, random(1, 5), 20));
	}

	this.fluid = new Fluid(this, 0, height / 2, width, height / 2, 0.3f);

    }

    @Override
    public void draw() {
	background(255);

	this.fluid.display();

	for (Ch2Mover allM : this.m) {
	    float mass = allM.mass;

	    // 바람
	    this.wind = new PVector(0.01f, 0);
	    // 중력 모방
	    this.gravity = new PVector(0, 0.1f * mass);

	    // 힘 적용
	    allM.applyForce(this.wind);
	    allM.applyForce(this.gravity);
	    
	    if(allM.inInside(this.fluid)) {
		allM.drag(this.fluid);
	    }

	    // update & display
	    allM.update();
	    allM.display();
	}

    }

    PVector getFriction(Ch2Mover m) {
	PVector friction = m.getVelocity().get();
	friction.normalize();
	friction.mult(-1);

	float cof = 0.1f; // 마찰 계수
	float normal = 1; // 수직력
	float frictionMag = cof * normal;

	friction.mult(frictionMag);
	return friction;
    }

    @Override
    public void mousePressed() {
	// TODO Auto-generated method stub
	super.mousePressed();
	
	Ch2Mover m = new Ch2Mover(this, mouseX, mouseY, mass, 20);
	this.m.add(m);
    }
    @Override
    public void mouseWheel(MouseEvent e) {
        // TODO Auto-generated method stub
        super.mouseWheel(e);
        if (e.getCount() == -1) {
	    mass += 1;
	} else if (e.getCount() == 1) {
	    mass -= 1;
	}
        System.out.println(mass);
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
