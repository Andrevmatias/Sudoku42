package br.edu.ufsc.sudoku42.model;

import java.awt.Color;
import java.util.Random;

import javax.swing.UIManager;

import br.edu.ufsc.sudoku42.network.InterfaceNetgames;
import br.edu.ufsc.sudoku42.network.JogadaSudoku;
import br.edu.ufsc.sudoku42.network.NetworkException;
import br.edu.ufsc.sudoku42.view.InterfaceJogador;

public class Tabuleiro {

	protected static final Color COR_JOGADOR_LOCAL = new Color(50,153,204);
	protected static final Color COR_JOGADOR_REMOTO = new Color(255,165,0);
	
	protected boolean partidaEmAndamento;
	protected Jogador jogadorDoTurno;
	protected boolean conectado;
	protected boolean tabuleiroCompletamenteRevelado;
	protected boolean isSolicitante;
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

	public void trocarDeJogador() {
		this.pararRelogioAtual();
		this.mudarJogadorAtual();
		this.dispararRelogioAtual();
	}

	public void definirPrimeiro(Jogador jogador) {
		 this.jogadorDoTurno = jogador;
		 if(this.jogadorLocal == this.jogadorDoTurno)
			 this.interfaceJogador.desbloquearCampos();
	}

	public void descartarJogadores() {
		jogadorLocal = null;
		jogadorRemoto = null;
	}

	public void ocuparPosicaoDoMeio() throws NetworkException {
		try {
			Campo campoMatriz = matrizSudoku.ocuparPosicaoMeio();
			System.out.println(campoMatriz.getValor());
			interfaceJogador.ocuparCampo(4, 4, campoMatriz.getValor(), UIManager.getColor("Button.background"));
		} catch (CampoOcupadoException e) {
			throw new RuntimeException("Tentativa de ocupar campo do meio já ocupado");
		}
	}

	public void pararRelogioAtual() {
		int segundosRestantes;
		if(jogadorDoTurno == jogadorLocal)
			segundosRestantes = interfaceJogador.pararRelogioJogadorLocal();
		else
			segundosRestantes = interfaceJogador.pararRelogioJogadorRemoto();
		jogadorDoTurno.setSegundosRestantes(segundosRestantes);
	}
	
	public void dispararRelogioAtual() {
		if(jogadorDoTurno == jogadorLocal)
			interfaceJogador.dispararRelogioJogadorLocal(jogadorDoTurno.getSegundosRestantes());
		else
			interfaceJogador.dispararRelogioJogadorRemoto(jogadorDoTurno.getSegundosRestantes());
	}

	public void ocuparPosicao(int linha, int coluna) throws NetworkException, CampoOcupadoException {
		this.tratarLance(new JogadaSudoku(linha, coluna));
	}

	public boolean verificarTabuleiroCompletamenteRevelado() {
		boolean retorno = false;
		int i = matrizSudoku.getCountCamposOcupados();
		if(i == 81){
			retorno = true;
		}
		return retorno;
	}

	private void mudarJogadorAtual() {
		if(jogadorDoTurno == jogadorLocal){
			jogadorDoTurno = jogadorRemoto;
			interfaceJogador.bloquearCampos();
		}
		else{
			jogadorDoTurno = jogadorLocal;
			interfaceJogador.desbloquearCampos();
		}
	}

	public void solicitarInicioDePartida() throws NetworkException {
		jogadorLocal.setSolicitante(true);
		interfaceRede.iniciarPartida();
	}

	public void iniciarPartidaRecebida() throws NetworkException {
		String nome = interfaceRede.getNomeJogadorRemoto();
		jogadorRemoto = new Jogador(nome);
		interfaceJogador.configurarPainelJogadorRemoto(nome, COR_JOGADOR_REMOTO);
		
		if(jogadorLocal.isSolicitante()){
			definirPrimeiro(jogadorLocal);
			Random gerador = new Random();
			long seed = gerador.nextLong();
			this.criarNovaMatriz(seed);
			this.enviarMatriz(seed);
			this.dispararRelogioAtual();
			this.ocuparPosicaoDoMeio();
			partidaEmAndamento = true;
		}
		else{
			definirPrimeiro(jogadorRemoto);
		}
		
		interfaceJogador.mudarParaModoPartidaEmAndamento();
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
		return partidaEmAndamento;
	}

	public JogadaSudoku criarJogada(int linha, int coluna) {
		JogadaSudoku jogadaSudoku = new JogadaSudoku(linha, coluna);
		return jogadaSudoku;
	}

	public void conectar(String nome) throws NetworkException {
		this.interfaceRede.conectar(nome);
		this.jogadorLocal = new Jogador(nome);
		interfaceJogador.configurarPainelJogadorLocal(nome, COR_JOGADOR_LOCAL);
		this.conectado = true;
	}

	public void desconectar() throws NetworkException {
		this.descartarJogadores();
		this.conectado = false;
		this.interfaceRede.desconectar();
	}

	public void encerrarPartida() {
		jogadorLocal.zerarPotuacao();
		jogadorRemoto.zerarPotuacao();
		matrizSudoku = null;
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
		try{
			matrizSudoku.embaralhar(seed);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void tratarLance(JogadaSudoku jogada) throws NetworkException, CampoOcupadoException {
		if(jogada.getTempoRestante() != -1)
			sincronizarTempoRestanteJogadorAtual(jogada.getTempoRestante());
		
		Campo campo = matrizSudoku.ocuparPosicaoMatriz(jogada.getLinha(), jogada.getColuna(), jogadorDoTurno);
		int pontos = campo.getValor();
		jogadorDoTurno.addPotuacao(pontos);
		tabuleiroCompletamenteRevelado = this.verificarTabuleiroCompletamenteRevelado();
		
		if(jogadorDoTurno == jogadorLocal){
			interfaceRede.enviarJogada(jogada);
		}
		
		atualizarInterface(jogada);
		
		if(!tabuleiroCompletamenteRevelado){
			this.trocarDeJogador();
		}
		else{
			Jogador vencedor = getJogadorComMaiorPontuacao();
			if(vencedor != null)
				interfaceJogador.notificarVencedor(vencedor.getNome());
			else
				interfaceJogador.notificarEmpate();
			this.encerrarPartida();
		}
	}

	private Jogador getJogadorComMaiorPontuacao() {
		if(jogadorLocal.getPontuacao() == jogadorRemoto.getPontuacao())
			return null;
		return jogadorLocal.getPontuacao() > jogadorRemoto.getPontuacao() ? jogadorLocal : jogadorRemoto;
	}

	public void atualizarInterface(JogadaSudoku jogada) {
		Campo campo = matrizSudoku.getCampo(jogada.getLinha(), jogada.getColuna());
		Color cor = jogadorLocal == jogadorDoTurno ? COR_JOGADOR_LOCAL : COR_JOGADOR_REMOTO;
		interfaceJogador.ocuparCampo(jogada.getLinha(), jogada.getColuna(), campo.getValor(), cor);
		if(jogadorLocal == jogadorDoTurno){
			interfaceJogador.atualizarPontuacaoJogadorLocal(jogadorDoTurno.getPontuacao());
		}
		else{
			interfaceJogador.atualizarPontuacaoJogadorRemoto(jogadorDoTurno.getPontuacao());
		}
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
