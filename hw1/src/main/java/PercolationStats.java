import java.util.Random;


public class PercolationStats {
	private double[] stats;
	 public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
	 {
		 if ( N <= 0 || T <= 0 ){
				throw new java.lang.IllegalArgumentException("Invalid arguments");
			}
		 stats = new double[T];
		for (int r = 0; r < T; r++) {
			Percolation p = new Percolation(N);
			int i ,j;
			double k = 0;
			while (!p.percolates()){
				i = StdRandom.uniform(1,N+1);
				j = StdRandom.uniform(1,N+1);
				if ( !p.isOpen(i, j) ){
					p.open(i, j);
					k += 1;
				}
			}
			stats[r] = k / (N*N);
		} 
	 }
	   public double mean()                     // sample mean of percolation threshold
	   {
		return StdStats.mean(stats);
		   
	   }
	   public double stddev()                   // sample standard deviation of percolation threshold
	   {
		return StdStats.stddev(stats);
		   
	   }
	   public double confidenceLo()             // returns lower bound of the 95% confidence interval
	   {
		return mean() - 1.96*stddev() / Math.sqrt((double)stats.length);
		   
	   }
	   public double confidenceHi()             // returns upper bound of the 95% confidence interval
	   {
		   int i = stats.length;
		   double mean = mean();
		   double st = stddev();
		   double s = 1.96*stddev();
		   double sq = Math.sqrt(stats.length);
		   return mean() + 1.96*stddev() / Math.sqrt((double)stats.length);
		   
	   }

	public static void main(String[] args) {
		if ( args.length != 2 ) {
			throw new java.lang.IllegalArgumentException("Not enough arguments");
		} 
		PercolationStats ps = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
		System.out.println("mean "+ps.mean());
		System.out.println("stddev "+ps.stddev());
		System.out.printf("95%% confidence interval %.2f %.2f\n",ps.confidenceLo(),ps.confidenceHi());
	}

}
