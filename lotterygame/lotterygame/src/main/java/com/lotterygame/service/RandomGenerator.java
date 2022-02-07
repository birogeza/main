package com.lotterygame.service;

import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class RandomGenerator {

    Random random = new Random();
    HashSet<Integer> randomSet = new HashSet<Integer>();

    public HashSet<Integer> generate() {
        while (randomSet.size() < 5) {
            randomSet.add(random.nextInt(20) + 1);
        }
        //Only for test purposes, write out the result
        for (Integer i : randomSet) {
            System.out.println(i);
        }
        return randomSet;
    }



}
