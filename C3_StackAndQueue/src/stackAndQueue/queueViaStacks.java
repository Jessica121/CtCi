package stackAndQueue;
import java.util.LinkedList;//queue using linkedlist???
import java.util.Queue;//testing correctness

public class queueViaStacks<T> {
	myStack<T> oldS;
	myStack<T> newS;
	
	public queueViaStacks(){
		oldS = new myStack<T>();
		newS = new myStack<T>();
	}
	
	public int size(){
		return oldS.size + newS.size;
	}
	
	public void add(T a){
		newS.push(a);
	}
	
	public T peek(){
		shift();
		return oldS.peek();
	}
	
	public T remove(){
		shift();
		return oldS.pop();
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public void shift(){
		while(oldS.isEmpty()){
			while(!newS.isEmpty()){
				oldS.push(newS.pop());
			}
		}
	}
	
	public static void main(String [] args){
		Queue<Integer> testQ = new LinkedList<Integer>();
		queueViaStacks<Integer> myQ = new queueViaStacks<Integer>();
		int[] arr = {1,2,3,4,5,6,7,8,9};
		for(int a: arr){
			testQ.add(a);
			myQ.add(a);
			//System.out.println("ans is: "+ testQ.size() + " my is: " + myQ.size());
		}
		testQ.remove();testQ.remove();myQ.remove();myQ.remove();
		int ansT = testQ.remove();
		int myT = myQ.remove();
		System.out.println("ans is: "+ ansT + " my is: " + myT);
	}
	
}
