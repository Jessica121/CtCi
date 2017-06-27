package stackAndQueue;
import java.util.EmptyStackException;
import java.util.ArrayList;

public class ArrayOfStack {
ArrayList<Stack> stacks = new ArrayList<Stack>();
public int capacity;
public ArrayOfStack(int c){
	this.capacity = c;
}

public Stack getLast(){
	if(stacks.size() == 0)
		{return null;
		}else{
	return stacks.get(stacks.size() - 1);}
}

public void push(int v){
	Stack last = getLast();
//	if( last.isFull() || last == null){
//		Stack n = new Stack(capacity);
//		n.push(v);
//		stacks.add(n);
//	}else{
//		last.push(v);
//	}										//why not omg???
	if (last != null && !last.isFull()) {		
		last.push(v);
	} else {  
		Stack stack = new Stack(capacity);
		stack.push(v);
		stacks.add(stack);
	}
}

public int pop(){
	if (getLast() == null || getLast().isEmpty()) throw new EmptyStackException();
	int i =  getLast().pop();
	if(getLast().isEmpty()){
		stacks.remove(getLast());
	}
	return i;
}

public int popAt(int i){
	return leftShift(i,true);
}

public int leftShift(int i, boolean removeTop){
	Stack popS = stacks.get(i);
	int res;
	if(removeTop){
		res = popS.pop();
	}else{
	 res = popS.getBottom();
	 System.out.println("leftShift's bottom?="+res);
	}
	if(popS.isEmpty()){//狀態改變,查詢
		stacks.remove(i);
	}else if(i < stacks.size()-1){
		int b = leftShift(i+1,false);
		popS.push(b);
	}
	return res;
}

public boolean isEmpty(){
	return getLast() == null || getLast().size == 0 ;
}

public static void main(String[] args) {
	ArrayOfStack s = new ArrayOfStack(2);
		s.push(1);s.push(2);
		s.push(3);s.push(4);
		s.push(5);s.push(6);
		
		// 1 3
		//4 5
		//6
		
		//1 4 
		//5 6
		
	s.popAt(0);
	s.popAt(0);
	s.popAt(0);
	s.popAt(0);
	s.popAt(0);
	s.popAt(0);
	//int i = s.popAt(1);
	
	System.out.println(s.popAt(0));
	
}

}

class Stack{
	public Node top , bottom;
	public int size = 0;
	public int capacity;
	public boolean isFull(){
		if(this.capacity == this.size){
			System.out.println("Stack full baby");
		}
		return this.capacity == this.size;
	}
	public boolean isEmpty(){
		return this.size == 0;
	}
	public Stack(int c){
		this.capacity = c;
	}
	public void join(Node up, Node down){
		if(up != null){
			up.below = down;
		}
		if(down != null){
			down.above = up;
		}
	}
	public boolean push(int a){
		if(this.isFull()){
			return false;}
		Node n = new Node(a);
		if(size == 0){
			bottom = n;
			System.out.println("bottom is"+bottom.value);
		}
		size++;
		join(n,top);
		top = n;
		return true;
	}
	
	public int pop(){
		if (this.isEmpty()) throw new EmptyStackException();
		Node t = top;
		top = top.below;
		size--;
		return t.value;
	}
	public int getBottom(){
		int b = bottom.value;
		bottom = bottom.above;
		if (bottom != null)
			bottom.below = null;
		size--;		///-->狀態改變！想！
					//shift的時候size沒有減少，空了不會被刪除，別人access它，null
		return b;
	}

}

class Node{
	public int value;
	public Node below, above;
	public Node(int a){
		this.value = a;
	}
}