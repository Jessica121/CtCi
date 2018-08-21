package ParkingLot;

public enum VehicleType {
	CAR(1), MOTOR(1), BUS(3);
	private int size;
	private VehicleType(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
}
