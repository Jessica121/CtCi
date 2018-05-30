import java.util.BitSet;

public class FindDuplicates {

	/* An array of all nums from 1 to N, n <= 32,000
	 * array may have duplicates, print all duplicates within 4KB of memory
	 * 
	 * 4KB = 4 * 1024 > 4,000 Bytes = 32,000 bits -> maps to 32,000 integers.
	 * use an byte[] of size 4000, map the integer to it, shift the bits of mod 8, if already 1 then print.
	 */
	
	// byte array version
	private static void findDuplicates(int[] arr) {
		byte[] byteArr = new byte[4000];
		for(int num : arr) {
			num -= 1;
			if((byteArr[num / 8] >> (num % 8) & 1) == 1) System.out.println(num + 1);
			else byteArr[num / 8] |= 1 << (num % 8) ;
		}
	}
	
	// BitSet
	
	private static void findDupWithBitSet(int[] arr) {
		BitSet bs = new BitSet(14);
		for(int num : arr) {
			// bitset indexing from 0.
			if(bs.get(num - 1)) System.out.println(num);
			else bs.set(num - 1);
		}
	}
	
	static class BitSet {
		int[] bitset;
		public BitSet(int size) {
			// size is num of bit.
			// since it's an integer array, it has 32 bits.
			bitset = new int[(size >> 5) + 1]; // for the size less than 32, and don't forget the parentheses.
		}
		
		public int size() {
			return bitset.length;
		}
		
		public boolean get(int index) {
			return (bitset[index >> 5] >> (index % 32) & 1) == 1;  // and by one to check if it exist
		}
		
		public void set(int index) {
			if(get(index)) return;
			bitset[index >> 5] |= 1 << (index % 32);  // left shift by one
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,4,5,6,7,7,7,7,8,9,10};
		findDupWithBitSet(arr);
	}

}
