package stackAndQueue;

//public class StackWithMin extends myStack<NodeWithMin>{
//	public void push(int data){	//overriding super class myStack's push func cuz they have the same name;
//		int min = Math.min(data,this.min());
//		NodeWithMin add = new NodeWithMin(data,min);
//		super.push(add);
//	}
//	
//	public int min(){
//		if(this.isEmpty()){		//this-- superclass myStack's instance
//			return Integer.MAX_VALUE;
//		}
//		return peek().min;
//	}
//
//	public static void main(String[] args){
//		StackWithMin stackWM = new StackWithMin();
//		stackWM.push(10);
//		stackWM.push(20);
//		stackWM.push(3);
//		stackWM.push(20);
//
//		System.out.println(stackWM.min());
//		System.out.println(stackWM.peek().value+" "+stackWM.peek().min);
//		System.out.println(stackWM.isEmpty());
//	}
//	
//}

class NodeWithMin{
	public int value;
	public int min;
	public NodeWithMin(int v, int m){
		this.value = v;
		this.min = m;
	}
}

//--------------------------------use another stack to track min, compare with it----------------------------------------
public class StackWithMin extends myStack<Integer>{
	myStack<Integer> minS;//ppt
	public StackWithMin(){
		minS = new myStack<Integer>();	//a stack to push min / pop 
	}
	public void push(int a){
		if (a<=this.min()){
			minS.push(a);
		}
		super.push(a);
	}
	
	public Integer pop(){
		int a = super.pop();
		if(a == this.min()){
			minS.pop();
		}
		return a;
	}
	
	public int min(){
		if(minS.isEmpty()){
			return Integer.MAX_VALUE;
		}else{
			return minS.peek();
		}
	}
	
	public static void main(String[] args){
	StackWithMin a = new StackWithMin();
	a.push(2);
	a.push(2);    
	a.push(1);
	a.pop();
	System.out.println(a.min());
	}
	
}
