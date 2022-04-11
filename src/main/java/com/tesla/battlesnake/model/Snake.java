package com.tesla.battlesnake.model;

import java.util.ArrayList;

public class Snake {
	
	private String id;
	private String name;
	private int latency;
	private int health;
	ArrayList<Coord> body = new ArrayList<Coord>();
	Head head;
	private int length;
	private String shout;
	private Squad squad;
	private Customizations customization;
}
