import java.util.*;
public class sumSwap {
	/*
	 * swap a ele in each arr to make them equal
	 * [4,1,2,1,1,2] [3,6,3,3]
	 * */
	public static void main(String[] args) {
		int[] arr1 = {4,1,2,1,1,2,2};
		int[] arr2 = {3,6,3,3};
		System.out.println(Arrays.toString(sumSwapSort(arr1, arr2)));
	}

	private static int[] sumSwap(int[] arr1, int[] arr2) {
		Set<Integer> set = putInSet(arr1, new HashSet<>());
		int sum1 = sumOf(arr1);
		int sum2 = sumOf(arr2);
		if(sum1 == sum2) return null;
		Integer diff = getDiff(sum1, sum2);
		if(diff == null) return null;
		if(sum1 > sum2) {
			for(int a : arr2) {
				if(set.contains(a + diff)) return new int[] {a, a + diff};
			}
		} else {
			for(int a : arr2) {
				if(set.contains(a - diff)) return new int[] {a - diff, a};
			}
		}
		return null;
	}
	
	private static int sumOf(int[] arr) {
		int sum = 0;
		for(int n : arr) sum += n;
		return sum;
	}
	
	private static Set<Integer> putInSet(int[] arr, Set<Integer> set) {
		for(int n : arr) set.add(n);
		return set;
	}
	
	private static Integer getDiff(int sum1, int sum2) {
		if(sum1 == sum2) return null;
		int diffSum = Math.abs(sum1 - sum2);
		if(diffSum % 2 != 0) return null;
		int diff = diffSum / 2;
		return diff;
	}
	
	private static int[] sumSwapSort(int[] arr1, int[] arr2) {
		int sum1 = sumOf(arr1);
		int sum2 = sumOf(arr2);
		Arrays.sort(arr1); // 1,1,1,2,2,4
		Arrays.sort(arr2); // 3,3,3,6
		Integer diff = getDiff(sum1, sum2);
		if(diff == null) return null;
		int p1 = 0, p2 = 0;
		int toFind = sum1 > sum2 ? diff : -diff;
//		System.out.println(toFind);
		while(p1 < arr1.length && p2 < arr2.length) {
			if(arr1[p1] - arr2[p2] == toFind) return new int[] {arr1[p1], arr2[p2]};
			else if(arr1[p1] - arr2[p2] > toFind) p2++;
			else p1++;
		}
		return null;
	}
}
