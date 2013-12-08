package br.edu.ufsc.sudoku42.network;


import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaSudoku implements Jogada {

	private static final long serialVersionUID = -723886612137827007L;

	protected boolean isEnvioDeMatriz;
	protected int i;
	protected int j;
	protected long seed;
	protected int tempoRestante = -1;
	
	public JogadaSudoku(){}
	
	public JogadaSudoku(int linha, int coluna){
		i = linha;
		j = coluna;
	}
	
	public boolean isEnvioDeMatriz() {
		return isEnvioDeMatriz;
	}
	
	public void setEnvioDeMatriz(boolean isEnvioDeMatriz) {
		this.isEnvioDeMatriz = isEnvioDeMatriz;
	}

	public int getLinha() {
		return this.i;
	}

	public void setLinha(int linha) {
		this.i = linha;
	}
	
	public int getColuna() {
		return this.j;
	}

	public void setColuna(int coluna) {
		this.j = coluna;
	}

	public long getSeed() {
		return this.seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public int getTempoRestante() {
		return this.tempoRestante;
	}

}
