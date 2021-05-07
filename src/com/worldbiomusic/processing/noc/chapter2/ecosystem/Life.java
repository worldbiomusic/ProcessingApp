package com.worldbiomusic.processing.noc.chapter2.ecosystem;

import java.util.ArrayList;
import java.util.List;

import com.worldbiomusic.processing.noc.chapter1.PVector;

import processing.core.PApplet;

public class Life extends Obj {
    // food target
    Obj foodTarget;
    Obj enemyTarget;

    List<Class<Obj>> foodList = new ArrayList<>();
    List<Class<Obj>> enemyList = new ArrayList<>();

    public Life(PApplet p) {
	super(p);
    }

    @Override
    public void checkAroundObjs(List<Obj> objs) {
	for (int i = 0; i < objs.size(); i++) {
	    Obj obj = objs.get(i);
	    if (isFood(obj)) {
		this.findFood(obj);
	    }
	    if (isEnemy(obj)) {
		this.findEnemy(obj);
	    }
	}

    }

    private void findEnemy(Obj enemy) {
	PVector enemyToMeVector = PVector.sub(this.getLocation(), enemy.getLocation());
	float dist = enemyToMeVector.mag();

	// 거리가 일정 이하일때 도망가기
	if (dist < 30) {
	    if (this.enemyTarget == null) {
		// targetFood 등록
		this.enemyTarget = enemy;
	    } else { // 더 가까운 food이면 변경
		float currentEnemyTargetDist = (PVector.sub(this.getLocation(), this.enemyTarget.getLocation())).mag();
		if (dist < currentEnemyTargetDist) {
		    // targetFood 등록
		    this.enemyTarget = enemy;
		}
	    }
	} else { // 너무 멀 때 target focus 취소
	    if (this.enemyTarget == enemy) {
		this.enemyTarget = null;
	    }
	}
    }

    protected void findFood(Obj food) {
	/*
	 * 거리(mag)가 일정거리 이하일때 가장 가까운 음식으로 경로 변경후 음식 삭제
	 */
	
	// 죽어있느면 패스
	if(food.checkAlive() == false) {
	    return;
	}
	
	PVector lifeToFoodVector = PVector.sub(this.getLocation(), food.getLocation());
	float dist = lifeToFoodVector.mag();

	// 가까우면 음식 삭제
	if (dist < 20) {
	    this.foodTarget.alive = false;
	    this.foodTarget = null;
	}
	// 거리가 일정 이하일때 이끌리기
	else if (dist < 100) {
	    if (this.foodTarget == null) {
		// targetFood 등록
		this.foodTarget = food;
	    } else { // 더 가까운 food이면 변경
		float currentFoodTargetDist = (PVector.sub(this.getLocation(), this.foodTarget.getLocation())).mag();
		if (dist < currentFoodTargetDist) {
		    // targetFood 등록
		    this.foodTarget = food;
		}
	    }
	} else { // 너무 멀 때 target focus 취소
	    if (this.foodTarget == food) {
		this.foodTarget = null;
	    }
	}

	// 버그: foodTarget을 먹었는데 
	if (this.foodTarget != null) {
	    if (this.foodTarget instanceof Life) {
		if (this.foodTarget.checkAlive() == false) {
		    this.foodTarget = null;
		}
	    }
	}
    }

    @Override
    public void update() {
	// foodTarget이 있다면 perlinNoiseVector와 foodTargetVector의 중간지점으로
	// Vector방향 바꾸기
	if (this.enemyTarget != null) {
	    this.repulsedFromOther(this.enemyTarget, 50);
	    this.generalUpdate();
	} else if (this.foodTarget != null) {
	    this.attractedToOther(this.foodTarget, 40);
	    this.generalUpdate();
	} else {
	    perlinNoiseUpdate();
	}
    }

    public void addFoodList(Class food) {
	// 같은 클래스면 중복할 필요 없음
	if (this.foodList.contains(food.getClass())) {
	    return;
	}

	foodList.add(food);
    }

    public boolean isFood(Obj obj) {
	return this.foodList.contains(obj.getClass());
    }

    public void addEnemyList(Class obj) {
	// 같은 클래스면 중복할 필요 없음
	if (this.enemyList.contains(obj.getClass())) {
	    return;
	}

	enemyList.add(obj);
    }

    public boolean isEnemy(Obj obj) {
	return this.enemyList.contains(obj.getClass());
    }
}
