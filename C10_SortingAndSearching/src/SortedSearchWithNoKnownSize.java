import java.util.ArrayList;
import java.util.List;

public class SortedSearchWithNoKnownSize {

	/*
	 * If the elementAt(i) == -1, means the i is out of the bounds of the size of the list.
	 * try the 2^0, 2^1, 2^k till get greater than target or -1.
	 * */
	
	public static int findSortedWithoutSize(Listy list, int target) {
		int index = 1; // 2^0
		while(true) {
			if(list.elementAt(index) == -1 || list.elementAt(index) >= target) break;
			index *= 2;
		}
		int lo = index / 2, hi = index, mid = -1;
		while(lo <= hi) {
			mid = lo + (hi - lo) / 2;
			int midEle = list.elementAt(mid);
			if(midEle == target) return mid;
			else {
				if(midEle == -1 || midEle > target) hi = mid - 1;
				else lo = mid + 1;
			}
		}
		return -1;
	}
	
	public static class Listy {
		List<Integer> arr = new ArrayList<>();
		public Listy(int size) {
			for(int i = 0; i < size; i++) arr.add(i);
		}
		
		public int elementAt(int i) {
			if(i >= arr.size()) return -1;
			else return arr.get(i);
		}
	}
	
	public static void main(String[] args) {
		Listy list = new Listy(1000);
		System.out.println(findSortedWithoutSize(list, 123));
	}

}
