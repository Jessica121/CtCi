package CallCenter;

/*
 * Object: Rank, Call
 * Action: isFree, takeCall, hangUp, escalate.
 * 
 * how it would escalate is has an instance of call center, so it could access to higher level employees. 
 * Call center assign the current call.
 * 
 * */
public abstract class Employee {
	protected Rank rank;
	protected Call call;
	protected CallCenter callCenter;
	public Employee(CallCenter callCenter) {
		this.call = null;
		this.callCenter = callCenter;
	}
	
	public abstract int getRank();

	public boolean isFree() {
		return call == null;
	}
	
	public void takeCall(Call call) {
		if(isFree()) {
			// assign the call and the employee bidirectionally.
			this.call = call;
			call.assignHandler(this);
		}
		else System.out.println("ERROR, should not be here since the call center should check if is free before calling this.");
	}
	
	// request new call when hang up or escalate.
	public void hangUp() {
		if(isFree()) System.out.println("ERROR, should not be here since call and employee relation are bidirectional.");
		else {
			this.call.hangUp();
			this.call = null;			
		}
		requestNewCall();
	}
	
	// escalate means increase the rank of the call and assign it the call center
	// and since this was unsuccessful, request a new call.
	public void escalate() {
		call.increaseRank();
		callCenter.assignCall(this.call);
		this.call = null;
		requestNewCall();
	}
	
	public boolean requestNewCall() {
		if(!this.isFree()) return false;
		return this.callCenter.assignCall(this);
	}
	
}
