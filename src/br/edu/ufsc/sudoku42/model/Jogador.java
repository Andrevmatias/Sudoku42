package br.edu.ufsc.sudoku42.model;

public class Jogador {
	protected String nome;
	protected int cor;
	protected int segundosRestantes;
	protected int pontuacao;
	protected Tabuleiro tabuleiro;

	public Jogador(String nome2) {
		nome = nome2;
		pontuacao = 0;
	}

	/**
	 * 
	 * @param r
	 */
	public void setPotuacao(int r) {
		pontuacao += r;
	}

	public void zerarPotuacao() {
		pontuacao = 0;
	}

	public void pararRelogio() {
		// TODO - implement Jogador.pararRelogio
		throw new UnsupportedOperationException();
	}
	
	public void dispararRelogio(){
		//TODO este método
	}
	

}
