package stackAndQueue;
import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.LinkedList;

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
	
	public static void main(String args[]){
		Queue<Integer> ans = new LinkedList<Integer>();		//
		myQueue<Integer> cek = new myQueue<Integer>();
		int[] arr = {1,2,3,4,5,6};
		for(int a: arr){
			ans.add(a);
			cek.add(a);
		}
		
		while(!ans.isEmpty() && !cek.isEmpty()){
			int a = ans.remove();
			int c = cek.remove();
			System.out.println(a+"=?"+c);
			if(a != c){
				System.out.println("err at implementation");
			}
		}
		System.out.println("Done!");
		
	}
	
	
	
}
