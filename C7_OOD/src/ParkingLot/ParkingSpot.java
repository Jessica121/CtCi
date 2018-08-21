package ParkingLot;

public class ParkingSpot {
	private int size;
	private Vehicle vehicle;
	private Level level;
	private int row;
	public ParkingSpot(int size, Level level, int row) {
		this.size = size;
		this.vehicle = null;
		this.level = level;
		this.row = row;
	}
	
	public boolean isEmpty() {
		return this.vehicle == null;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public void setVehicle(Vehicle v) {
		this.vehicle = v;
	}
	
	public void freeVehicle() {
		this.vehicle = null;
	}
}
