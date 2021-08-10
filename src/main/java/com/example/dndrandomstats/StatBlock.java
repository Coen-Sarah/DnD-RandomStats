package com.example.dndrandomstats;

public class StatBlock {
	Stat[] statBlock = new Stat[6];
	//the generateStats() fills the statBlock array with 6 stat objects
	public void generateStats() {
		for(int i = 0; i < 6; i++) {
			statBlock[i] = new Stat();
			statBlock[i].rollStat();
		}
	}
	public int[] toArray() {
		int[] output = new int[6];
		for (int i = 0; i < 6; i ++) {
			output[i] = statBlock[i].total;
		}
		
		return output;
	}
}
