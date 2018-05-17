import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StackOfBoxesCannotRotate {
	public static StackOfBoxesCannotRotate instance = new StackOfBoxesCannotRotate();
	public static void main(String[] args) {
		Box b0 = instance.new Box(6,0,0), b1 = instance.new Box(5,4,3), b2 = instance.new Box(4,3,6), b3 = instance.new Box(4,2,2); 
		Box b4 = instance.new Box(3,9,7), b5 = instance.new Box(3,1,1);
		List<Box> list = new ArrayList<>();
		list.add(b1);list.add(b0);list.add(b2);list.add(b3);list.add(b4);list.add(b5);
		Collections.sort(list, (a, b) -> {
			if(a.h != b.h) return b.h - a.h;
			else if(a.w != b.w) return b.w - a.w;
			else return b.l - a.l;
		});
		for(Box b : list) System.out.println(b.toString());
		System.out.println(maxHeight(list));
		maxBackTrack(list, 0, null, 0);
		System.out.println("backtrack " + res);
		System.out.println("add it or not , " + addOrNotAddIt(list, 0, null, new int[list.size()]));
		System.out.println("DP" + maxdp(list));
	}
	
	public class Box {
		int w, h, l;
		public Box(int h, int w, int l) {
			this.w = w;
			this.h = h;
			this.l = l;
		}
		
		private boolean contains(Box b) {
			return this.w > b.w && this.h > b.h && this.l > b.l;
		}
		
		@Override
		public String toString() {
			return "" + h + ", " + w + ", " + l;
		}
	}
	
	public static int maxHeight(List<Box> list) {
		int[] memo = new int[list.size()];
		int res = 0;
		for(int i = 0; i < list.size(); i++) {
			res = Math.max(res, maxHeight(list, i, memo));
		}
		return res;
	}
	
	private static int maxHeight(List<Box> list, int index, int[] memo) {
//		if(index == list.size()) return 0;
		int res = 0;
		if(memo[index] != 0) return memo[index];
		Box b = list.get(index);
		for(int j = index + 1; j < list.size(); j++) {
			Box next = list.get(j);
			if(b.contains(next)) res = Math.max(res, maxHeight(list, j, memo));
		}
		return memo[index] = res + b.h;
	}
	
	public static int res = 0;
	private static void maxBackTrack(List<Box> list, int index, Box prev, int hei) {
		if(index == list.size()) {
			res = Math.max(res, hei);
			return;
		}
		for(int i = index; i < list.size(); i++) {
			Box b = list.get(i);
			if(prev == null || prev.contains(b)) {
				maxBackTrack(list, i + 1, b, hei + b.h);
			} else maxBackTrack(list, i + 1, prev, hei);
		}
	}
	
	public static int addOrNotAddIt(List<Box> list, int index, Box prev, int[] memo) {
		if(index == list.size()) return 0;
		if(memo[index] != 0) return memo[index];
		int addIt = 0, notAdd = 0;
		Box b = list.get(index);
			
		if(prev == null || prev.contains(b)) {
			addIt = b.h + addOrNotAddIt(list, index + 1, b, memo);
		}
		// one clever way is to just keep the prev.
		notAdd = addOrNotAddIt(list, index + 1, prev, memo);
		return Math.max(addIt, notAdd);
	}
	
	public static int maxdp(List<Box> list) {
		int[] dp = new int[list.size() + 1];
		int max = 0;
		for(int i = list.size(); i >= 1; i--) {
			Box b = list.get(i - 1);
			int prev = 0;
			for(int j = list.size() - 1; j > i; j--) {
				if(b.contains(list.get(j))) prev = Math.max(prev, dp[j]);
			}
			dp[i] = Math.max(dp[i - 1], b.h + prev);
			max = Math.max(max, dp[i]);
		}
		System.out.println(Arrays.toString(dp));
		return max;
	}

}
