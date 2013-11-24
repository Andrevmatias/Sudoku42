package br.edu.ufsc.sudoku42.model;

import br.edu.ufsc.sudoku42.network.JogadaSudoku;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Tabuleiro {

	protected boolean jogoEmAndamento;
	protected Jogador jogadorDoTurno;
	protected boolean conectado;
	protected boolean temVencedor;

	public void alterarEstadoDeJogo() {
		// TODO - implement Tabuleiro.alterarEstadoDeJogo
		throw new UnsupportedOperationException();
	}

	public void trocarDeJogador() {
		// TODO - implement Tabuleiro.trocarDeJogador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 */
	public void criarJogador(String nome) {
		// TODO - implement Tabuleiro.criarJogador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jogador
	 */
	public void definirPrimeiro(Jogador jogador) {
		// TODO - implement Tabuleiro.definirPrimeiro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param seed
	 */
	public void criarNovaMatriz(int seed) {
		// TODO - implement Tabuleiro.criarNovaMatriz
		throw new UnsupportedOperationException();
	}

	public void descartarJogadores() {
		// TODO - implement Tabuleiro.descartarJogadores
		throw new UnsupportedOperationException();
	}

	public void ocuparPosicaoDoMeio() {
		// TODO - implement Tabuleiro.ocuparPosicaoDoMeio
		throw new UnsupportedOperationException();
	}

	public void dispararRelogioAtual() {
		// TODO - implement Tabuleiro.dispararRelogioAtual
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public void ocuparPosicao(int linha, int coluna) {
		// TODO - implement Tabuleiro.ocuparPosicao
		throw new UnsupportedOperationException();
	}

	public boolean verificarTabuleiroCompletamenteRevelado() {
		// TODO - implement Tabuleiro.verificarTabuleiroCompletamenteRevelado
		throw new UnsupportedOperationException();
	}

	public void pararRelogioAtual() {
		// TODO - implement Tabuleiro.pararRelogioAtual
		throw new UnsupportedOperationException();
	}

	public void mudarJogadorAtual() {
		// TODO - implement Tabuleiro.mudarJogadorAtual
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jogada
	 */
	public void atualizarInterface(Jogada jogada) {
		// TODO - implement Tabuleiro.atualizarInterface
		throw new UnsupportedOperationException();
	}

	public void solicitarInicioDePartida() {
		// TODO - implement Tabuleiro.solicitarInicioDePartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param isSolicitante
	 */
	public void iniciarPartida(boolean isSolicitante) {
		// TODO - implement Tabuleiro.iniciarPartida
		throw new UnsupportedOperationException();
	}

	public void enviarMatriz() {
		// TODO - implement Tabuleiro.enviarMatriz
		throw new UnsupportedOperationException();
	}

	public boolean isConectado() {
		return this.conectado;
	}

	public void isPartidaEmAndamento() {
		// TODO - implement Tabuleiro.isPartidaEmAndamento
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campo
	 */
	public JogadaSudoku criarJogada(Campo campo) {
		// TODO - implement Tabuleiro.criarJogada
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 */
	public void conectar(String nome) {
		// TODO - implement Tabuleiro.conectar
		throw new UnsupportedOperationException();
	}

	public void desconectar() {
		// TODO - implement Tabuleiro.desconectar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numJog
	 */
	public void iniciarPartida(int numJog) {
		// TODO - implement Tabuleiro.iniciarPartida
		throw new UnsupportedOperationException();
	}
	
	public void iniciarNovaPartida(boolean isSolicitante) {
		// TODO Auto-generated method stub
		
	}

	public void encerrarPartida() {
		// TODO Auto-generated method stub
		
	}

	public void notificarFinalizacaoInesperada() {
		// TODO Auto-generated method stub
		
	}

	public void notificarMensagemServidor(String msg) {
		// TODO Auto-generated method stub
		
	}

	public void criarNovaMatriz(long seed) {
		// TODO Auto-generated method stub
		
	}

	public void tratarLance(Campo campo) {
		// TODO Auto-generated method stub
		
	}

	public void atualizarInterface(JogadaSudoku jogada) {
		// TODO Auto-generated method stub
		
	}

	public void sincronizarTempoRestanteJogadorAtual(long tempoRestante) {
		// TODO Auto-generated method stub
		
	}

	public void notificarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	public void notificarPartidaNaoIniciada() {
		// TODO Auto-generated method stub
		
	}

}
