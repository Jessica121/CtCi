package ParkingLot;
public class Level {
	private ParkingSpot[] spots;
	private int avaliable;
	// 2D array calculated into 1D array
	private int NUM_OF_SPOTS, NUM_OF_ROWS;
	// assume that the num of spots % num of rows == 0
	public Level(int numSpots, int numRows) {
		this.NUM_OF_SPOTS = numSpots;
		this.NUM_OF_ROWS = numRows;
		spots = new ParkingSpot[NUM_OF_SPOTS];
		initParkingSpots();
		this.avaliable = NUM_OF_SPOTS;
	}
	
	private void initParkingSpots() {
		for(int i = 0; i < NUM_OF_ROWS; i++) {
			for(int j = 0; j < NUM_OF_SPOTS / NUM_OF_ROWS; j++) {
				spots[i * NUM_OF_SPOTS / NUM_OF_ROWS + j] = new ParkingSpot(1, this, i);
			}
		}
	}
	
	// find a valid starting index to park.
	// again if set something, must be a way to unset something.
	public boolean park(Vehicle v) {
		if(this.avaliable < v.getSize()) return false;
		else {
			int fitIndex = findIndex(v);
			if(fitIndex == -1) return false;
			parkAtIndex(fitIndex, v);
			return true;
		}
	}
	
	/*
	 * since in each row, there can be gaps between parked lots, which happened when some vechiles leave.
	 * so use a brute force way to find the consecutive parkling lots, nothing fancy.
	 * for each row, if find an empty index, mark it, continue to check next ones, increase a count, if count == vechile size, 
	 * stop and park it. else if interrupted by finding an non empty spot, then clear the current count and continue to find till
	 * the end of the row. if not found, go to the next row.
	 * 
	 * */
	private int findIndex(Vehicle v) {
		int count = 0;
		for(int i = 0; i < NUM_OF_ROWS; i++) {
			int j = 0;
			while(j < NUM_OF_SPOTS / NUM_OF_ROWS) {
				count = 0;
				while(spots[i * NUM_OF_SPOTS / NUM_OF_ROWS + j++].isEmpty()) {
					if(count == v.getSize()) return i * NUM_OF_SPOTS / NUM_OF_ROWS + j;
					count++;
				}
			}
		}
		return -1;
	}
	
	// maybe needs a different row, so maybe have a park at row, index.
	private void parkAtIndex(int emptyIndex, Vehicle v) {
		for(int i = emptyIndex; i < emptyIndex + v.getSize(); i++) {
			spots[i].setVehicle(v);
			v.park(spots[i]);
		}
		this.avaliable -= v.getSize();
	}
	
	// when leaving, free the spots and increase the availble spots back. 
	// If decrease something, must have a way to increase it back.
	public void leave(Vehicle v) {
		v.freeSpots();
		this.avaliable += v.getSize();
	}
	
	public boolean isFull() {
		return avaliable == 0;
	}
	
}
