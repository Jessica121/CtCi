package ParkingLot;
import java.util.*;
public abstract class Vehicle {
	protected VehicleType type;
	protected String licenseNumber;
	protected int size;
	protected List<ParkingSpot> spots;
	public Vehicle(String ln) {
		this.licenseNumber = ln;
		spots = new ArrayList<>();
	}
	
	public void park(ParkingSpot lot) {
		if(canFit(lot)) {
			spots.add(lot);
			lot.setVehicle(this);
		}
	}
	
	public abstract int getSize();
	
	// but i thought parking spot is of unit 1.
	public boolean canFit(ParkingSpot spot) {
		return spot.getSize() >= this.getSize();
	}
	
	public void freeSpots() {
		for(ParkingSpot spot : spots) {
			spot.freeVehicle();
		}
		this.spots = new ArrayList<>();
	}
}
