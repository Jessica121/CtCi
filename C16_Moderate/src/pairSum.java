import java.util.*;

public class pairSum {
	public static void main(String[] args) {
		int[] arr = {14,13,9,7,6,5,4,3,0,-1,-2};
		System.out.println(findPairsWithSum(arr, 11));
	}
	
	// HashMap
	private static List<String> findPairsWithSum(int[] arr, int tar) {
		List<String> res = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int n : arr) map.put(n, map.getOrDefault(n, 0) + 1);
		for(int i = 0; i < arr.length; i++) {
			// Decrease itself first, so if they are the same thing, it would not think itself as the other pair.
			map.put(arr[i], map.get(arr[i]) - 1);
			if(map.containsKey(tar - arr[i]) && map.get(tar - arr[i]) >= 1) {
				map.put((tar - arr[i]), map.get(tar - arr[i]) - 1);
				res.add("(" + String.valueOf(arr[i]) + ", " + String.valueOf(tar - arr[i]) + ")");
			}
		}
		return res;
	}
	
	// Sort with two pointers.
	private static List<String> findPairsWithSumSorted(int[] arr, int tar) {
		Arrays.sort(arr);
		List<String> res = new ArrayList<>();
		int s = 0, e = arr.length - 1;
		while(s < e) {
			if(arr[s] + arr[e] == tar) {
				res.add("(" + String.valueOf(arr[s++]) + ", " + String.valueOf(tar - arr[e--]) + ")");
			} else if(arr[s] + arr[e] > tar) e--;
			else s++;
		}
		return res;
	}
	
	
}
