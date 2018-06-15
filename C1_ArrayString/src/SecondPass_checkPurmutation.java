
public class SecondPass_checkPurmutation {
/*
 * given two strings, check if one is a permutation of the other.
 * 
 * act - cat
 * baaa aaba
 * 00011
 * 
 * can also use bit manipulation if char only lower case a to z: use a res to set the bits, using ^ (1 << numerics rep)
 * the other string do the same thing, the res should be 0.
 * 
 * if not, if cap and blank space counts, return false when two strings not the same length, 
 * should use an array to cnt each char num, and the other decrease it.
 * only meet < 0 false, 
 * dont have to went thru again to check if != 0 false, as the len of the strings are same, if one is more, the other will be less.
 * 
 * Recognize string pattern and apply the rule to the pattern.
 * 
 * */
	// bit manipulation version if only lower case a to z.
	public static boolean isPermutation(String s, String p) {
		if(s.length() != p.length()) return false;
		int res = 0;
		for(int i = 0; i < s.length(); i++) {
			res ^= 1 << (s.charAt(i) - 'a');
		}
		for(int i = 0; i < p.length(); i++) {
			res ^= 1 << (p.charAt(i) - 'a');
		}
		return res == 0;
	}
	
	public static void main(String[] args) {
		System.out.println(isPermutation("avc", "ewq"));

	}

}
