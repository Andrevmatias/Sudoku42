package br.edu.ufsc.sudoku42.model;

public class MatrizBase extends MatrizSudoku {
	private static MatrizBase matrizBase;
	
	private MatrizBase(Campo[][] campos){
		this.campos = campos;
	}
	
	public static MatrizBase getCopia() {
		if(matrizBase == null){
			matrizBase = criarMatrizBase();
		}
		
		try {
			return (MatrizBase)matrizBase.clone();
		} catch (CloneNotSupportedException e) {
			return matrizBase;
		}
	}

	public static MatrizBase criarMatrizBase() {

		final int n = 3;
		Campo[][] campos = new Campo[n*n][n*n];
		for (short i = 0; i < n*n; i++)
		        for (short j = 0; j < n*n; j++)
		        	campos[i][j] = new Campo((short) ((i*n + i/n + j) % (n*n) + 1) );
		return new MatrizBase(campos);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		final int n = 3;
		Campo[][] campos = new Campo[n*n][n*n];
		for (short i = 0; i < n*n; i++)
	        for (short j = 0; j < n*n; j++)
	        	campos[i][j] = this.campos[i][j];
		return new MatrizBase(campos);
	}
}
