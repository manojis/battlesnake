package com.tesla.battlesnake.service;

import java.util.List;

import com.tesla.battlesnake.model.Board;
import com.tesla.battlesnake.model.Snake;
import com.tesla.battlesnake.model.You;

public interface BattleSnakeService {
	
	Snake findMySnake(You you);	
	List<Snake> findOtherSnakes(Board board);
}
