
public class PairwiseSwap {
	/*
	 * swap the 0 with 1 and 2 with 3, etc, with as less operations possible.
	 * 1011100101 -> 0111011010
	 * 1 ^ 1 = 0, 0 ^ 1 = 1 -> flip bits.
	 * 0 ^ 0 = 0, 1 ^ 0 = 1 -> remain bits.
	 * i = 0, i += 2, check (>> i) & 1) ^ (>>(i + 1) & 1) == 1, flip with 11 << i.
	 * */
	
	public static int pairwiseFlip(int n) {
		int mask = (1 << 2) - 1;
		for(int i = 0; i < Integer.SIZE; i += 2) {
			if((((n >> i) & 1) ^ ((n >> (i + 1)) & 1)) == 1) {
				n ^= (mask << i);
			}
		}
		return n;
	}
	
	/*
	 * Mask 10101010 0xaaaaaaaa to get the even bits, then 01010101.. 0x55555555 to get the odd.
	 * even >>> 1 | odd << 1
	 * */
	public static int pairwiseShiftEvenOddSeparatelt(int n) {
		// 				original -> 1011100101
		// even 1010100000 >>> 1 -> 0101010000
		// odd  0001000101  << 1 -> 0010001010
		// 					res  -> 0111011010
		int even = n & 0xaaaaaaaa, odd = n & 0x55555555;
		return (even >>> 1) | (odd << 1);
	}

	public static void main(String[] args) {
		for(int n = 0; n < 100; n++) {
			System.out.println(pairwiseFlip(n) == pairwiseShiftEvenOddSeparatelt(n));
		}
	}

}
