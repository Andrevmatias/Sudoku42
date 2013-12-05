package br.edu.ufsc.sudoku42.model;

public class MatrizBase {
	
	
	public void getCopia() {
		// TODO - implement MatrizBase.getCopia
		throw new UnsupportedOperationException();
	}

	public void criarMatrizBase() {
		//Peguei esse algoritmo lá na wikipedia, ele gera um sudoku bem básico, mas daí vamos embaralha-lo né. Porém aqui
		// ele ta populando com inteiros, e não campos.
		final int n = 3;
		final int[][] field = new int[n*n][n*n];
		for (int i = 0; i < n*n; i++)
		        for (int j = 0; j < n*n; j++)
		                field[i][j] = (i*n + i/n + j) % (n*n) + 1;
	}
}
