import java.util.HashMap;
import java.util.Map;

public class masterMind {
	private static final masterMind ms = new masterMind();
	public static void main(String[] args) {
		// R - G G R R 
		// if its hit cannot be sudoHit
		System.out.println(masterMind("AACD", "DCBA").toString());
	}
	
	class Result {
		int hit = 0;
		int sudoHit = 0;
		public Result(int h, int s) {
			this.hit = h;
			this.sudoHit = s;
		}
		
		public String toString() {
			return "hit: " + hit + ", sudohit: " + sudoHit;
		}
	}
	
	private static Result masterMind(String solu, String guess) {
		if(solu == null || guess == null || solu.length() != guess.length()) return null;
		// R G B Y - G G R R  
		int hit = 0, sudo = 0;
		Map<Character, Integer> map = new HashMap<>();
		for(int i = 0; i < solu.length(); i++) {
			if(solu.charAt(i) != guess.charAt(i)) {
				map.put(solu.charAt(i), map.getOrDefault(solu.charAt(i), 0) + 1);
			} else hit++;
		} 
		for(int i = 0; i < guess.length(); i++) {
			if(map.containsKey(guess.charAt(i))) {
				sudo++;
				if(map.get(guess.charAt(i)) == 1) map.remove(guess.charAt(i));
				else map.put(guess.charAt(i), map.get(guess.charAt(i)) - 1);
			}
		}
		return ms.new Result(hit, sudo);
	}

}
