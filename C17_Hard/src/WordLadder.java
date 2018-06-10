import java.util.*;
public class WordLadder {
	 /*
        can return the length of each transformation.
        if 1 + next. update the max, return.
        if word == endWord, return 1, if all words visited / not exist in the dict, return 0.
        if next == 0, return 0 instead.
        need a Set for visited in DFS searches.
        iterate each char from a to z, if the transformed word exist in the dict and not visited. put visited and take the next, start word is transformed word.
        if start word == end word. return 1.
        backtrack visited.
     */
	public int ladderLengthDFS(String beginWord, String endWord, List<String> wordList) {
        return minStep(beginWord, endWord, new HashSet<>(wordList), new HashSet<>());
    }
    
    private int minStep(String begin, String end, Set<String> dict, Set<String> visited) {
        if(begin.equals(end)) return 1;
        char[] array = begin.toCharArray();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < array.length; i++) {
            char origin = array[i];
            for(char cha = 'a'; cha <= 'z'; cha++) {
                array[i] = cha;
                String trans = new String(array);
                if(dict.contains(trans) && !visited.contains(trans)) {
                    visited.add(trans);
                    int next = minStep(trans, end, dict, visited);
                    if(next != 0) min = Math.min(min, next + 1);
                    visited.remove(trans);
                }
            }
            // dont forget to transform back if using the array to transform.
            array[i] = origin;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
       
    
    /*
        BFS: transform each index of begin word, put into queue. 
        level++ before iterate from 0 to queue.size 
        if word equal end, return the level;
        else transfrom, put into visited.
        cannot print: have to record all the path. and take the one that ends
      */    
    public int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) { 
        Queue<String> que = new LinkedList<>();
        Set<String> visited = new HashSet<>(), dict = new HashSet<>(wordList);
        int level = 0;
        que.offer(beginWord);
        while(!que.isEmpty()) {
            level++;
            int size = que.size();
            for(int j = 0; j < size; j++) {
                String target = que.poll();
                if(target.equals(endWord)) return level;
                char[] array = target.toCharArray();
                for(int i = 0; i < array.length; i++) {
                    char origin = array[i];
                    for(char cha = 'a'; cha <= 'z'; cha++) {
                        array[i] = cha;
                        String trans = new String(array);
                        if(dict.contains(trans) && !visited.contains(trans)) {
                            visited.add(trans);
                            que.offer(trans);
                        }
                    }
                    // dont forget to transform back if using the array to transform.
                    array[i] = origin;
                }
            }
        }
        return 0;
    }
    
 /*
    Two-end BFS
    use two sets instead of queues, for look up when transformed string contained in another level(set), an level track the length. *****if one edit away from another, the transformed string contained in another set*******
    use a set to track visited strings, from one end it may go back to its original set, so stopping when visited set contains it does not indicate find the path.
    two sets only store the one level of stuff, behaving like queues. just for faster look ups
    for faster operation, operate on smaller set.
    to prevent duplicate code, swap the set before BFS
    level is one at first, from the start word. after go thru one level, no matter which set, increase it.
    if the transfromed string already in another set, means find it, return level + 1;
    if one of them is empty and cannot connet to the other one, then the other one cannot connet with this either. this way not found.
    while two sets are not empty, swap the smaller set before BFS, put the transformed into a temp set, increase the level, 
    override the current set by the temp set.
*/
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if(!wordList.contains(endWord)) return 0;
    Set<String> set1 = new HashSet<>(), set2 = new HashSet<>(), visited = new HashSet<>();
    int level = 1;
    set1.add(beginWord);
    set2.add(endWord);
    while(!set1.isEmpty() && !set2.isEmpty()) {
        if(set1.size() > set2.size()) {
            Set<String> temp = set1;
            set1 = set2;
            set2 = temp;
        }
        level++;
        Set<String> temp = new HashSet<>();
        for(String s : set1) {
            char[] arr = s.toCharArray();
            for(int i = 0; i < arr.length; i++) {
                char origin = arr[i];
                for(char c = 'a'; c <= 'z'; c++) {
                    arr[i] = c;
                    String trans = new String(arr);
                    if(set2.contains(trans)) return level;
                    if(wordList.contains(trans) && !visited.contains(trans)) {
                        visited.add(trans);
                        temp.add(trans);
                    }
                }
                arr[i] = origin;
            }
        }
        set1 = temp;
    }
    return 0;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
