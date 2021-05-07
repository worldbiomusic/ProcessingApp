package com.worldbiomusic.processing.noc.chapter1.ecosystem;

import java.util.List;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public class FishMover extends Life {

    float xTime, yTime;
    FishFoodMover foodTarget;

    public FishMover(PApplet p, int topspeed) {
	super(p, topspeed);
    }

    @Override
    public void update() {
	// foodTarget이 있다면 perlinNoiseVector와 foodTargetVector의 중간지점으로 Vector방향 바꾸기
	if (this.foodTarget != null) {
	    PVector currentVector = this.accelation;
	    PVector fishToFoodVector = PVector.sub(this.foodTarget.getLocation(), this.getLocation());
	    PVector dir = PVector.add(currentVector, fishToFoodVector);
	    dir.normalize();
	    dir.mult(0.1f);
	    
	    this.accelation = dir;
	    
	    this.generalUpdate();
	} else {
	    perlinNoiseUpdate();
	}
    }

    @Override
    public void checkAroundMovers(List<Life> movers) {
	super.checkAroundMovers(movers);
	for (int i = 0; i < movers.size(); i++) {
	    if (movers.get(i) instanceof FishFoodMover) {
		FishFoodMover food = (FishFoodMover) movers.get(i);
		this.attractToFood(food);
	    }
	}
    }

    private void attractToFood(FishFoodMover food) {
	/*
	 * 거리(mag)가 일정거리 이하일때 가장 가까운 음식으로 경로 변경후 음식 삭제
	 */
	PVector fishToFoodVector = PVector.sub(this.getLocation(), food.getLocation());
	float dist = fishToFoodVector.mag();
	
	// 엄청 가까우면 음식 삭제
	if(dist < 20) {
	    this.foodTarget = null;
	    food.alive = false;
	}
	// 거리가 일정 이하일때 이끌리기
	else if (dist < 100) {
	    if(this.foodTarget == null) {
		// targetFood 등록
		    this.foodTarget = food;
	    }
	    else {
		float currentFoodTargetDist = (PVector.sub(this.getLocation(), this.foodTarget.getLocation())).mag();
		if (dist < currentFoodTargetDist) {
		    // targetFood 등록
		    this.foodTarget = food;
		}
	    }
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
