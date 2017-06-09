package stackAndQueue;

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
	
	public static boolean isEmpty(){
		return top == null;
	}
}
