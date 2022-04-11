package com.tesla.battlesnake.model;

import java.util.ArrayList;

public class Board {
	
	private float height;
	private float width;
	ArrayList<Snake> snakes = new ArrayList<Snake>();
	ArrayList<Coord> food = new ArrayList<Coord>();
	ArrayList<Object> hazards = new ArrayList<Object>();

}
