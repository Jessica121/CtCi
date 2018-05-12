import java.util.HashSet;
import java.util.Set;

public class LangtonsAntSet {
	public static LangTonsAnt ins = new LangTonsAnt();
	public static LangtonsAntSet instance = new LangtonsAntSet();
	
	class Board {
		Set<LangTonsAnt.Position> set = new HashSet<>();
		LangTonsAnt.Position init = ins.new Position(0, 0);
		LangTonsAnt.Ant ant = ins.new Ant(init, LangTonsAnt.Orientation.RIGHT);
		int rowMin = 0, rowMax = 0, colMin = 0, colMax = 0;
		
		public void move() {
			ant.turn(isWhite(ant.pos.x, ant.pos.y)); // ori is encapsulated in ant, orientation is updated.
			flip(ant.pos);
			ant.move(); // move in the orientation, and update its pos as well.
			updateBorders(ant.pos);
		}
		
		public void updateBorders(LangTonsAnt.Position pos) {
			if(pos.x < rowMin) rowMin = pos.x;
			else if(pos.x > rowMax) rowMax = pos.x;
			if(pos.y < colMin) colMin = pos.y;
			else if(pos.y > colMax) colMax = pos.y;
		}
		
		public void flip(LangTonsAnt.Position pos) {
			if(set.contains(pos)) set.remove(pos);
			else set.add(ins.new Position(pos.x, pos.y));
		}
		
		public boolean isWhite(int r, int c) {
			return set.contains(ins.new Position(r, c));
		}	
		
		public void printKMoves(int k) {
			while(k > 0) {
				move();
				k--;
			}
			System.out.println(rowMin + " " + rowMax + " " + colMin + " " + colMax);
			for(int i = rowMin; i <= rowMax; i++) {
				for(int j = colMin; j <= colMax; j++) {
					if(isWhite(i, j)) System.out.print(" W ");
					else System.out.print(" B ");
				}
				System.out.println();
			}
			for(LangTonsAnt.Position pos : set) 
				pos.print();
		}
	}
	
	public static void main(String[] args) {
		Board b = instance.new Board();
		b.printKMoves(10);
	}

}
