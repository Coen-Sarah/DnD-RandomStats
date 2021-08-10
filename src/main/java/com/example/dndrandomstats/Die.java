package com.example.dndrandomstats;

public class Die {
	int sides;
	// generates a die object using the number of sides from the constructor
	Die(int sides){
		this.sides = sides;
	}
	//rolls the die
	public int roll() {
		int result = (int) (Math.random()*sides) + 1;
		return result;
	}
}
