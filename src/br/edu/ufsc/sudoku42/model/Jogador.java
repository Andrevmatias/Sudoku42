package br.edu.ufsc.sudoku42.model;

import java.awt.Color;

import br.edu.ufsc.sudoku42.configuration.Configurations;

public class Jogador {
	protected String nome;
	protected Color cor;
	protected int segundosRestantes;
	protected int pontuacao;
	protected Tabuleiro tabuleiro;
	protected boolean solicitante;

	public Jogador(String nome) {
		this.nome = nome;
		this.pontuacao = 0;
		this.segundosRestantes = Configurations.getTempoJogador();
		this.solicitante = false;
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

	public boolean isSolicitante() {
		return solicitante;
	}

	public void setSolicitante(boolean solicitante) {
		this.solicitante = solicitante;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}
	
	public Color getCor() {
		return cor;
	}
}
