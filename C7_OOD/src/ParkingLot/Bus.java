package ParkingLot;

public class Bus extends Vehicle {
	public Bus(String ln) {
		super(ln);
		this.type = VehicleType.BUS;
		this.size = type.getSize();
	}
	
	public int getSize() {
		return this.size;
	}
}
