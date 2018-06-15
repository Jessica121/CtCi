
public class SecondPass_PermutationPalidrome {

	/*
	 * Check if a string is a permutation of a palindrome
	 * 
	 * String characteristic definition problem
	 * 
	 * blank space, cap;
	 * 
	 * assume that cap is ignored and blank space doesnt count either
	 * 
	 * taco cat 
	 * 
	 * count the number of characters, can only have at most 1 odd num for a char.
	 * boolean hasOdd.
	 * when cnt == 1 and !hasOdd, set hasOdd else return false
	 * but this can only check at the end.
	 * 
	 * if using bit manipulation, flip bits, XOR with 1 << numeric rep, and count the number of 1s have, still have to go thru again.
	 * one way that does not need to check again is noticing that 000010000 or 0000000 when it AND with itself - 1, it will get an zero.
	 * 
	 * 0000110000 & 0000101111 != 0
	 * 
	 * if have to do it one pass, cnt the even and odd as we go and return cnt;
	 * as a cnt = 1, aa cnt = 0
	 * have an array to track the num, for each char, increase the char's cnt. update the cnt for odd.
	 * mind that intermediate results can be incomplete, not DP.
	 * 
	 * */
	
	public static boolean permutationPalidrome(String s) {
		int[] array = new int[128]; // ASCII
		int cnt = 0;
		for(int i = 0; i < s.length(); i++) {
			array[s.charAt(i)]++;
			if(array[s.charAt(i)] % 2 == 1) cnt++;
			else cnt--;
		}
		return cnt <= 1;
	}
	
	// bit manipulation requires lower case a to z
	// aba
	public static boolean checkBit(String s) {
		int res = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ' ') continue;
			int mask = 1 << (s.charAt(i) - 'a');
			res ^= mask;
		}
		return (res & (res - 1)) == 0;
	}
	
	public static void main(String[] args) {
		System.out.println(checkBit("taco cat"));

	}

}
