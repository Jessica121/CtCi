package stackAndQueue;

import java.util.NoSuchElementException;

public class myQueue<T> {
	private static class QueueNode<T>{
		private T data;
		private QueueNode<T> next;
		public QueueNode(T data){
			this.data = data;
		}
	}
	
	private QueueNode<T> head;
	private QueueNode<T> end;
	
	public T remove(){
		if(head == null) throw new NoSuchElementException();
	
		T removedData = head.data;
		
		head = head.next;
		if(head == null) end = null;
		return removedData;
	}
	
	public void add(T data){
		QueueNode<T> add = new QueueNode<T>(data);
		if(end != null){
		 end.next = add;
		}
			end = add;	//update end when operating it.
		if(head == null){//head == null ==== end == null??????
			head = end;
		}
	}
	
	public T peek(){
		if (head == null) throw new NoSuchElementException();
		return head.data;
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
}
