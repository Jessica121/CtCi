import java.util.*;
public class CircusTower {
	/*
	 * A seq of people with height and width, get the longest possible sequence where both the height and weight are sorted in increasing order.
	 * 
	 * intuitive: sort on both height and width. backtracking to get the best res.
	 * 
	 * 
	 * 
	 * 
	 * */
	
	public static class People {
		int height, weight;
		public People(int h, int w) {
			this.height = h;
			this.weight = w;
		}
		
		public boolean canBeTopOf(People p) {
			return this.height < p.height && this.weight < p.weight;
		}
		
		public String toString() {
			return "(" + this.height + ", " + this.weight + ")";
		}
	}
	
	public static List<People> longestSequence(List<People> list) {
		sort(list);
//		printList(list);
		List<People> res = new ArrayList<>();
		calculate(list, 0, new ArrayList<>(), res);
		return res;
	}
	
	//O(2 ^ n) 0101010 choose or not choose.
	private static void calculate(List<People> list, int index, List<People> temp, List<People> max) {
		if(index >= list.size()) {
			return;
		}
		
		// cannot be put into termination, as the index == length cannot garantee to be reached, 
		// when the last element cannot append to the temp list,
		// it will not recursively call next one.
		if(temp.size() > max.size()) {
			// cannot use max = new ArrayList<>(temp) 
			max.clear();
			max.addAll(temp);
		}

		for(int i = index; i < list.size(); i++) {
			People p = list.get(i);
			if(temp.isEmpty() || temp.get(temp.size() - 1).canBeTopOf(p)) {
				temp.add(p);
				calculate(list, index + 1, temp, max);
				temp.remove(temp.size() - 1);
			}
		}
	}
	
	/*
	 * save till each index longest sequence, next index, iterate thru prev ones. append to the longest appenddable one.
	 * in the process, update a max one and return at the end
	 * O(N^2)
	 * 
	 * */
	
	public static List<People> findLongest(List<People> list) {
		sort(list);
		List<People> res = new ArrayList<>();
		List<List<People>> byIndex = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			List<People> index = findLongestAtThisIndex(list, byIndex, i);
			if(index.size() > res.size()) res = index;
		}
		for(List<People> l : byIndex) printList(l);
		return res;
	}
	
	private static List<People> findLongestAtThisIndex(List<People> list, List<List<People>> byIndex, int index) {
		List<People> res = new ArrayList<>();
		People people = list.get(index);
		if(!byIndex.isEmpty()) {
			for(int i = 0; i < index; i++) {
				List<People> prev = byIndex.get(i);
				if(prev.get(prev.size() - 1).canBeTopOf(people)) {
					if(prev.size() > res.size()) res = new ArrayList<>(prev); // MUST REMEBER TO CREATE A [COPY], NOT THE REFERENCE.
				}
			}
		}
		res.add(people);
		byIndex.add(res);
		return res;
	}
	
	private static void sort(List<People> list) {
		Collections.sort(list, (a, b) -> {
			if(a.height != b.height) return a.height - b.height;
			else return a.weight - b.weight;
		});
	}

	public static ArrayList<People> initialize() {
		ArrayList<People> items = new ArrayList<People>();
		
		People item = new People(65, 60);
		items.add(item);
		
		item = new People(70, 150);
		items.add(item);
		
		item = new People(56, 90);
		items.add(item);
		
		item = new People(75, 190);
		items.add(item);
		
		item = new People(60, 95);
		items.add(item);
		
		item = new People(68, 110);
		items.add(item);
		
		item = new People(35, 65);
		items.add(item);
		
		item = new People(40, 60);
		items.add(item);
		
		item = new People(45, 63);
		items.add(item);
		
		return items;
	}
	
	public static void printList(List<People> list) {
		for (People item : list) {
			System.out.print(item.toString() + " ");
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		List<People> items = initialize();
		List<People> solution = findLongest(items);
		printList(solution);
	}

}
