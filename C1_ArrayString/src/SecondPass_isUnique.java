
public class SecondPass_isUnique {
	/*
	 * check if a string has all uniq characters.
	 * 
	 * The second pass is served for summarizing purposes.
	 * 
	 * String: black space, ASCII or UNICODE.
	 * 
	 * if int: overflow
	 * 
	 * 
	 * for this problem: if the characters are only lower case a to z, can use bit manipulation:
	 * a:0, z:25
	 * since integer has 32 bits.
	 * so res & (1 << numeric representation), if 0 then set bit with |
	 * else already have one, return false;
	 * can also print all duplicated char.
	 * 
	 * 
	 * */
	
	// abcd 1111   abca  0111
	// O(n)
	public static boolean isUnique(String s) {
		if(s.length() > 26) return false;
		int res = 0;
		for(int i = 0; i < s.length(); i++) {
			int mask = 1 << (s.charAt(i) - 'a');
			if((res & mask) != 0) return false;
			res |= mask;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isUnique("abcd"));

	}

}
