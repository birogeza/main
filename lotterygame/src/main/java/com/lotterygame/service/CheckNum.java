package com.lotterygame.service;

import com.lotterygame.entity.Game;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Iterator;

@Service
public class CheckNum {

    Game game = new Game();
    private HashSet<Integer> userNumbers = new HashSet<Integer>();

    public String numbersAreDifferent(Game game){
        String msg = "";
        userNumbers.add(game.getNum1());
        userNumbers.add(game.getNum2());
        userNumbers.add(game.getNum3());
        userNumbers.add(game.getNum4());
        userNumbers.add(game.getNum5());

        if(userNumbers.size() < 5) {
            return "A megadott számoknak mind különbözőnek kell lenniük! ";
        }else{
            Iterator<Integer> it = userNumbers.iterator();
            while(it.hasNext()){
                int num = it.next();
                if(num > 90 || num < 1) {
                    return "A számoknak 1-90 közötti egésznek kell lenniük!";
                }
            }
            return "OK";
        }
    }



}
