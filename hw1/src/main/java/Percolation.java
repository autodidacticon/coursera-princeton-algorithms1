import java.util.Iterator;

public class Percolation {
	
	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF ufg;
	private int top, bottom, baseDim;
	private boolean[][] grid;
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		this.grid = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.grid[i][j] = false;
			}
		}
		uf = new WeightedQuickUnionUF(N*N + 2);
		ufg = new WeightedQuickUnionUF(N*N + 1);
		top = N*N;
		bottom = N*N + 1;
		baseDim = N;
	}              
	
	private int index(int i, int j){
	    if ( i < 1 || j < 1 || i > baseDim || j > baseDim )
                throw new IndexOutOfBoundsException();
	    
        return (i-1)*baseDim+j-1;
	}
	 // open site (row i, column j) if it is not already
	public void open(int i, int j){
		int index = index(i,j);
		if ( i == 1 ) {
			uf.union(top, index);
			ufg.union(top, index);
		}
		
		this.grid[i-1][j-1] = true;
		for (int x = -1; x <=1 ; x+=2) {
			if ( i + x >= 1 && i + x <= baseDim && isOpen(i+x, j) ){
				uf.union(index, index(i+x,j));
				ufg.union(index, index(i+x,j));
			}
		}
		
		for (int y = -1; y <= 1; y+=2) {
			if ( j + y <= baseDim && j + y >= 1 && isOpen(i, j+y) ) {
				uf.union(index, index(i,j+y));
				ufg.union(index, index(i,j+y));
			}
		}
		
		if ( i == baseDim ) 
			uf.union( index, bottom);
	};  
	 // is site (row i, column j) open?
	public boolean isOpen(int i, int j){
		return this.grid[i-1][j-1];
	};   
	// is site (row i, column j) full?
	public boolean isFull(int i, int j){
		return isOpen(i,j) && ufg.connected(top, index(i,j));
	};    
	public boolean percolates() {
		return uf.connected(top, bottom);
	};            // does the system percolate?
}
