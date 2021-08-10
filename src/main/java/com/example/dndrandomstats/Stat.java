package com.example.dndrandomstats;

import java.util.Arrays;

public class Stat {
	Die dSix = new Die(6);
	int total = 0;
	int rolls[] = {0,0,0,0};
	public Stat() {
	
	}
	public int rollStat() {

		//rolls 4 dice
		for (int i = 0; i < rolls.length; i++ ) {
			rolls[i] = dSix.roll();
		}
		//returns total without the lowest number
		rolls = dropLowest(rolls);
		for(int result: rolls) {
			total += result;
		}
		return total;
	}
	public int[] dropLowest(int[] array) {
		//sorts array in accending order and sets 
		//first value to 0 to remove the lowest number from set
		Arrays.sort(array);
		array[0] = 0;
		return array;
	}
}
