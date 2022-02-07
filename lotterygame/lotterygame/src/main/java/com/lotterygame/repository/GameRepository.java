package com.lotterygame.repository;

import com.lotterygame.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Long>{

}
