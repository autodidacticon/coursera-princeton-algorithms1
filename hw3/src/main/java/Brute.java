import java.util.Arrays;


public class Brute {

	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenRadius(.02);
		StdDraw.setPenColor(StdDraw.BLUE);
		In in = new In(args[0]);
		int N = in.readInt();
		Point[] points = new Point[N];
		for ( int i=0; i<N; i++ ){
			if ( points[i] == null ) {
				points[i] = new Point(in.readInt(), in.readInt() );
				points[i].draw();
			}
			for ( int j=i+1; j<N; j++ ){
				if ( points[j] == null ) {
					points[j] = new Point(in.readInt(), in.readInt() );
					points[j].draw();
				}
				for ( int k=j+1; k<N; k++ ){
					if ( points[k] == null ) {
						points[k]= new Point(in.readInt(), in.readInt() );
						points[k].draw();
					}
					for ( int l=k+1; l<N; l++ ){
						if ( points[l] == null ) {
							points[l] = new Point(in.readInt(), in.readInt() );
							points[l].draw();
						}
						if ( bruteCollinear( points[i], points[j], points[k], points[l]) ) {
							StdDraw.setPenRadius(.002);
							StdDraw.setPenColor(StdDraw.BLACK);
							points[i].drawTo(points[j]);
							points[j].drawTo(points[k]);
							points[k].drawTo(points[l]);
							Point[] line = { points[i], points[j], points[k], points[l] };
							Arrays.sort(line);
							System.out.println( String.format("%s -> %s -> %s -> %s", 
								line[0].toString(), line[1].toString(), line[2].toString(), line[3].toString() ) );
							StdDraw.setPenRadius(.02);
							StdDraw.setPenColor(StdDraw.BLUE);
						}
					}
				}
			}
		}
	}

	private static boolean bruteCollinear(Point point, Point point2,
			Point point3, Point point4) {
		boolean coll = point.slopeTo(point2) == point.slopeTo(point3) && point.slopeTo(point2) == point.slopeTo(point4);
		return coll;
	}

}
