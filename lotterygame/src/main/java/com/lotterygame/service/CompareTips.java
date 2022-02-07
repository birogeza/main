package com.lotterygame.service;

import com.lotterygame.entity.Game;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CompareTips {

    ArrayList<Integer> tipList = new ArrayList<Integer>();


    public String checkNums(Game game){
        int counter = 0;
        RandomGenerator rg = new RandomGenerator();
        tipList.add(game.getNum1());
        tipList.add(game.getNum2());
        tipList.add(game.getNum3());
        tipList.add(game.getNum4());
        tipList.add(game.getNum5());

        for(int i = 0; i < 5; ++i){
            if((rg.generate()).contains(tipList.get(i))){
                counter++;
            }
        }

        if(counter>1){
            return "Gratulálok " + counter + " találatod van!";
        }else{
            return "Sajnos ma nem nyertél!";
        }
    }

}
