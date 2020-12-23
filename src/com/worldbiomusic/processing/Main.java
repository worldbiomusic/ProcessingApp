package com.worldbiomusic.processing;

import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {

    // 비상시 참조할 PApplet 변수
    public static PApplet P;
    
    protected static String className;
    protected int width, height;
    
    public Main(String _className, int width, int height) {
	P = this;
	className = _className;
	this.width = width;
	this.height = height;
    }
    
    protected void setClass(String _className, int width, int height) {
	P = this;
	className = _className;
	this.width = width;
	this.height = height;
    }
    

    // only size()
    @Override
    public void settings() {
	size(width, height);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
	
    }
}
