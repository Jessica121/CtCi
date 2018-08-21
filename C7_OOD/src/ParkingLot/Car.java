package ParkingLot;

public class Car extends Vehicle {
	public Car(String ln) {
		super(ln);
		this.type = VehicleType.CAR;
		this.size = type.getSize();
	}
	
	public int getSize() {
		return this.size;
	}
}
