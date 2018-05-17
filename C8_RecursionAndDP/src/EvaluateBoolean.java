import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EvaluateBoolean {

	public static void main(String[] args) {
		String str = "1^0|0|1"; //0&0&0&1^1|0 true -> 10
		int[][] memo = new int[str.length()][2];
		int res = evalu(str, true, memo, 0);
		for(int[] m : memo) System.out.println(Arrays.toString(m));
		System.out.println(numOfWays(str, new HashMap<>(), false));
	}
	
	// =======================================: BUGGY WAY :======================================
	// evaluate 1 ^ 0 | 0 | 1 , true / false surround them with parenthese and cnt the num of ways to do that.
	private static int evalu(String str, boolean bool, int[][] memo, int index) {
		if(index >= str.length()) return 0;
		System.out.println("	index-> " + index);
		if(memo[index][bool ? 1 : 0] != 0) return memo[index][bool ? 1 : 0];
		int res = 0;
		for(int i = index; i < str.length(); i += 2) { // index always start with digits.
			int j = i + 1, numOfWays = 0;
			boolean left = eval(str, index, j);
			if(j == str.length()) {
				numOfWays = left == bool ? 1 : 0;
			} else {
				switch(str.charAt(j)) {
					case '^' :
						numOfWays = evalu(str, bool ^ left, memo, j + 1);
						break;
					case '&' :
						if(!bool) {
							numOfWays = evalu(str, false, memo, j + 1);
							if(!left) numOfWays += evalu(str, true, memo, j + 1);
						} else if(left) numOfWays = evalu(str, true, memo, j + 1); /*
						 the problem lies here : if left == false and bool is true. does not recurse. 
						 should use symmetric in stead. left ^,|,& right evaluate
						 */
						break;
					case '|' :
						if(bool == true) {
							numOfWays = evalu(str, !(bool & left), memo, j + 1);
//							System.out.println(j + 1 + "," + numOfWays);
						}
						else {
							numOfWays = evalu(str, false, memo, j + 1);
							if(left == false) numOfWays += evalu(str, true, memo, j + 1);
						}
						break;
				}
			}
			res += numOfWays;
		}
		return memo[index][bool? 1 : 0] = res;
	}
	
	// ======================================: REUSE HELPER :======================================

	private static boolean eval(String s, int start, int end) {
		int i = start;
		Boolean res = null;
		Character sign = null; // 1 | 0 ^ 1 & 0
		System.out.println(s.substring(start, end));
		while(i < end) {
			if(Character.isDigit(s.charAt(i))) {
				boolean eva = s.charAt(i) == '0' ? false : true;
				if(res == null) res = eva;
				else switch(sign) {
					case '^' : res = res ^ eva;
						break;
					case '&' : res = res & eva;
						break;
					case '|' : res = res | eva;
						break;
				}
			} else sign = s.charAt(i);
			i++;
		}
		System.out.println(" -> " + res);
		return res;
	}
	
	// ======================================: SECOND TRY :======================================
	// 1 ^ 0 | 0 | 1
	// o(n!)
	private static int numOfWays(String s, Map<String, Integer> map, boolean bool) { // key := substring + true / false
		if(s.length() == 0) return 0;
		String key = new String(s + bool);
		if(s.length() == 1 || map.containsKey(key)) {
			if(s.length() == 1) {
				if(s.equals("1") && bool || (s.equals("0") && !bool)) map.put(key, 1);
				else map.put(key, 0);
			}
			return map.get(key);
		}
		int res = 0;
		for(int i = 1; i < s.length(); i += 2) {
			int num = 0;
			String left = s.substring(0, i), right = s.substring(i + 1);
			int leftT = numOfWays(left, map, true), leftF = numOfWays(left, map, false);
			int rightT = numOfWays(right, map, true), rightF = numOfWays(right, map, false);
			switch(s.charAt(i)) {
				case '^' : {
					if(bool) num = leftT * rightF + leftF * rightT;
					else num = leftT * rightT + leftF * rightF;
					break;
				}
				case '&' : {
					if(bool) num = leftT * rightT;
					else num = leftT * rightF + leftF * rightT + leftF * rightF;
					break;
				}
				case '|' : {
					if(!bool) num = leftF * rightF;
					else num = leftF * rightT + leftT * rightF + leftT * rightT;
					break;
				}
				default: break;
			}
			res += num;
		}
		map.put(key, res);
		return res;
	}
}
