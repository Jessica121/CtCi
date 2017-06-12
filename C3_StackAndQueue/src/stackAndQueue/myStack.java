package stackAndQueue;
import java.util.Stack;
import java.util.EmptyStackException;

public class myStack<T>{		//head op 
	private static class StackNode<T>{
		private T data;
		private StackNode<T> next;
		
		public StackNode(T data){//constructor should be public
			this.data = data;
		}
	}
	
	private StackNode<T> top;
	// static ???
	public T peek(){
		if(top == null)
			throw new EmptyStackException();
		return top.data;
	}
	
	public T pop(){
		if(top == null)
			throw new EmptyStackException();
		T removedData = top.data;
		top = top.next;
		return removedData;
		
	}
	
	public void push(T data){
		StackNode<T> push = new StackNode<T>(data);
		if(top != null){
			push.next = top;
		}
		top = push;
	}
	
	public boolean isEmpty(){
		return top == null;
	}
	
	public static void main(String args[]){
		Stack<Integer> ans = new Stack<Integer>();
		myStack<Integer> cek = new myStack<Integer>();
		int[] arr = {1,2,3,4,5,6};
		for (int a : arr){
			ans.push(a);
			cek.push(a);
		}
		while(!ans.isEmpty() && !cek.isEmpty()){
			int a = ans.pop();
			int c = cek.pop();
			System.out.println(a+"=?"+c);
			if (a != c){
				System.out.println("not correct!");
			}
		}
		System.out.println("Done!");
	}
	
	
}
