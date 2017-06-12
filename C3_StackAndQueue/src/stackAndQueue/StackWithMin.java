package stackAndQueue;

public class StackWithMin extends myStack<NodeWithMin>{
	public void push(int data){	//overriding super classd myStack's push func cuz they have the same name;
		int min = Math.min(data,this.min());
		NodeWithMin add = new NodeWithMin(data,min);
		super.push(add);
	}
	
	public int min(){
		if(this.isEmpty()){		//this-- superclass myStack's instance
			return Integer.MAX_VALUE;
		}
		return peek().min;
	}
	
	public static void main(String[] args){
		StackWithMin stackWM = new StackWithMin();
		stackWM.push(10);
		stackWM.push(20);
		stackWM.push(3);
		stackWM.push(20);

		System.out.println(stackWM.min());
		System.out.println(stackWM.peek().value+" "+stackWM.peek().min);
		System.out.println(stackWM.isEmpty());
	}

}

class NodeWithMin{
	public int value;
	public int min;
	public NodeWithMin(int v, int m){
		this.value = v;
		this.min = m;
	}
}