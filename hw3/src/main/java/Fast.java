import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Fast {

	public static void main(final String[] args){
		StdDraw.setXscale(0, 40000);
		StdDraw.setYscale(0, 40000);
		StdDraw.setPenRadius(.02);
		StdDraw.setPenColor(StdDraw.BLUE);
		
		In in = new In(args[0]);
		int N = in.readInt();
		Point[] points = new Point[N];
				
		for (int i=0; i<N; i++){
			points[i] = new Point(in.readInt(), in.readInt());
			points[i].draw();
		}
		StdDraw.setPenRadius(.002);
		StdDraw.setPenColor(StdDraw.BLACK);
		Arrays.sort(points);
		Point[] pointSort = points.clone();
		
		for (int i = 0; i<N; i++){
			Arrays.sort(pointSort, points[i].SLOPE_ORDER);
			double[] slopes = new double[N];
			for (int j = 1; j < N; j++){
				slopes[j] = pointSort[0].slopeTo(pointSort[j]);
			}

			for (int j = 1; j+2 < N; j++){
				int n = 3;
				if ( slopes[j] == slopes[j+1] &&
						slopes[j] == slopes[j+2] ){
					while (j+n < N){
						if (slopes[j] == slopes[j+n]){
							n += 1;
						} else {
							break;
						}
					}
					Point[] line = new Point[n+1];
					line[n] = points[i];
					for (int k = 0; k < n; k++){
						line[k] = pointSort[j+k];
					}
					Arrays.sort(line);
					j+=n-1;
					
					if ( line[0].compareTo(points[i]) == 0 ) {
						StringBuilder out = new StringBuilder(line[0].toString());
						line[0].drawTo(line[line.length-1]);
						for (int k = 1; k < line.length; k++){
							out.append(" -> "+line[k].toString());
						}
						System.out.println(out);
					}
				}
			}
			
		}
	}
}