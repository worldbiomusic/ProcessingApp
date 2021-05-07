package com.worldbiomusic.processing.noc.chapter5.toxiclibs;

import processing.core.PApplet;
import toxi.physics2d.VerletPhysics2D;

public abstract class Drawing {
	PApplet p;
	VerletPhysics2D physics;

	public Drawing(PApplet p, VerletPhysics2D physics) {
		this.p = p;
		this.physics = physics;
	}

	public abstract void display();

	// 상속하는 클래스들은 무조건 사용할 물리 객체들을 이 메소드에 등록해야 하고 생성자에서 실행시켜야 함
	public abstract void addObjectToPhysics();
}
