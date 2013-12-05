package br.edu.ufsc.sudoku42.model;

import br.edu.ufsc.sudoku42.network.InterfaceNetgames;
import br.edu.ufsc.sudoku42.network.JogadaSudoku;
import br.edu.ufsc.sudoku42.network.NetworkException;
import br.edu.ufsc.sudoku42.view.InterfaceJogador;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Tabuleiro {

	protected boolean jogoEmAndamento;
	protected Jogador jogadorDoTurno;
	protected boolean conectado;
	protected boolean temVencedor;
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	private InterfaceNetgames interfaceRede;
	private InterfaceJogador interfaceJogador;
	protected MatrizSudoku matrizSudoku;


	public void desistir(){
		boolean partidaEmAndamento = this.isPartidaEmAndamento();
		if(partidaEmAndamento == true){
			this.encerrarPartida();
		}
	}
	
	public void alterarEstadoDeJogo() {
		// TODO - implement Tabuleiro.alterarEstadoDeJogo
		throw new UnsupportedOperationException();
	}

	public void trocarDeJogador() {
		this.pararRelogioAtual();
		this.mudarJogadorAtual();
		this.dispararRelogioAtual();
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
		jogadorLocal = null;
		jogadorRemoto = null;

	}

	public void ocuparPosicaoDoMeio() {
		// TODO - implement Tabuleiro.ocuparPosicaoDoMeio
		throw new UnsupportedOperationException();
	}

	public void dispararRelogioAtual() {
		jogadorDoTurno.dispararRelogio();
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 * @throws NetworkException 
	 */
	public void ocuparPosicao(int linha, int coluna) throws NetworkException {
		Campo campoMatriz = matrizSudoku.ocuparPosicaoMatriz(linha, coluna, jogadorDoTurno);
		if(campoMatriz == null){
			throw new UnsupportedOperationException();
		}
		this.tratarLance(campoMatriz);
	}

	public boolean verificarTabuleiroCompletamenteRevelado() {
		boolean retorno = false;
		int i = matrizSudoku.getCount();
		if(i == 81){
			retorno = true;
		}
		
		return retorno;
	}

	public void pararRelogioAtual() {
		jogadorDoTurno.pararRelogio();
	}

	public void mudarJogadorAtual() {
		if(jogadorDoTurno == jogadorLocal){
			jogadorDoTurno = jogadorRemoto;
		}
		else{
			jogadorDoTurno = jogadorLocal;
		}
	}

	/**
	 * 
	 * @param jogada
	 */
	public void atualizarInterface(Jogada jogada) {
		// TODO - implement Tabuleiro.atualizarInterface
		throw new UnsupportedOperationException();
	}

	public void solicitarInicioDePartida() throws NetworkException {
		interfaceRede.iniciarPartida();
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

	public boolean isPartidaEmAndamento() {
		return jogoEmAndamento;

	}

	/**
	 * 
	 * @param campo
	 */
	public JogadaSudoku criarJogada(Campo campo) {
		JogadaSudoku jogadaSu = new JogadaSudoku(campo);
		return jogadaSu;
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
		jogadorLocal.zerarPotuacao();
		jogadorRemoto.zerarPotuacao();
		matrizSudoku.limparMatriz();
		//TODO pararRelogio();
		jogadorLocal.pararRelogio();
		jogadorRemoto.pararRelogio();
		this.descartarJogadores();
		interfaceJogador.finalizarPartida();
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

	public void tratarLance(Campo campo) throws NetworkException {
		int r = campo.getValor();
		jogadorDoTurno.setPotuacao(r);
		matrizSudoku.incrementaCount();
		temVencedor = this.verificarTabuleiroCompletamenteRevelado();
		JogadaSudoku jogada = this.criarJogada(campo);
		
		if(jogadorDoTurno == jogadorLocal){
			interfaceRede.enviarJogada(jogada);
		}
		
		if(!temVencedor){
			this.trocarDeJogador();
		}
		
		else{
			//TODO notificar vencedor
			this.encerrarPartida();
		}
		
	}

	public void atualizarInterface(JogadaSudoku jogada) {
		// TODO Auto-generated method stub
		
	}

	public void sincronizarTempoRestanteJogadorAtual(long tempoRestante) {
		// TODO Auto-generated method stub
		
	}
	
	public Jogador getJogadorLocal() {
		return this.jogadorLocal;
	}

	/**
	 * 
	 * @param jogadorLocal
	 */
	public void setJogadorLocal(Jogador jogadorLocal) {
		this.jogadorLocal = jogadorLocal;
	}

	public Jogador getJogadorRemoto() {
		return this.jogadorRemoto;
	}

	/**
	 * 
	 * @param jogadorRemoto
	 */
	public void setJogadorRemoto(Jogador jogadorRemoto) {
		this.jogadorRemoto = jogadorRemoto;
	}


	public void notificarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	public void notificarPartidaNaoIniciada() {
		// TODO Auto-generated method stub
		
	}

	public void setInterfaceRede(InterfaceNetgames interfaceRede) {
		this.interfaceRede = interfaceRede;
	}

	public void setInterfaceJogador(InterfaceJogador interfaceJogador) {
		this.interfaceJogador = interfaceJogador;
	}

}
