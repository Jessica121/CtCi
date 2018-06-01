
public class Conversion {
	
	/*
	 * count the number of different bits in a and b.
	 * 
	 * count the number of 1s in a XOR b
	 * or
	 * count the times it takes to flip a least significant 1 bit into 0 in a XOR b.
	 * 
	 * */
	
	public static int flip(int a, int b) {
		int c = a ^ b, cnt = 0;
		for(int i = 0; i < 32; i++) {
			// after the shift, & with 1 is the last digit. don't forget.
			if(((c >> i) & 1) == 1) cnt++;
		}
		return cnt;
	}
	
	public static int flipFlip(int a, int b) {
		int c = a ^ b, cnt = 0;
		while(c != 0) {
			c &= c - 1;
			cnt++;
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		int a = 15, b = 29;
		System.out.println(flip(a, b) + "\n" + flipFlip(a, b));
	}

}
