package com.lotterygame.service;

import com.lotterygame.entity.Game;
import com.lotterygame.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface GameService {

    public ArrayList<Game> listAllGamesByUser();

}
