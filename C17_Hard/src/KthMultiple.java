import java.util.*;
public class KthMultiple {
	
	/*
	 * return the kth number which its prime factors are limited to 3 5 7. 
	 * meaning the only factors are 1 3 5 7 (since even number are factors of 2, and 2 is a prime number)
	 * 
	 * 1 3 5 7 9 15 21 25 27(3*3*3) 35
	 * 
	 * use a queue / pq, poll one and multiple by 3 5 7. add back. 
	 * till the kth is found.
	 * 
	 * has duplicates. use a treeset
	 * 
	 */
	
	public static int getKth(int k) {
		TreeSet<Integer> set = new TreeSet<>();
		set.add(1);
		for(int i = 0; i < k; i++) {
			int val = set.first();
			if(i == k - 1) {
//				System.out.println(set);
				return val;
			}
			set.remove(val);
			add(set, val);
		}
		return -1;
	}
	
	private static void add(TreeSet<Integer> set, int val) {
		// control overflow.
		set.add(val * 3 < 0 || val * 3 == Integer.MAX_VALUE ? Integer.MAX_VALUE : val * 3);
		set.add(val * 5 < 0 || val * 5 == Integer.MAX_VALUE ? Integer.MAX_VALUE : val * 5);
		set.add(val * 7 < 0 || val * 7 == Integer.MAX_VALUE ? Integer.MAX_VALUE : val * 7);
	}

	/*
	* Use three queues. extracting the min, and put min * 3 into Q3, min * 5 into Q5, min * 7 into Q7, if min found in the third.
	 * 										   if min found in 5, put min * 5 into Q5, min * 7 into Q7, e.g. 3 * 5 * 3 == 3 * 3 * 5 (added first by 3)
	 * 										   if mind found in 7, only add min * 7 into Q7
	 * */
	public static int findKth(int k) {
		if(k < 0) return -1;
		Queue<Integer> Q3 = new LinkedList<>(), Q5 = new LinkedList<>(), Q7 = new LinkedList<>();
		Q3.add(1);
		int min = 0;
		for(int i = 0; i < k; i++) {
			int three = Q3.peek(), five = Q5.isEmpty()? Integer.MAX_VALUE : Q5.peek(), seven = Q7.isEmpty()? Integer.MAX_VALUE : Q7.peek();
			min = Math.min(three, Math.min(five, seven));
			if(min == three) {
				Q3.poll();
				Q3.add(min * 3);
				Q5.add(min * 5);
			} else if(min == five) {
				Q5.poll();
				Q5.add(min * 5);
			} else {
				Q7.poll();
			}
			Q7.add(min * 7);
		}
		return min;
	}
	
	public static void main(String[] args) {
		for (int i = 1; i < 14; i++) {
			if(findKth(i) != getKth(i)) System.out.println("error at " + i);
//			System.out.println(findKth(i));
//			System.out.println(getKth(i));

		}
	}

}
