import java.util.*;

public class RandomSet {
	/*
	 * Generate a set of size m from an array of size n.
	 * 
	 * */
	
	// Native. use a set.
	
	public static Set<Integer> randomSet(int[] arr, int m) {
		Set<Integer> res = new HashSet<>();
		Random r = new Random();
		while(res.size() < m) {
			int next = r.nextInt(arr.length);
			if(!res.contains(arr[next])) {
				res.add(arr[next]);
			}
		}
		return res;
	}
	
	/* copy the first m element from the array to the set array.
	 * after m, pick arr[index] and insert randomly into, if less than m, set array.
	 * insert randomly: chose an index randomly from 0 to current index. 
	 */
	
	// interative.
	public static int[] randomSet2(int[] arr, int m) {
		// n >= m
		int[] set = new int[m];
		for(int i = 0; i < m; i++) set[i] = arr[i];
		Random r = new Random();
		for(int i = m; i < arr.length; i++) {
			int next = r.nextInt(i + 1);
			if(next < m) set[next] = arr[i];
		}
		return set;
	}
	
	// recursive.
	public static int[] randomSetRecursive(int[] arr, int m) {
		int[] res = new int[m];
		randomSetRecurHelper(arr, m, arr.length - 1, res);
		return res;
	}
	
	private static void randomSetRecurHelper(int[] arr, int m, int i, int[] res) {
		// return the first m elements.
		if(i == -1) return;
		randomSetRecurHelper(arr, m, i - 1, res);
		if(i <= m - 1) {
			res[i] = arr[i];
		} else {
			Random r = new Random();
			int next = r.nextInt(i + 1);
			if(next < m) res[next] = arr[i];
		}
	}

	public static void main(String[] args) {
		int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println(Arrays.toString(randomSetRecursive(arr, 4)));
	}

}
