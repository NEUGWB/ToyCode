public class Soduku {
	public int a[][];
	public int t = 0;
	public boolean solved = false;
	public void solve(){
		t++;
		int i=0, j=0;
		for (i = 0; i < 9; i++){
			for (j = 0; j < 9; j++){
				if (a[i][j] == 0){
					for (int k = 1; k <= 9; k++){
						a[i][j] = k;
						if (legal(i, j)){
							solve();
							if (solved)
								return;
						}
					}
					a[i][j] = 0;
					return;
					
				}
			}
		}
		if (i == 9 && j == 9)
			solved = true;
	}
	public boolean legal(int i, int j){
		// line
		{
			int ii = i;
			for (int jj = 0; jj < 9; jj++){
				if (a[ii][jj] != 0 &&
						jj != j && a[i][j] == a[ii][jj]){
					return false;
				}
			}
		}
		//colume
		{
			int jj = j;
			for (int ii = 0; ii < 9; ii++){
				if (a[ii][jj] != 0 &&
						ii != i && a[i][j] == a[ii][jj]){
					return false;
				}
			}
		}
		
		//cube
		{
			int ib = i-i%3, jb = j-j%3;
			int ie = ib+3, je = jb+3;
			for (int ii = ib; ii < ie; ii++){
				for (int jj = jb; jj < je; jj++){
					if (a[ii][jj] != 0 && 
							(ii!=i || jj!=j) && a[i][j] == a[ii][jj]){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void print(){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
		
		System.out.print(t);
	}
	public static void main(String argc[]) {
		int a[][] = {
				{8,0,0,0,0,0,0,0,0},
				{0,0,3,6,0,0,0,0,0},
				{0,7,0,0,9,0,2,0,0},
				{0,5,0,0,0,7,0,0,0},
				{0,0,0,0,4,5,7,0,0},
				{0,0,0,1,0,0,0,3,0},
				{0,0,1,0,0,0,0,6,8},
				{0,0,8,5,0,0,0,1,0},
				{0,9,0,0,0,0,4,0,0}
		};
		
		/*int a[][] = {
				{8,1,2,7,5,3,6,4,9},
				{9,4,3,6,8,2,1,7,5},
				{3,7,5,4,9,1,2,8,3},
				{1,5,4,2,3,7,8,9,6},
				{3,6,9,8,4,5,7,2,1},
				{2,8,7,1,6,9,5,3,4},
				{5,2,1,9,7,4,3,6,8},
				{4,3,8,5,2,6,9,1,7},
				{7,9,6,3,1,8,4,5,2}
		};*/
		
		Soduku s = new Soduku();
		s.a = a;
		s.solve();
		/*s.legal(1, 2);
		for (int i = 0; i< 9; i++)
			for (int j = 0; j < 9; j++){
				if (!s.legal(i, j)){
					System.out.println("error" + i + j);
				}
			}*/
		s.print();
	}
}
