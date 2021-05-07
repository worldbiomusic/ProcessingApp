package com.worldbiomusic.processing.noc.chapter3;

import java.util.ArrayList;
import java.util.List;

import com.worldbiomusic.processing.ProcessingMain;
import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Ch3Main extends ProcessingMain {
	Ch3Mover m;
	List<Oscillator> osc;

	float angle = 0, startAngle = 0, nextEllipseAngleInterval = 0.01f;
	float angleVel = 0.01f;
	float amplitude = height;

	Wave wave;

	List<Wave> waves;

	public static void main(String[] args) {
		ProcessingMain.main(new String[] { Ch3Main.class.getName(), "1000", "600" });
	}

	@Override
	public void setup() {
		m = new AngleMover(this, 100, 100, 10, 5);

		this.osc = new ArrayList<Oscillator>();
		this.osc.add(new Oscillator(this));
		this.osc.add(new Oscillator(this));
		this.osc.add(new Oscillator(this));

		this.wave = new Wave(this, 100, 1, new PVector(0, 300), new PVector(500, 300));

		this.waves = new ArrayList<Wave>();
	}

	@Override
	public void draw() {
		background(255);

//		this.wave.wave();

		this.waves.forEach((w) -> w.wave());

	}

	void severalOscillator() {
		angle = startAngle;
		for (int x = 0; x <= width; x += 24) {
//			float y = map(sin(angle), -1, 1, 0, height);
			float y = height * noise(angle);
//			System.out.println(y);
			strokeWeight(2);
			fill(0, 255, 0);
			ellipse(x, y, 40, 40);
			angle += 0.05;
		}
		startAngle += angleVel;
	}

	void oscillator() {
		this.osc.forEach((e) -> {
			e.oscillator();
			e.display();
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == UP) {
			// 값 줄때 현재 angle값을 가지고 벡터방향으로 변환해서 벡터값 applyForce 해야 함
			PVector v = m.getAngleDirection();

			m.applyForce(v);
		}
		if (e.getKeyCode() == DOWN) {
			PVector v = m.getAngleDirection();
			v.mult(-1);

			m.applyForce(v);
		}
		if (e.getKeyCode() == RIGHT) {
			m.addAngleForce(0.001f);
		}
		if (e.getKeyCode() == LEFT) {
			m.addAngleForce(-0.001f);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
//		super.mousePressed(event);
//		this.osc.add(new Oscillator(this));

		if (e.getButton() == RIGHT) {
			this.waves.clear();
		} else if (e.getButton() == LEFT) {
			System.out.println("mouse");
			System.out.println(width / 2 + ":" + height / 2);
			System.out.println(e.getX() + ":" + e.getY());
			this.waves.add(new Wave(this, 100, 7f, new PVector(0, 0), new PVector(e.getX(), e.getY())));
		}
	}
}
