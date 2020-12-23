package com.worldbiomusic.processing.chapter0;

import processing.core.PApplet;

public class MainPixelArt extends PApplet {
    Walker w;

    public static void main(String[] args) {
	PApplet.main(MainPixelArt.class.getName());
    }

    // only size()
    @Override
    public void settings() {
	size(1500, 1000);
    }

    @Override
    public void setup() {
	this.w = new Walker(this, width / 2, height / 2);
	println("start");

	// perllin noise 이용한 픽셀 아트
	loadPixels();
	float n = 0, m = 0;
	float n2 = 1110, m2 = 110;
	float n3 = 11110, m3 = 11110;

	for (int x = 0; x < width; x++) {
	    m = m2 = m3 = 0;
	    for (int y = 0; y < height; y++) {
		float bright1 = map(noise(n, m), 0, 1, 0, 255);
		float bright2 = map(noise(n2, m2), 0, 1, 0, 255);
		float bright3 = map(noise(n3, m3), 0, 1, 0, 255);
		pixels[x + y * width] = color(bright1, bright2, bright3);

		m += 0.01;
		m2 += 0.01;
		m3 += 0.01;
	    }
	    n += 0.01;
	    n2 += 0.01;
	    n3 += 0.01;
	}

	updatePixels();
    }

    @Override
    public void draw() {
//	this.w.stepWithPerlinNoise();
//	this.w.display();

    }
}
