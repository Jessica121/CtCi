package ParkingLot;

public class ParkingLot {
	private Level[] levels;
	private int NUM_LEVEL;
	public ParkingLot(int num) {
		this.NUM_LEVEL = num;
		this.levels = new Level[num];
	}
	
	public int getLevels() {
		return this.NUM_LEVEL;
	}

	public Level getLevel(int i) {
		if(i < 0 || i >= this.NUM_LEVEL) return null;
		else return levels[i];
	}
}
