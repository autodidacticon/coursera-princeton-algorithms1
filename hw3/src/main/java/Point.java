import java.util.Comparator;

public class Point implements Comparable<Point> {
   public final Comparator<Point> SLOPE_ORDER;        // compare points by slope to this point
   private final int x;
   private final int y;

   public Point(final int x, final int y) {
	   // construct the point (x, y)
	   this.x = x;
	   this.y = y;
	   this.SLOPE_ORDER = new Comparator<Point>(){

		public int compare(Point o1, Point o2) {
			if (o1 == null || o2 == null) {
				throw new java.lang.NullPointerException();
			}
			
			double slope1 = slopeTo(o1);
			double slope2 = slopeTo(o2);
			if (slope1 < slope2)
				return -1;
			else if (slope1 > slope2)
				return 1;
			else
				return 0;
		}
	   };
   }

   public void draw(){
	   // draw this point
	   StdDraw.point(x, y);
   }
   
   public void drawTo(Point that) {
	   // draw the line segment from this point to that point
	   StdDraw.line(x, y, that.x, that.y);
   }
   
   public String toString(){
	   // string representation
	   return String.format("(%d, %d)", x, y);
   }

   public int compareTo(Point that) {
	   // is this point lexicographically smaller than that point?
	   if (that == null) {
		   throw new java.lang.NullPointerException();
	   }
		if (y < that.y || (y == that.y && x < that.x))
			return -1;
		else if (y > that.y || (y == that.y && x > that.x))
			return 1;
		else
			return 0;
   }
   
   public double slopeTo(Point that) {
	   // the slope between this point and that point
	   if (that == null) {
		   throw new java.lang.NullPointerException();
	   }
	   if (y == that.y && x == that.x)
		   return Double.NEGATIVE_INFINITY;
	   else if (y == that.y)
		   return 0;
	   else if (x == that.x)
		   return Double.POSITIVE_INFINITY;
	   else
		   return (double)(that.y - y) / (that.x -x);
   }
}

