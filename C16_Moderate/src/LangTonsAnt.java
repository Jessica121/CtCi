import java.util.Arrays;

public class LangTonsAnt {
	public static LangTonsAnt instance = new LangTonsAnt();
	
	public class Board {
		boolean[][] board = new boolean[1][1];
		Position init = new Position(0, 0);
		Ant ant = instance.new Ant(init, Orientation.RIGHT);
		
		public void move() {
			// turn the ant based on color of the board and (implicitly) ant's orientation
			ant.turn(board[ant.pos.x][ant.pos.y]);
			flip(ant.pos);
			ant.move();
			shiftWhenOutOfBound(ant.pos);
		}
		
		public void flip(Position pos) {
			if(board[pos.x][pos.y]) board[pos.x][pos.y] = false;
			else board[pos.x][pos.y] = true;
		}
		
		public void shiftWhenOutOfBound(Position pos) {
			// create new grid -> double the current grid and copy all the current elements.
			// shift ant's pos
			int shiftRow = 0, shiftCol = 0, row = board.length, col = board[0].length;
			if(pos.x < 0) {
				shiftRow = row;
				row *= 2;
			} else if(pos.x >= row) {
				row *= 2;
			}
			
			if(pos.y < 0) {
				shiftCol = col;
				col *= 2;
			} else if(pos.y >= col) {
				col *= 2;
			}
			
			// means need to expand
			if(row != board.length || col != board[0].length) {
				boolean[][] newGrid = new boolean[row][col];
				for(int i = 0; i < board.length; i++) {
					for(int j = 0; j < board[0].length; j++) {
						newGrid[shiftRow + i][shiftCol + j] = board[i][j];
					}
				}
				board = newGrid;
				ant.shiftPos(shiftRow, shiftCol);
			}
		}
		
		public void printKMoves(int k) {
			while(k > 0) {
				move();
				k--;
				for(int i = 0; i < board.length; i++) {
					System.out.println(Arrays.toString(board[i]));
				}
				System.out.println();
			}
			
		}
	}
	
	public class Ant {
		Position pos;
		Orientation ori;
		
		public Ant(Position pos, Orientation ori) {
			this.pos = pos;
			this.ori = ori;
		}
		
		public void shiftPos(int shiftRow, int shiftCol) {
			pos.x = pos.x + shiftRow;
			pos.y = pos.y + shiftCol;
		}
		
		public void move() {
			switch (ori) {
			case UP:
				pos.x--;
				break;
			case DOWN:
				pos.x++;
				break;
			case LEFT:
				pos.y--;
				break;
			case RIGHT:
				pos.y++;
				break;
			default: break;
			}
		}
		
		public void turn(boolean white) {
			this.ori = ori.turn(white);
		}
	}
	
	public enum Orientation {
		UP, DOWN, LEFT, RIGHT;
		
		public Orientation turn(boolean isWhite) {
			if(this == UP) return isWhite ? RIGHT : LEFT;
			else if(this == DOWN) return !isWhite ? RIGHT : LEFT;
			else if(this == LEFT) return isWhite ? UP : DOWN;
			else return !isWhite ? UP : DOWN;
		}
	}
	
	public class Position {
		int x, y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void print() {
			System.out.println("( " + x + ", " + y + " )");
		}
		
		// Provide the hashcode func and equals when need it to be in the set.
		@Override
		public int hashCode() {
			return (x * 31) ^ y;
		}
		
		@Override 
		public boolean equals(Object o) {
			if(o instanceof Position) {
				return ((Position) o).x == this.x && (((Position) o).y == this.y);
			}
			return false;
		}
	}
	
	public static void main(String[] args) {
		Board b = instance.new Board();
		b.printKMoves(10);
	}
}
