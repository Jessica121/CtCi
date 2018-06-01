
public class NextNumber {
	
	/*
	 * given a number, return the nearest prev and next one that has the same number of 1 bits in the rep
	 * 
	 * 11011001111100 -> nearest next: 11011010001111
	 * 00100110000011 -> nearest prev: 00100101110000
	 * 
	 * nearest next: first non-trailing 0, into 1, rest c0 + 1 0s and c1 - 1 1s. (00001111)
	 * nearest prev: first non-trailing 1, into 0. the rest, fill c1 + 1 ones and c0 - 1 0s.
	 * 
	 * */
	
	public static int nearestNext(int n) {
		int c1 = 0, c0 = 0;
		int i = 0;
		while(i < 32 && ((n >> i) & 1) == 0) {
			c0++;
			i++;
		}
		while(i < 32 && ((n >> i) & 1) == 1) {
			c1++;
			i++;
		}
		// 11111000000, 000000000, 1111111111 already the largest possible
		if(c1 + c0 == 32) return n; 
		// put 0 into 1. when & 0 and 1, result will be 0, the 1 wont be set.
		int mask = 1 << (c1 + c0);
		// set bits use OR
		n |= mask;
		// generating bits looks like 1111100000
		mask = ~((1 << (c1 + c0)) - 1);
		// clear bits using AND
		n &= mask;
		// generate bits looks like 00000111111
		mask = (1 << (c1 - 1)) - 1;
		// set bits use OR.
		n |= mask;
		return n;
	}  
	
	public static int nearestPrev(int n) {
		int c1 = 0, c0 = 0;
		int i = 0;
		while(i < 32 && ((n >> i) & 1) == 1) {
			c1++;
			i++;
		}
		while(i < 32 && ((n >> i) & 1) == 0) {
			c0++;
			i++;
		}
		// smallest possible.
		if(c0 + c1 == 32) return n;
		// - has higher association, so use () for bit manipulations.
		int mask = ~((1 << (c0 + c1 + 1)) - 1);
		n &= mask;
		mask = ((1 << (c0 + c1)) - 1) - ((1 << (c0 - 1)) - 1);
		n |= mask;
		return n;
	}

	public static void main(String[] args) {
		System.out.println("next nearest: " + nearestNext(13948));
		System.out.println("next prev: " + nearestPrev(2435));
	}

}
