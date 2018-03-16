
public class BisectSquares {
	
	class Point {
		double x;
		double y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		@Override 
		public String toString() {
			return "(" + x + ", " + y +")";
		}
	}
	class Square {
		Point p;
		double wid;
		public Square(Point p, double wid) {
			this.p = p;
			this.wid = wid;
		}
		
		private Point getMid() {
			return new Point(p.x + (wid / 2.0), p.y - (wid / 2.0));
		}
		
		private Point getLowRight() {
			return new Point(p.x + wid, p.y - wid);
		}
		
		private Point getTopLeft() {
			return new Point(p.x, p.y);
		}
	}
	public static BisectSquares init = new BisectSquares();
	public static void main(String[] args) {
		Point p1 = init.new Point(8,10);
		Point p2 = init.new Point(10,10);
		Square s1 = init.new Square(p1, 10);
		Square s2 = init.new Square(p2, 10);
		System.out.println(bisectSquare(s1, s2));
	}
	
	private static String bisectSquare(Square s1, Square s2) {
		// ***Switch smaller one to the left
		if(s2.p.x < s1.p.x || (s2.p.x == s1.p.x && s1.p.y < s2.p.y)) {
			Square temp = s1;
			s1 = s2;
			s2 = temp;
		}
		Point mid1 = s1.getMid();
		Point mid2 = s2.getMid();
		if(mid1.toString().equals(mid2.toString())) return "Invalid Input - Same Squares";
//		System.out.println(mid1.toString() + mid2.toString());
		Point p1 = null, p2 = null;
		if(mid1.x == mid2.x) {
			p1 = init.new Point(mid1.x, s1.p.y);
			p2 = init.new Point(mid2.x, s2.getLowRight().y);
		} else if(mid1.y == mid2.y) {
			p1 = init.new Point(s1.p.x, mid1.y);
			p2 = init.new Point(s2.getLowRight().x, mid2.y);
		} else {
			double slop = (mid1.y - mid2.y) / (mid1.x - mid2.x);
			double intersect = s1.p.y - slop * s1.p.x;
			if(Math.abs(intersect) >= 1) {
				p1 = init.new Point((s1.p.y - intersect) / slop, s1.p.y);
				p2 = init.new Point((s2.getLowRight().y - intersect) / slop, s2.getLowRight().y);
			} else {
				p1 = init.new Point(s1.p.x, s1.p.x * slop + intersect);
				p2 = init.new Point(s2.getLowRight().x, s2.getLowRight().x * slop + intersect);
			}
			System.out.println(slop);
			System.out.println(intersect);
		}
		return p1.toString() + p2.toString();
	}

}
