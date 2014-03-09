import java.util.Arrays;
import java.util.HashMap;

public class Fast {

	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenRadius(.02);
		StdDraw.setPenColor(StdDraw.BLUE);
		
		In in = new In(args[0]);
		int N = in.readInt();
		Point[] points = new Point[N];
		HashMap< String, Double > origins = new HashMap<String, Double>();
				
		for ( int i=0; i<N; i++ ){
			points[i] = new Point( in.readInt(), in.readInt() );
			points[i].draw();
		}
		StdDraw.setPenRadius(.002);
		StdDraw.setPenColor(StdDraw.BLACK);
		Arrays.sort(points);
		
		for ( int i = 0; i<N; i++ ){
			Point[] pointSort = points.clone();
			Arrays.sort(pointSort, points[i].SLOPE_ORDER);
			
			for ( int j = 0; j+2 <= N-1; j+=2 ) {
				int n = 3;
				if ( points[i].slopeTo(pointSort[j]) == points[i].slopeTo(pointSort[j+1]) &&
						points[i].slopeTo(pointSort[j]) == points[i].slopeTo(pointSort[j+2]) ) {
					while ( j + n <= N-1 ){
						if ( points[i].slopeTo(pointSort[j]) == points[i].slopeTo(pointSort[j+n]) ) {
							n+=1;
						} else {
							break;
						}
					}
					Point[] line = new Point[n+1];
					line[n] = points[i];
					for ( int k = 0; k < n; k++ ) {
						line[k] = pointSort[j+k];
					}
					Arrays.sort(line);
					String key = Integer.toString(line[0].x)+Integer.toString(line[0].y);
					double slope = line[0].slopeTo(line[line.length-1]);
					if( !origins.containsKey(key) || origins.get(key) != slope ){
						origins.put(key, slope);
						StringBuilder out = new StringBuilder(line[0].toString());
						for ( int k = 1; k < line.length; k++ ) {
							line[0].drawTo(line[k]);
							out.append("-> "+line[k].toString());
						}
						System.out.println(out);
					}
				}
			}
			
		}
	}
}