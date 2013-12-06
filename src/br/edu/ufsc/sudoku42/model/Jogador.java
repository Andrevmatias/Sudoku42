package br.edu.ufsc.sudoku42.model;

public class Jogador {
	protected String nome;
	protected int cor;
	protected int segundosRestantes;
	protected int pontuacao;
	protected Tabuleiro tabuleiro;

	public Jogador(String nome) {
		this.nome = nome;
		pontuacao = 0;
		segundosRestantes = 900;
	}

	public void addPotuacao(int pontos) {
		pontuacao += pontos;
	}

	public void zerarPotuacao() {
		pontuacao = 0;
	}

	public int getSegundosRestantes() {
		return segundosRestantes;
	}

	public void setSegundosRestantes(int segundosRestantes) {
		this.segundosRestantes = segundosRestantes;
	}

	public String getNome() {
		return this.nome;
	}
}
