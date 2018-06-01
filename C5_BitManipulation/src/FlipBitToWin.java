import java.util.ArrayList;
import java.util.List;

public class FlipBitToWin {

	/*
	 * Given a number, flip one bit from 0 to 1, output the length of the longest consecutive 1s
	 * e.g. 11011101111 (1775)
	 *  	output 8
	 *  
	 *  go thru the binary rep, record with arraylist, the start (0) and the length, and the index of the 0s
	 *  start from i = 0, get the distance of i + 2, record the max.
	 * 
	 * */
	public static int longestSequence(int n) {
		List<Integer> index = new ArrayList<>();
		index.add(-1); // add the bound -1 for easier calculation
		for(int i = 0; i < 32; i++) {
			if(((n >> i) & 1) == 0) index.add(i);
		}
		index.add(32); // add the length last index.
		// all ones 
//		System.out.println(index);
		if(index.size() == 2) return index.get(1) - index.get(0) - 1;
		int res = 0;
		for(int j = 0; j < index.size() - 2; j++) {
			res = Math.max(res, index.get(j + 2) - index.get(j) - 1);
		}
		return res;
	}
	
	// save the length of 0 and 1s, concat left 1 len and right 1 len + 1 when len of 0 is 1, when > 1, use bigger of left / right
	// when == 0 (at the border) use the left / right one that is inbound
	public static int longestSeq2(int n) {
		List<Integer> intersectLen = new ArrayList<>();
		int prev = 0, cnt = 0;
		// 11011101111
		for(int i = 0; i < Integer.SIZE; i++) {
			if(((n >> i) & 1) == prev) cnt++;
			else {
				prev = (n >> i) & 1;
				intersectLen.add(cnt);
				cnt = 1;
			}
		}
		intersectLen.add(cnt);
		if(prev == 1) intersectLen.add(0);
		// 0, 4, 1, 3, 1, 2, 21
		// even indexes rep 0
		int res = 0;
		for(int i = 0; i < intersectLen.size(); i += 2) {
			int left = i - 1 < 0 ? 0 : intersectLen.get(i - 1), right = i + 1 >= intersectLen.size() ? 0 : intersectLen.get(i + 1);
			int partialLen = 0;
			if(intersectLen.get(i) == 1) partialLen = left + 1 + right;
			else if(intersectLen.get(i) == 0) partialLen = Math.max(left, right);
			else partialLen = Math.max(left, right) + 1;
			res = Math.max(res, partialLen);
		}
		return res;
	}
	
	/*
	 * no need for tracking all the sequence, only the most recent 2 1s sequence and 0 is used. could reduce the space into constant
	 * if meets a 1, increase current 1 sequence, if 0, update max to prev + cur + 1, if one 0, prev = cur, else more than 1, prev = 0
	 * meaning, the next sequece of 1s cannot use this sequence since there are 1+ 0s between them
	 * if start with 0s, prev = cur = 0, res = 1, it's OK,
	 * if start with 1s, prev = 0, when meet the first 0, cur + prev + 1 = cur + 1, it's also OK.
	 * if all 0s, cur = prev = 0, OK
	 * if all 1s, out of the loop, cur + prev + 1, not OK. should be put into special case at the beginning.
	 * 
	 * or could update the max whether its 1 or 0. 
	 * when it's 0, update the prev as above. 
	 * when it's 1, increase the current as above, and update the max to cur + prev + 1, but it needs all 1 as the special case as well.
	 * this way no need to calculate again out of the loop.
	 * */
	
	public static int longestSeqConstantSpace(int n) {
		// 11011101111
		if(n == -1) return Integer.SIZE;
		int cur = 0, prev = 0, res = 0;
		for(int i = 0; i < Integer.SIZE; i++) {
			if(((n >> i) & 1) == 1) cur++;
			else {
				if(((n >> i) & 2) == 2) prev = cur;
				else prev = 0;
				cur = 0;
			}
			res = Math.max(res, prev + cur + 1);
		}
		return res;
	}
	
	public static void main(String[] args) { 
		for(int n = 0; n < 100; n++) {
			System.out.println((longestSequence(n) == longestSeq2(n)) && (longestSeq2(n) == longestSeqConstantSpace(n)));
		}
	}

}
