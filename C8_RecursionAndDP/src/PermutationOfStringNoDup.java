import java.util.*;
public class PermutationOfStringNoDup {

	public static void main(String[] args) {
		String test = "abc"
				+ ""
				+ "'";
		System.out.println(permuGeneratePermuExceptSelf(test));
	}
	
	private static List<String> permu(String s, int index) {
		List<String> res = new ArrayList<>();
		if(index >= s.length() - 1) {
			if(index == s.length() - 1) res.add("" + s.charAt(index)); // deal with the base case, the result of the last one
			// will not be added in the for loop.
			// deal with when index exceeeeeeed the length.
			return res;
		}
		// O(N!) generate what is needed.
		List<String> prevPer = permu(s, index + 1);
		for(String candi : prevPer) {
			for(int i = 0; i <= candi.length(); i++) { // is the candidate not the string
				String concat = concating(candi, i, s, index);
				res.add(concat);
			} 
		}
		return res;
	}
	
	private static String concating(String candi, int i, String s, int index) {
		String left = candi.substring(0, i), right = candi.substring(i);
		String res = left + s.charAt(index) + right;
		return res;
	}
	
	public static List<String> permuGeneratePermuExceptSelf(String s) {
		List<String> res = new ArrayList<>();
		if(s.isEmpty()) {
			res.add("");
			return res;
		}
		
		for(int i = 0; i < s.length(); i++) {
			String left = s.substring(0, i), right = s.substring(i + 1);
			List<String> rest = permuGeneratePermuExceptSelf(left + right);
			for(String r : rest) {
				res.add(s.charAt(i) + r);
			}
		}
		return res;
	}

}
