package stackAndQueue;

public class SortStack {
	public static void sortS(myStack<Integer> s){
		myStack<Integer> s2 = new myStack<Integer>();
		while(!s.isEmpty()){
			int Ori = s.pop();
			while(!s2.isEmpty() && s2.peek() > Ori){		//execute nothing outside 
//				if(s2.peek() > Ori){
					s.push(s2.pop());
//				}
			}
			s2.push(Ori);
		}
		while(! s2.isEmpty()){
			s.push(s2.pop());
		}
	}
	
	public static void main (String[] args){
		myStack<Integer> s = new myStack<Integer>();
		s.push(6);
		s.push(5);
		s.push(4);
		s.push(3);
		s.push(2);
		s.push(4);
		sortS(s);
		for(int i = 0; i< 6 ; i++){
		System.out.println(s.pop());
		}
	}
}
