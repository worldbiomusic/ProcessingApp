# EcoSystem chapter2

## update
- FishFoodMover삭제
- FishMover를 상속한 여러 물고기 사이에서 천적관계 생성
- Obj생성 (1.Life, 2.Thing으로 나뉨)
- Life와 상대적인 정적인 것들인 Thing 생성 (여전히 Mover 상속받는 클래스임, 생명이 아닐 뿐 ex.지우개)



## Life 행동  
- Life는 기본적으로 Life또는 Thing을 먹고 살아감 (foodTarget이 Obj형)
- Life는 평소에 foodTarget이 없으면 perlin noise로 유유히 돌아다님
- 천적이 먹이와 일정거리 가까워지면 천적에게 attractedToOther(먹이)실행되고, 먹이에게 repulsedFromOther(천적)가 실행됨
- 먹이와 천적이 가까워 지면 먹이 삭제됨
- 


## 수정할 것
- Obj의 alive가 false라도 display되게 수정할 에정