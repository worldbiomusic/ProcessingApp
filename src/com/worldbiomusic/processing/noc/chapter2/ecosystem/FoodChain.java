package com.worldbiomusic.processing.noc.chapter2.ecosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodChain {
    public static Map<Class, List<Class>> foodChain;
    
    static {
	foodChain.put(Shark.class, new ArrayList<>());
	foodChain.get(Shark.class).add(Anchovy.class);
    }
}
