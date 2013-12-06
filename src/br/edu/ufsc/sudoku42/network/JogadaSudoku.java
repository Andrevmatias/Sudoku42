package br.edu.ufsc.sudoku42.network;

import br.edu.ufsc.sudoku42.model.Campo;
import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaSudoku implements Jogada {

	private static final long serialVersionUID = -723886612137827007L;

	protected boolean isEnvioDeMatriz;
	protected Campo campo;
	protected long seed;
	protected int tempoRestante;
	
	public JogadaSudoku(){}
	
	public JogadaSudoku(Campo campo){
		this.campo = campo;
	}
	
	public boolean isEnvioDeMatriz() {
		return isEnvioDeMatriz;
	}
	
	public void setEnvioDeMatriz(boolean isEnvioDeMatriz) {
		this.isEnvioDeMatriz = isEnvioDeMatriz;
	}

	public Campo getCampo() {
		return this.campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
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
