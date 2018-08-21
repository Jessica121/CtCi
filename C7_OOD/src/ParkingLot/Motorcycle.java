package ParkingLot;

public class Motorcycle extends Vehicle {
	public Motorcycle(String ln) {
		super(ln);
		this.type = VehicleType.MOTOR;
		this.size = type.getSize();
	}
	
	public int getSize() {
		return this.size;
	}
}
