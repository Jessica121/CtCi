package src;
import java.util.*;
public class PoisonNative {
	public static final int NUM_OF_BOTTLES = 1000;
	public static int nStrips = 10;
	public static int days = 0;
	public static List<Strip> strips;
	public static List<Bottles> bottles;
	
	private static void setUpBottles() {
		bottles = new ArrayList<>();
		for(int i = 0; i < NUM_OF_BOTTLES; i++) {
			bottles.add(new Bottles(i));
		}
		Random r = new Random();
		int thePosionedOne = r.nextInt(NUM_OF_BOTTLES);
		bottles.get(thePosionedOne).setPoison();
	}
	
	public static void setUpStrip() {
		strips = new ArrayList<>();
		int stripIndex = 0;
		for(Bottles b : bottles) {
			if(strips.size() <= stripIndex) strips.add(new Strip(stripIndex));
			strips.get(stripIndex).addDropsFromBottleAtDate(days, b);
			stripIndex = (stripIndex + 1) % nStrips;
		}
//		System.out.println("strips on day: " + days + " is " + strips.toString());
	}

	static class Bottles {
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
	
	static class Strip {
		Map<Integer, List<Bottles>> posionByDate = new HashMap<>();
		public static final int NUM_OF_DAYS_TO_REVEAL_THE_RESULT = 7;
		int id;
		public Strip(int id) {
			this.id = id;
		}
		
		public void addDropsFromBottleAtDate(int date, Bottles b) {
			this.posionByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(b);
		}
		
		public List<Bottles> getResultsOnDate(int date) throws Exception {
			int testDay = date - Strip.NUM_OF_DAYS_TO_REVEAL_THE_RESULT;
			if(!this.posionByDate.containsKey(testDay)) 
				throw new Exception("this date does not have corresponding results!");
			return this.posionByDate.get(testDay);
		}
		
		public boolean hasPosionOnDate(int date) throws Exception {
			List<Bottles> thisDatesResults = getResultsOnDate(date);
			for(Bottles b : thisDatesResults) {
				if(b.isPoisoned()) return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) {
		setUpBottles();
		while(nStrips > 1 && bottles.size() > 1) {
			setUpStrip();
			days += Strip.NUM_OF_DAYS_TO_REVEAL_THE_RESULT;
			for(Strip s : strips) {
				try {
					if(s.hasPosionOnDate(days)) {
						bottles = s.getResultsOnDate(days);
						nStrips--;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(bottles.size() == 1) {
			System.out.println("got posion on bottle #" + bottles.get(0).getId() +
					" and it took " + days + " days.");
		}
		else System.out.println("Error, no posion found.");
	}
}
