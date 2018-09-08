import java.util.Arrays;

public class echelon {
	
	public static void main(String[] args) {
	
	float[][] A = 
		{	{0,0,-2,0,7,12},
			{2,4,-10,6,12,28},
			{2,4,-5,6,-5,-1}	};
	
	printMatrix(toRREF(A));
	
	}
	
	private static float[][] toRREF(float[][] A) {
		int m = A.length;
		int n = A[0].length;
		float coeff = 0;
		
		// Steps 1-4
		A = toREF(A, 0);
		
		// Step 5: get rid of all nonzero entries above leading ones
		for (int i = m-1; i > 0; i--) {
			for (int j = 0; j < n-1; j++) {
				if (A[i][j] == 1) {
					for (int k = i-1; k >= 0; k--) {
						if (A[k][j] != 0) { 
							coeff = A[k][j];
							for (int l = 0; l < n; l++) { A[k][l] = A[k][l] - coeff*A[i][l]; }	
						}
					}
				}
			}
		}
		
		return A;
	}
	
	public static float[][] toREF(float[][] A, int a) {

		int m = A.length;
		int n = A[0].length;
		int row = 0;
		int col = 0;
		float coeff = 0;
		if (a < m) {
			
			// Step 1: locate leftmost nonzero column
			for (int j = 0; j < n-1; j++) {
				for (int i = a; i < m; i++) { 
					if (A[i][j] != 0) {
						row = i;
						col = j;
						coeff = A[row][col];
						break; 
					} 
				}
				if (coeff != 0) { break; }
			}
			
			// Step 2: if necessary, interchange rows such that top entry in leftmost nonzero column is nonzero
			if (row != a) {
				float[] temp = A[a];
				A[a] = A[row];
				A[row] = temp;
				row = a;
			}
			
			// Step 3: multiply row "a" w/ appropriate constant such that it has a leading one
			if (coeff != 1) { for (int j = 0; j < n; j++) { A[row][j] = A[row][j]/coeff; } }
			
			// Step 4: subtract each row below "a" by multiple of "a" such that all the elements below the leading one are zero
			if (a < m-1) {	
				for (int i = a+1; i < m; i++) { 
					if (A[i][col] != 0) {
						coeff = A[i][col];
						for (int j = col; j < n; j++) { A[i][j] = A[i][j] - coeff*A[row][j]; }
					}
				}
			}
			
			toREF(A,a+1);
		
		}
		
		return A;
	}
	
	public static void printMatrix(float[][] A) {
		for (int i = 0; i < A.length; i++) {System.out.println(Arrays.toString(A[i])); }
	}

}
