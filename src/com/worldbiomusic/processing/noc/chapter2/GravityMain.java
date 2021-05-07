package com.worldbiomusic.processing.noc.chapter2;

import java.util.ArrayList;
import java.util.List;

import com.worldbiomusic.processing.ProcessingMain;

import processing.event.MouseEvent;

public class GravityMain extends ProcessingMain {
	int mass = 10;
	List<Ch2Mover> movers;

	public static void main(String[] args) {
		ProcessingMain.main(new String[] { GravityMain.class.getName(), "1000", "600" });
	}

	@Override
	public void setup() {
		smooth();

		this.movers = new ArrayList<>();
	}

	@Override
	public void draw() {
		background(255);
		for (Ch2Mover m : this.movers) {

			// gravity attraction
			for (Ch2Mover other : this.movers) {
				if (m != other) {
					m.attractOther(other);
				}
			}

			if (m.mass != 100) {
				m.update();
			}

			m.display();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if (e.getButton() == LEFT) {
			GravityMover m = new GravityMover(this, mouseX, mouseY, mass, 3);
			this.movers.add(m);
		} else if (e.getButton() == RIGHT) {
			this.movers.clear();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//	super.mouseDragged(e);
//	if (e.getButton() == LEFT) {
//	    GravityMover m = new GravityMover(this, mouseX, mouseY, mass, 3);
//	    this.movers.add(m);
//	}
	}

	@Override
	public void mouseWheel(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseWheel(e);
		if (e.getCount() == -1) {
			mass += 5;
		} else if (e.getCount() == 1) {
			mass -= 5;
		}
		System.out.println(mass);
	}
}
