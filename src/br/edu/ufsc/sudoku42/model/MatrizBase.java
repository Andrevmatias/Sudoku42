package br.edu.ufsc.sudoku42.model;

public class MatrizBase {
	Campo [][] matrizBase;
	
	public Campo[][] getCopia() {
		if(matrizBase == null){
			this.criarMatrizBase();
		}
		
		return matrizBase;
	}

	public void criarMatrizBase() {
		//Peguei esse algoritmo lá na wikipedia, ele gera um sudoku bem básico, mas daí vamos embaralha-lo né. Porém aqui
		// ele ta populando com inteiros, e não campos.
		final int n = 3;
		matrizBase = new Campo[n*n][n*n];
		for (short i = 0; i < n*n; i++)
		        for (short j = 0; j < n*n; j++)
		                matrizBase[i][j] = new Campo((short) ((i*n + i/n + j) % (n*n) + 1) );
		              
	}
}
