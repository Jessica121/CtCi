import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class divingBoard {
	private static int cnt = 0;
	// K planks of short and long boards, put together to generate all length
	public static void main(String[] args) {
		System.out.println(allPossLen(12, 1, 3));
		System.out.println(cnt);
	}
	
	private static List<Integer> allPossLen(int K, int s, int l) {
//		List<Integer> res = new ArrayList<>();
//		generateDFS(K, s, l, 0, res, new HashSet<>());
//		return generateLiner(K, s, l);
		Set<String> exist = new HashSet<>();
		Set<Integer> res = new HashSet<>();
		generateMemo(K, K, s, l, 0, 0, 0, res, exist);
		return new ArrayList<>(res);
	}
	
	/* Liner solution*/
	private static List<Integer> generateLiner(int K, int s, int l) {
		List<Integer> res = new ArrayList<>();
		int sum = s * K;
		for(int i = 0; i <= K; i++) {
			res.add(sum);
			// when short and long have the same length, we should return 1 res.
			if(s == l) break;
			sum = sum - s + l;
		}
		return res;
	}
	
	/* Recursion solution BF*/
	private static void generateDFS(int resid, int s, int l, int sum, List<Integer> res, Set<Integer> set) {
		if(resid == 0) {
			if(!set.contains(sum)) {
				res.add(sum);
				set.add(sum);
			}
			return;
		}
		// O(2^k)
		generateDFS(resid - 1, s, l, sum + s, res, set);
		generateDFS(resid - 1, s, l, sum + l, res, set);
	}
	
	/* Liner solution*/
	private static void generateMemo(int K, int resid, int s, int l, int cnL, int cnR, int sum, Set<Integer> set, Set<String> existStr) {
		cnt++;
		if(resid == 0) {
			if(!set.contains(sum)) {
				set.add(sum);
			}
			return;
		}
		if(existStr.contains(String.valueOf((cnL + " " + cnR)))) return;
		existStr.add(String.valueOf((cnL + " " + cnR)));
		generateMemo(K, resid - 1, s, l, cnL + 1, cnR, sum + s, set, existStr);
		generateMemo(K, resid - 1, s, l, cnL, cnR + 1, sum + l, set, existStr);
		/*NOT REMOVING THIS, if removed same as no memo*/
//		existStr.remove(String.valueOf((cnL + " " + cnR))); 
	}

}
