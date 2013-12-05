package br.edu.ufsc.sudoku42.model;

import java.util.Random;

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
	protected MatrizBase matrizBase;


	public void desistir(){
		boolean partidaEmAndamento = this.isPartidaEmAndamento();
		if(partidaEmAndamento == true){
			this.encerrarPartida();
		}
	}
	
	public void alterarEstadoDeJogo() {
		jogoEmAndamento = true;
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
		if(jogadorLocal == null){
			jogadorLocal = new Jogador(nome);
		}
		
		else{
			jogadorRemoto = new Jogador(nome);
		}
	}

	/**
	 * 
	 * @param jogador
	 */
	public void definirPrimeiro(Jogador jogador) {
		jogadorDoTurno = jogador;
	}


	public void descartarJogadores() {
		jogadorLocal = null;
		jogadorRemoto = null;

	}

	public void ocuparPosicaoDoMeio() throws NetworkException {
		this.ocuparPosicao(4, 4);
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
	 * @throws NetworkException 
	 */
	public void iniciarPartidaRecebida(boolean isSolicitante) throws NetworkException {
		String j2 = interfaceRede.getNomeJogadorRemoto();
		if(isSolicitante){
			criarJogador(j2);
		}
		
		else{
			criarJogador(j2);
		}
		
		if(isSolicitante){
			definirPrimeiro(jogadorLocal);
		}
		
		else{
			definirPrimeiro(jogadorRemoto);
		}
		
		if(isSolicitante){
			Random gerador = new Random();
			long seed = gerador.nextLong();
			this.criarNovaMatriz(seed);
			this.enviarMatriz(seed);
			this.alterarEstadoDeJogo();
			this.ocuparPosicaoDoMeio();
			this.dispararRelogioAtual();
		}
	}

	public void enviarMatriz(long seed) throws NetworkException {
		JogadaSudoku jogada = new JogadaSudoku(null);
		//isEnvioDeMatriz vai ser true temporariamente, até eu saber de onde ele vem
		boolean isEnvioDeMatriz = true;
		jogada.setEnvioDeMatriz(isEnvioDeMatriz);
		jogada.setSeed(seed);
		interfaceRede.enviarJogada(jogada);
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
	 * @throws NetworkException 
	 */
	public void conectar(String nome) throws NetworkException {
		interfaceRede.conectar(nome);
		this.criarJogador(nome);
		
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
		Campo[][] copia = matrizBase.getCopia();
		
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
