package com.lotterygame.repository;

import com.lotterygame.entity.Game;
import com.lotterygame.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface GameRepository extends CrudRepository<Game,Long>{

         //@Query(value = "SELECT g FROM Game g WHERE g.user_id =  :user_id")
        ArrayList<Game> findByUserId (Long userId);
}
