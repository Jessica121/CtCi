import java.util.*;
public class SparseSimilarity {
	/*
	 * Given a bunch of documents with words, find all pairs of docs that has overlap words. 
	 * compute their similarities: overlap / uniq elements of two.
	 * e.g.: ID : WORDS
	 * 	      1 : 1, 5 ,3
	 * 		  2 : 1, 7, 2, 3
	 * simi: 2 / (7 - 2) = 0.4
	 * most of the documents does not overlap, the simi mostly small
	 * 
	 * 
	 * idea: save each word, map all docs contain it.
	 * 1: 1, 2
	 * 5: 1
	 * 3: 1, 2
	 * 7: 2
	 * 2: 2
	 * another map, for each pair in the word map, if size() > 1, means overlap, record its number.
	 * 
	 * similarities: iterate thru second map, for each pair, get the value and the sum of two - value(intersection) divide.
	 * for the pair: compare the value, small one first.
	 * 
	 * */
	
	//O(D*W) D:dict size * W longest word, O(P) num of pairs.
	public static Map<List<Integer>, Double> computeSimilarity(Map<Integer, List<Integer>> docs) {
		Map<Integer, List<Integer>> wordToDoc = new HashMap<>();
		computeWordToDoc(wordToDoc, docs);
		Map<List<Integer>, Integer> overlap = new HashMap<>();
		computeOverLap(overlap, wordToDoc);
		Map<List<Integer>, Double> res = new HashMap<>();
		// array cannot be used as keys in hashmap.
		for(List<Integer> key : overlap.keySet()) {
			double sum = docs.get(key.get(0)).size() + docs.get(key.get(1)).size();
			int ove = overlap.get(key);
			double sim = ove / (sum - ove);
			res.put(key, sim);
		}
		return res;
	}
	
	private static void computeWordToDoc(Map<Integer, List<Integer>> wordToDoc, Map<Integer, List<Integer>> docs) {
		for(Integer doc : docs.keySet()) {
			for(int word: docs.get(doc)) {
				wordToDoc.computeIfAbsent(word, k -> new ArrayList<>()).add(doc);
			}
		}
	}
	
	//O(P) P: pairs.
	private static void computeOverLap(Map<List<Integer>, Integer> overlap, Map<Integer, List<Integer>> wordToDoc) {
		for(int word : wordToDoc.keySet()) {
			if(wordToDoc.get(word).size() > 1) {
				List<Integer> list = wordToDoc.get(word);
				for(int i = 0; i < list.size() - 1; i++) {
					for(int j = i + 1; j < list.size(); j++) {
						if(list.get(i) > list.get(j)) {
							int t = i;
							i = j; 
							j = t;
						}
						List<Integer> key = Arrays.asList(list.get(i), list.get(j));
						overlap.put(key, overlap.getOrDefault(key, 0) + 1);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Map<Integer, List<Integer>> docs = new HashMap<>();
		List<Integer> word = new ArrayList<>();
		word.add(1);word.add(5);word.add(3);
		docs.put(1, word);
		List<Integer> word2 = new ArrayList<>();
		word2.add(1);word2.add(7);word2.add(2);word2.add(3);
		docs.put(2, word2);
		System.out.println(computeSimilarity(docs));
	}

}
