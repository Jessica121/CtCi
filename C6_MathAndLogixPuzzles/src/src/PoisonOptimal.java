package src;

import java.util.*;

public class PoisonOptimal {
	public static final int NUM_OF_BOTTLES = 1000;
	public static final int NUM_OF_STRIPS = 10;
	public static List<Strip> strips;
	public static List<Bottles> bottles;
	public static int days = 0;
	public static int posionedId = -1;

	public static class Bottles {
		private int id;
		private boolean isPosioned = false;
		public Bottles(int id) {
			this.id = id;
		}
		public void setPosion() {
			this.isPosioned = true;
		}
		public boolean isPoisoned() {
			return this.isPosioned;
		}
		public int getId() {
			return this.id;
		}
	}
	
	public static class Strip {
		private static final int NUM_OF_DAYS_TILL_SHOWS_RESULTS = 7;
		List<Bottles>[] bottlesByDate = new List[4];
		private int id;
		public Strip(int id) {
			this.id = id;
		}
		public int getId() {
			return this.id;
		}
		public void addBottlesToDate(int date, Bottles b) {
			if(date < 0 || date >= 4) return;
			if(bottlesByDate[date] == null) bottlesByDate[date] = new ArrayList<>();
			bottlesByDate[date].add(b);
		}
		public boolean isPosionedOnDate(int date) throws Exception {
			date -= NUM_OF_DAYS_TILL_SHOWS_RESULTS;
			if(date < 0 || date >= 4 || bottlesByDate[date] == null) throw new Exception("Input Date is wrong! ");
			List<Bottles> bottles = bottlesByDate[date];
			for(Bottles b : bottles) {
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
		int posionedIndex = r.nextInt(NUM_OF_BOTTLES);
		bottles.get(posionedIndex).setPosion();
		posionedId = posionedIndex;
		System.out.println("The Posioned Bottle is #" + posionedIndex);
	}
	
	private static void addBottlesToStrips() {
		for(int i = 0; i < bottles.size(); i++) {
			for(int day = 0; day < 4; day++) {
				addToStripOnDayOfBottle(day, bottles.get(i));				
			}
		}
	}
	
	private static void addToStripOnDayOfBottle(int date, Bottles b) {
		int id = b.getId();
		Strip s = null;
		switch(date) {
			case 0: s = strips.get(id / 100);
			break;
			case 1: s = strips.get(id % 100 / 10);
			break;
			case 2: s = strips.get(id % 10);
			break;
			case 3: s = strips.get((id % 10 - 1 + NUM_OF_STRIPS) % NUM_OF_STRIPS);
			break;
			default: break;
		}
		s.addBottlesToDate(date, b);
	}
	
	private static void setUpStrips() {
		strips = new ArrayList<>();
		for(int i = 0; i < NUM_OF_STRIPS; i++) {
			strips.add(new Strip(i));
		}
	}
	
	public static void setUpExperment() {
		setUpBottles();
		setUpStrips();
		addBottlesToStrips();
	}
	
	private static int[] checkResults() throws Exception {
		int[] res = new int[4];
		Set<Integer> noOfPostiveStrip = new HashSet<>();
		days += Strip.NUM_OF_DAYS_TILL_SHOWS_RESULTS;
		while(days < res.length + Strip.NUM_OF_DAYS_TILL_SHOWS_RESULTS) {
			for(Strip s : strips) {
				if(s.isPosionedOnDate(days)) {
					if(!noOfPostiveStrip.contains(s.getId())) {
						res[days - Strip.NUM_OF_DAYS_TILL_SHOWS_RESULTS] = s.getId();
						noOfPostiveStrip.add(s.getId());
					} else res[days - Strip.NUM_OF_DAYS_TILL_SHOWS_RESULTS] = -1;
					days++;
					break;
				}
			}
		}
		return res;
	}
	
	public static int getPoinsonedOne() throws Exception {
		int[] res = checkResults();
		if(res[0] == -1) return -1;
		if(res[1] == -1) res[1] = res[0];
		if(res[2] == -1) {
			if(res[3] != -1) res[2] = (res[3] + 1) % NUM_OF_STRIPS;
			else res[2] = (res[0] + 1) % NUM_OF_STRIPS == res[1] ? res[0] : res[1];
		}
		System.out.println("\tres array is " + Arrays.toString(res));
		return 100 * res[0] + 10 * res[1] + res[2];
	}
	
	public static void main(String[] args) throws Exception {
		for(int i = 0; i < 20; i++) {
			days = 0;
			setUpExperment();
			int res = getPoinsonedOne();
			System.out.println("Experiement #" + i + " found Posioned Bottle #" + res + "\n\t\tAnd it took " + (days - 1) + " days."
					+ "\n\t\t\t\t\t\t " + (posionedId == res));
		}
		
	}

}
 