import java.util.*;

public class Shuffle {
	/*
	 * shuffle a deck of card such that 52! kind happens equally.
	 * 
	 * Native: use a set to track the card already generated. 
	 * Then use an arraylist to add to. stop when the size reaches 52.
	 * 
	 * */
	
	public static List<Integer> shuffleNaive() {
		Set<Integer> set = new HashSet<>();
		Random r = new Random();
		List<Integer> list = new ArrayList<>();
		while(list.size() < 52) {
			int next = r.nextInt(52) + 1;
			if(!set.contains(next)) {
				set.add(next);
				list.add(next);
			}
		}
		return list;
	}
	
	/*
	 * have a sorted first. go from the indexes.
	 * at nth index, generate a random within 0 ~ (n - 1), swap the nth index with the num in that index.
	 * at 1, put 
	 * {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	 * possibility of element j end up being at i: j < i: 
	 * selectP : 1 / j * ( j / j + 1) being not ended up with the next index. * ... = 1 / (1 + n) when element can swap with itself.
	 * */
	
	public static int[] swapShuffle(int[] arr) {
		shuffle(arr, arr.length - 1);
		return arr;
	}
	
	private static void shuffle(int[] arr, int i) {
		if(i == 0) return;
		shuffle(arr, i - 1);
		Random r = new Random();
		int next = r.nextInt(i + 1);
		swap(arr, next, i);
	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	// interative
	public static void shuffle(int[] arr) {
		Random r = new Random();
		for(int i = 0; i < arr.length; i++) {
			int next = r.nextInt(i + 1);
			swap(arr, next, i);
		}
	}

	public static void main(String[] args) {
//		List<Integer> res = shuffleNaive();
//		System.out.println(res);
		int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] buckts = new int[10];
		for(int i = 0; i < 100; i++) {
			shuffle(arr);
			for(int n : arr) {
				buckts[n]++;
			}
			arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		}
		System.out.println(Arrays.toString(buckts));
	}

}
