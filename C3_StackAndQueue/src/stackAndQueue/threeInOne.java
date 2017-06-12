package stackAndQueue;
import java.util.EmptyStackException;

public class threeInOne {
//3.1
		// ___ ___ ___
		private int stackNum = 3;
		private int stackCapacity;
		private int[] sizeFES;		//size for each stack;
		private int[] values;
		public threeInOne(int stackSize){
			stackCapacity = stackSize;
			values = new int[stackCapacity * stackNum];
			sizeFES = new int[stackNum];	
		}
		
		public int top(int stackNo){
			return (stackNo*stackCapacity + sizeFES[stackNo] -1);
		}
		
		public boolean isEmpty(int stackNo){
			return sizeFES[stackNo] == 0;
		}
		
		public boolean isFull(int stackNo){
			return sizeFES[stackNo] == stackCapacity;
		}
		
		public void push(int stackNo, int x) throws Exception{
		
			if(isFull(stackNo))
				throw new Exception();		///should be fullStackException
			values[top(stackNo)+1] = x;
			sizeFES[stackNo]++;
		}

		public int pop(int stackNo) throws EmptyStackException{
			if(isEmpty(stackNo)) throw new EmptyStackException();
			int top = values[top(stackNo)];
			values[top(stackNo)] = 0;
			sizeFES[stackNo]--;
			return top;
		}
		

		public int peek(int stackNo) throws EmptyStackException{
			if(isEmpty(stackNo)) throw new EmptyStackException();
			int top = values[top(stackNo)];
			return top;
		}	
		

	public static void main(String [] args) throws Exception {
		threeInOne stack = new threeInOne(4);
		stack.push(0,1);
		stack.push(0,2);
		stack.push(0,3);
		stack.push(1,4);
		stack.push(1,5);
		stack.push(1,6);
		stack.push(2,7);
		stack.push(2,8);
		stack.push(2,9);
		stack.push(2,10);
//		for(int i=0;i<12;i++)
//		{System.out.println(stack.values[i]);}
		stack.pop(0);
		stack.pop(0);
		stack.pop(0);
		stack.pop(1);
		stack.pop(1);
		stack.pop(1);
		stack.pop(2);
		stack.pop(2);
		stack.pop(2);
		stack.pop(2);
		for(int i=0;i<12;i++)
		{System.out.println(stack.values[i]);}
	}
	
	
	
}
