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
	protected InterfaceNetgames interfaceRede;
	protected InterfaceJogador interfaceJogador;
	protected MatrizSudoku matrizSudoku;

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
		 this.jogadorDoTurno = jogador;
	}


	public void descartarJogadores() {
		jogadorLocal = null;
		jogadorRemoto = null;

	}

	public void ocuparPosicaoDoMeio() throws NetworkException {
		try {
			this.ocuparPosicao(4, 4);
		} catch (CampoOcupadoException e) {
			throw new RuntimeException("Tentativa de ocupar campo do meio já ocupado");
		}
	}

	public void dispararRelogioAtual() {
		if(jogadorDoTurno == jogadorLocal)
			interfaceJogador.dispararRelogioJogadorLocal(jogadorDoTurno.getSegundosRestantes());
		else
			interfaceJogador.dispararRelogioJogadorRemoto(jogadorDoTurno.getSegundosRestantes());
	}

	public void ocuparPosicao(int linha, int coluna) throws NetworkException, CampoOcupadoException {
		//TODO: Permitir apenas após conectado e com o jogo iniciado
		Campo campoMatriz = matrizSudoku.ocuparPosicaoMatriz(linha, coluna, jogadorDoTurno);
		this.tratarLance(campoMatriz);
	}

	public boolean verificarTabuleiroCompletamenteRevelado() {
		boolean retorno = false;
		int i = matrizSudoku.getCountCamposOcupados();
		if(i == 81){
			retorno = true;
		}
		return retorno;
	}

	public void pararRelogioAtual() {
		int segundosRestantes;
		if(jogadorDoTurno == jogadorLocal)
			segundosRestantes = interfaceJogador.pararRelogioJogadorLocal();
		else
			segundosRestantes = interfaceJogador.pararRelogioJogadorRemoto();
		jogadorDoTurno.setSegundosRestantes(segundosRestantes);
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
		JogadaSudoku jogada = new JogadaSudoku();
		jogada.setEnvioDeMatriz(true);
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
		JogadaSudoku jogadaSu = new JogadaSudoku();
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
		matrizSudoku.limpar();
		this.descartarJogadores();
		interfaceJogador.finalizarPartida();
	}

	public void notificarFinalizacaoInesperada() {
		interfaceJogador.notificarErro("A partida foi encerrada. Pode ter ocorrido um problema de conexão.");
	}

	public void notificarMensagemServidor(String msg) {
		interfaceJogador.notificarMensagemServidor(msg);
	}

	public void criarNovaMatriz(long seed) {
		matrizSudoku = MatrizBase.getCopia();
		matrizSudoku.embaralhar(seed);
	}

	public void tratarLance(Campo campo) throws NetworkException {
		int pontos = campo.getValor();
		getJogadorDoTurno().addPotuacao(pontos);
		temVencedor = this.verificarTabuleiroCompletamenteRevelado();
		JogadaSudoku jogada = this.criarJogada(campo);
		
		if(getJogadorDoTurno() == jogadorLocal){
			interfaceRede.enviarJogada(jogada);
		}
		
		if(!temVencedor){
			this.trocarDeJogador();
		}
		
		else{
			interfaceJogador.notificarVencedor(jogadorLocal.getNome());
			this.encerrarPartida();
		}
		
	}

	public void atualizarInterface(JogadaSudoku jogada) {

		//TODO: Implementar
	}

	public void sincronizarTempoRestanteJogadorAtual(int tempoRestante) {
		jogadorDoTurno.setSegundosRestantes(tempoRestante);
	}
	
	public Jogador getJogadorLocal() {
		return this.jogadorLocal;
	}

	public void setJogadorLocal(Jogador jogadorLocal) {
		this.jogadorLocal = jogadorLocal;
	}

	public Jogador getJogadorRemoto() {
		return this.jogadorRemoto;
	}

	public void setJogadorRemoto(Jogador jogadorRemoto) {
		this.jogadorRemoto = jogadorRemoto;
	}


	public void notificarConexaoPerdida() {
		interfaceJogador.notificarErro("A conexão com o servidor foi perdida");
	}

	public void notificarPartidaNaoIniciada() {
		interfaceJogador.notificarErro("A partida não foi iniciada. O tempo de início pode ter sido excedido.");
	}

	public void setInterfaceRede(InterfaceNetgames interfaceRede) {
		this.interfaceRede = interfaceRede;
	}

	public void setInterfaceJogador(InterfaceJogador interfaceJogador) {
		this.interfaceJogador = interfaceJogador;
	}


	public Jogador getJogadorDoTurno() {
		return jogadorDoTurno;
	}
}
