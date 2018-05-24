package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoisonBestSol {
	public static final int NUM_OF_BOTTLES = 1000;
	public static final int NUM_OF_STRIPS = 10;
	public static int days = 0;
	public static List<Strip> strips;
	public static List<Bottles> bottles;
	
	public static class Bottles {
		private int id;
		private boolean isPoisoned;
		public Bottles(int id) {
			this.id = id;
			this.isPoisoned = false;
		}
		
		public int getId() {
			return this.id;
		}
		
		public void setPoison() {
			System.out.println("No. " + this.id + " is Poisoned.");
			this.isPoisoned = true;
		}
		
		public boolean isPoisoned() {
			return this.isPoisoned;
		}
	}
	
	public static class Strip {
		List<Bottles> posion;
		public static final int NUM_OF_DAYS_TO_REVEAL_THE_RESULT = 7;
		int id;
		public Strip(int id) {
			this.id = id;
			this.posion = new ArrayList<>(); 
		}
		
		public void addToList(Bottles b) {
			this.posion.add(b);
		}
		
		public boolean hasPosion() {
			for(Bottles b : posion) {
				if(b.isPoisoned()) return true;
			}
			return false;
		}
	}
	
	private static void setUpBottles() {
		bottles = new ArrayList<>();
		for(int i = 0; i < NUM_OF_BOTTLES; i++) {
			bottles.add(new Bottles(i));
		}
		Random r = new Random();
		int thePosionedOne = r.nextInt(NUM_OF_BOTTLES);
		bottles.get(thePosionedOne).setPoison();
	}
	
	private static void setUpStrips() {
		strips = new ArrayList<>();
		for(int i = 0; i < NUM_OF_STRIPS; i++) {
			strips.add(new Strip(i));
		}
	}
	
	public static void addPosionToBottle() {
		for(int i = 0; i < bottles.size(); i++) {
			for(int j = 0; j < NUM_OF_STRIPS; j++) {
				if(((i >> j) & 1) == 1) {
					strips.get(j).addToList(bottles.get(i));
				}
			}
		}
	}
	
	public static int getResults() {
		int res = 0;
		for(int i = 0; i < strips.size(); i++) {
			if(strips.get(i).hasPosion()) res += Math.pow(2, i);
		}
		return res;
	}

	public static void main(String[] args) {
		for(int i = 0; i < 20; i++) {
			setUpBottles();
			setUpStrips();
			addPosionToBottle();
			int res = getResults();
			System.out.println("\t\t\tFound poinsoned one is " + res);
		}
		
	}

}
