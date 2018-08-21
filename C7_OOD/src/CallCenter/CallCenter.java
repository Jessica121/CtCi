package CallCenter;
import java.util.*;
/*
 * object: collection of employees, three level could be put into list.
 * 			number of different levels of employees.
 * 			does it have calls?
 * 			a three level queue for storing the unhandled calls.
 * action: assign calls: get the first available employee for the call, meaning, the rank must match between the call and the employees
 * by checking one by one in the employee list until one is empty. O(N)
 * but what if the rank of the call is full? what if other condition make the handler cannot handle the call even if the call
 * matches?
 * if it has a queue for storing the calls, when they are dequeued??
 * 
 * */
public class CallCenter {
	// index 0 1 2 rep the rank of the employees
	private List<List<Employee>> employees;
	private List<Queue<Call>> waitLists;
	private final int NUM_OF_RESPONDER = 1000, NUM_OF_MANAGER = 50, NUM_OF_DIRECTOR = 10;
	public CallCenter() {
		this.waitLists = new ArrayList<>();
		initEmployees(NUM_OF_DIRECTOR, NUM_OF_MANAGER, NUM_OF_DIRECTOR);
	}
	
	private void initEmployees(int res, int manag, int direc) {
		initEmployees(NUM_OF_RESPONDER, 0);
		initEmployees(NUM_OF_MANAGER, 1);
		initEmployees(NUM_OF_DIRECTOR, 2);
	}
	
	private void initEmployees(int num, int rank) {
		for(int i = 0; i < num; i++) {
			Employee e = rank == 0 ? new Respondant(this) : rank == 1 ? new Manager(this) : new Director(this);
			employees.get(rank).add(e);
		}
	}
	
	// assign call when call comes in.
	public boolean assignCall(Call call) {
		Employee handler = findEmployeeForCall(call);
		if(handler == null) {
			System.out.println("Sorry, line is busy, please wait");
			waitLists.get(call.getRank()).add(call);
			return false;
		} else {
			handler.takeCall(call);
			return true;
		}
	}
	
	// assign call when an employee is free, checking from the queue.
	// check from employee's rank, check from its rank till the lowest rank.
	public boolean assignCall(Employee emp) {
		for(int i = emp.getRank(); i >= 0; i--) {
			Queue<Call> waitList = waitLists.get(i);
			if(!waitList.isEmpty()) {
				Call call = waitList.poll();
				emp.takeCall(call);
				return true;
			}
		}
		return false;
	}
	
	private Employee findEmployeeForCall(Call call) {
		// can add things like start from the rank of current call, retrieve that rank of employees and go up.
		for(int i = call.getRank(); i < 3; i++) {
			List<Employee> candi = employees.get(i);
			for(Employee e : candi) {
				if(e.isFree()) return e;
			}
		}
		return null;
	}
}
