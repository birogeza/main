package com.lotterygame.service;

import com.lotterygame.entity.Game;
import com.lotterygame.entity.User;
import com.lotterygame.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameServiceImpl implements GameService{

    private Long userId;
    private GameRepository gameRepository;

    @Autowired
    public void setGameRepository(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @Override
    public ArrayList<Game> listAllGamesByUser(){
        ArrayList<Game> games = gameRepository.findByUserId(this.userId);
        return games;
    }

    public void setUserId(Long userId){
        this.userId = userId;
        //this.userId = 2L;
    }

}
