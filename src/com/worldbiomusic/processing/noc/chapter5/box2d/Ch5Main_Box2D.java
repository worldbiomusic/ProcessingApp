package com.worldbiomusic.processing.noc.chapter5.box2d;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import com.worldbiomusic.processing.ProcessingMain;

import processing.event.MouseEvent;
import shiffman.box2d.Box2DProcessing;

public class Ch5Main_Box2D extends ProcessingMain {

	Box2DProcessing box2d;
	List<Drawing> drawings;

	float size = 10;

	Set<Integer> keyList;

	Spring spring;
	Drawing box;

	Drawing circle, circle2;

	public static void main(String[] args) {
		ProcessingMain.main(new String[] { Ch5Main_Box2D.class.getName(), "1000", "600" });
	}

	@Override
	public void setup() {
		System.out.println("start");
		this.keyList = new HashSet<>();

		// world
		this.box2d = new Box2DProcessing(this);
		this.box2d.createWorld();
		this.box2d.listenForCollisions();
		this.box2d.world.setContactListener(new Ch5JBox2DContactListener());

		this.drawings = new ArrayList<Drawing>();
		this.circle = new Circle(this, this.box2d, 100, 100, 10, BodyType.DYNAMIC);
		this.drawings.add(this.circle);

//		this.spring = new Spring(this, box2d, this.box);

		// userData 설정
		this.drawings.forEach(d -> {
			d.setUserData();
		});
	}

	@Override
	public void draw() {
		this.box2d.step();
		this.box2d.setGravity(0, 0);

		background(75);
//
//		Vec2 mouse = this.box2d.coordPixelsToWorld(mouseX, mouseY);
//		Vec2 circlePos = this.circle.body.getWorldCenter();
//		Vec2 circleToMouse = mouse.sub(circlePos);
//		this.circle.body.setLinearVelocity(circleToMouse);

		// display drawings
		for (Drawing d : this.drawings) {
			d.display();
		}

		textSize(20);
		text("Size: " + this.size, 10, 30);
	}

	@Override
	public void mousePressed(MouseEvent event) {
		switch (event.getButton()) {
		case LEFT:
			break;
//		case RIGHT:
//			this.drawings.add(new Box(this, this.box2d, mouseX, mouseY, size));
//			break;
		case CENTER:
			// 삭제할때: box2d도 지우고, processing에서도 지우고
			for (Drawing d : this.drawings) {
				d.kill(this.drawings);
			}
			this.drawings.clear();
			break;
		}
	}

	@Override
	public void mouseWheel(MouseEvent e) {
		super.mouseWheel(e);
		if (e.getCount() == -1) {
			// wheel up
			this.size += 5;
			this.size = constrain(this.size, 0, 100);
		} else if (e.getCount() == 1) {
			// wheel down
			this.size -= 5;
			this.size = constrain(this.size, 5, 100);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void keyPressed(processing.event.KeyEvent e) {
		int key = e.getKeyCode();
		this.keyList.add(key);

		// kinematic 조종
		this.controlKinematicDrawing(e);

		this.spawnDrwaing(e);
	}

	void controlKinematicDrawing(processing.event.KeyEvent e) {
		Vec2 circle = this.box2d.getBodyPixelCoord(this.circle.body);
		Vec2 applyVec = null;
		int power = 2000;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
			if (this.checkPressedKeys(KeyEvent.VK_UP)) {
				applyVec = circle.add(new Vec2(0, -power));
			}
			if (this.checkPressedKeys(KeyEvent.VK_DOWN)) {
				applyVec = circle.add(new Vec2(0, power));
			}
			if (this.checkPressedKeys(KeyEvent.VK_RIGHT)) {
				applyVec = circle.add(new Vec2(power, 0));
			}
			if (this.checkPressedKeys(KeyEvent.VK_LEFT)) {
				applyVec = circle.add(new Vec2(-power, 0));
			}
			if (applyVec != null) {
				applyVec = applyVec.sub(circle);

				applyVec = this.box2d.vectorPixelsToWorld(applyVec);
//			this.circle.body.setLinearVelocity(applyVec);
				this.circle.body.applyForceToCenter(applyVec);
			}
			break;
		}

	}

	void spawnDrwaing(processing.event.KeyEvent e) {
		if (this.checkPressedKeys(KeyEvent.VK_1)) {
			this.drawings.add(new Circle(this, this.box2d, mouseX, mouseY, size, BodyType.DYNAMIC));
		} else if (this.checkPressedKeys(KeyEvent.VK_2)) {
			this.drawings.add(new Box(this, this.box2d, mouseX, mouseY, size, size));
		} else if (this.checkPressedKeys(KeyEvent.VK_3)) {
			this.drawings.add(new StickCandy(this, this.box2d, new Vec2(mouseX, mouseY)));
		} else if (this.checkPressedKeys(KeyEvent.VK_4)) {
			this.drawings.add(new CirclePair(this, this.box2d));
		} else if (this.checkPressedKeys(KeyEvent.VK_5)) {
			this.drawings.add(new CirclePairBridge(this, box2d));
		} else if (this.checkPressedKeys(KeyEvent.VK_6)) {
			this.drawings.add(new Surface(this, box2d, Surface.Type.PERLIN_NOISE));
		} else if (this.checkPressedKeys(KeyEvent.VK_7)) {
			this.drawings.add(new Windmill(this, box2d));
		} else if (this.checkPressedKeys(KeyEvent.VK_1, KeyEvent.VK_CONTROL)) {
		}
		
		this.drawings.forEach(d->{d.setUserData();});
	}

	@Override
	public void keyReleased(processing.event.KeyEvent e) {
		int key = e.getKeyCode();
		this.keyList.remove(key);
	}

	public boolean checkPressedKeys(int... keys) {
		if (keys.length != this.keyList.size()) {
			return false;
		}

		for (int key : keys) {
			if (!this.keyList.contains(key)) {
				return false;
			}
		}

		return true;
	}
	
	
	class Ch5JBox2DContactListener implements ContactListener {

		@Override
		public void beginContact(Contact ct) {
			System.out.println("collision");
			Fixture f1 = ct.getFixtureA();
			Fixture f2 = ct.getFixtureB();

			Body body1 = f1.getBody();
			Body body2 = f2.getBody();

			Object b1Data = body1.getUserData();
			Object b2Data = body2.getUserData();
			if (b1Data instanceof Circle && b2Data instanceof Circle) {
				System.out.println("circle collides");
				Circle c1 = (Circle) b1Data;
				Circle c2 = (Circle) b2Data;
				c1.changeColor();
				c2.changeColor();
			}
		}

		@Override
		public void endContact(Contact arg0) {
		}

		@Override
		public void postSolve(Contact arg0, ContactImpulse arg1) {
		}

		@Override
		public void preSolve(Contact arg0, Manifold arg1) {
		}

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
//
//
//
//
//
//
//
//
