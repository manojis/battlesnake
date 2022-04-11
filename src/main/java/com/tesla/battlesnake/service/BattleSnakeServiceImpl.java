package com.tesla.battlesnake.service;

import java.util.List;

import com.tesla.battlesnake.model.Board;
import com.tesla.battlesnake.model.Snake;
import com.tesla.battlesnake.model.You;

public class BattleSnakeServiceImpl implements BattleSnakeService{
	
	@Override
	public Snake findMySnake(You request) {
		return new Snake();
    }

	@Override
	public List<Snake> findOtherSnakes(Board request) {
		// TODO Auto-generated method stub
		return null;
	}

}
