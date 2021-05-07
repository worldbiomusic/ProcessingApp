package com.worldbiomusic.processing;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public abstract class ProcessingMain extends PApplet {
	/*
	 * 모든 프로세싱 메인은 이 클래스를 상속해서 만들 수 있게 함
	 * 
	 */

	// 비상시 참조할 PApplet 변수
	public static PApplet P;

	public static void main(String[] args) {
		/*
		 * class name을 따로 넘겨주고,
		 * 
		 * 나머지 여분의 인자값들은 따로 넘겨서 사용가능하게 함 (내부 배열 args로 사용가능)
		 * 
		 * +인자로 튜닝할 수 있는것 나중에 추가하기
		 */
		System.out.println("args length: " + args.length);
		String className = args[0];
		String[] sketchArgs = new String[args.length - 1];
		for (int i = 0; i < sketchArgs.length; i++) {
			sketchArgs[i] = args[i + 1];
		}

		PApplet.main(className, sketchArgs);
	}

	// only size()
	// settings를 상속하지 못하게 final로 선언(상속해놓고 size안하면 프로그램 작동 안할수잇음)
	@Override
	public void settings() {
		int w = Integer.parseInt(args[0]);
		int h = Integer.parseInt(args[1]);
		size(w, h);

		PVector.P = this;

	}

	@Override
	public abstract void setup();

	@Override
	public abstract void draw();
}
