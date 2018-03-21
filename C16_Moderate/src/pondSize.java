import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pondSize {

	public static void main(String[] args) {
		int[][] land = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
		int[][] land2 = {{0,2,1,0}, {0,1,0,1}, {1,1,0,1}, {0,1,0,1}};
		System.out.println(pondSizeDFS(land));
	}
	
	private static List<Integer> pondSizeBFS(int[][] land) {
		List<Integer> res = new ArrayList<>();
		for(int i = 0; i < land.length; i++) {
			for(int j = 0; j < land[0].length; j++) {
				if(land[i][j] == 0) compute(land, i, j, res);
			}
		}
		for(int[] row : land) System.out.println(Arrays.toString(row));
		return res;
	}
	
	// BFS
	private static void compute(int[][] land, int i, int j, List<Integer> res) {
		Queue<int[]> que = new LinkedList<>();
		int size = 1; // Root should be added too
		que.offer(new int[] {i, j});
		land[i][j] = -1;
		while(!que.isEmpty()) {
			int[] pos = que.poll();
			for(int a = -1; a < 2; a++) {
				for(int b = -1; b < 2; b++) {
					// Pos 没有increase
					if(!outBound(land, a + pos[0], b + pos[1]) && land[a + pos[0]][b + pos[1]] == 0) {
						size++;
						// 在这里mark -1 add的时候 mark，否则会重复。
						land[a + pos[0]][b + pos[1]] = -1;
						que.offer(new int[] {a + pos[0], b + pos[1]});
					}
				}
			}
		}
		res.add(size);
		System.out.println(size);
	}
	
	private static boolean outBound(int[][] m, int a, int b) {
		return a < 0 || b < 0 || a >= m.length || b >= m[0].length;
	}
	
	private static List<Integer> pondSizeDFS(int[][] land) {
		List<Integer> res = new ArrayList<>();
		for(int i = 0; i < land.length; i++) {
			for(int j = 0; j < land[0].length; j++) {
				if(land[i][j] == 0) res.add(count(land, i, j));
			}
		}
		return res;
	}
	
	private static int count(int[][] land, int r, int c) {
		if(outBound(land, r, c) || land[r][c] != 0) return 0;
		land[r][c] = -1;
		int size = 1;
		for(int a = -1; a < 2; a++) {
			for(int b = -1; b < 2; b++) {
				size += count(land, a + r, b + c);
			}
		}
		return size;
	}

}
