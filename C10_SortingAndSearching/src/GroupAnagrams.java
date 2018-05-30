import java.util.Arrays;
import java.util.Comparator;

public class GroupAnagrams {
	// act cat - > act
	public static class AnagramComparator implements Comparator<String> {
		public String sortString(String str) {
			char[] arr = str.toCharArray();
			Arrays.sort(arr);
			return (new String(arr));
		}
		
		public int compare(String o1, String o2) {
			return sortString(o1).compareTo(sortString(o2));
		}
	}
	
	public static void groupAnagrams(String[] arr) {
		Arrays.sort(arr, new AnagramComparator());
	}
	
	public static void main(String[] args) {
		String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
		groupAnagrams(arr);
		System.out.println(Arrays.toString(arr));
	}
	
}
