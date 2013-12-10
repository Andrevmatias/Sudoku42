package br.edu.ufsc.sudoku42.model;

import java.awt.Color;
import java.util.Random;

import javax.swing.UIManager;

import br.edu.ufsc.sudoku42.network.InterfaceNetgames;
import br.edu.ufsc.sudoku42.network.JogadaSudoku;
import br.edu.ufsc.sudoku42.network.NetworkException;
import br.edu.ufsc.sudoku42.view.InterfaceJogador;

public class Tabuleiro {

	protected static final Color COR_JOGADOR_1 = new Color(50,153,204);
	protected static final Color COR_JOGADOR_2 = new Color(255,165,0);
	
	protected boolean partidaEmAndamento;
	protected Jogador jogadorDoTurno;
	protected boolean conectado;
	protected boolean tabuleiroCompletamenteRevelado;
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	protected InterfaceNetgames interfaceRede;
	protected InterfaceJogador interfaceJogador;
	protected MatrizSudoku matrizSudoku;

	public void desistir() throws NetworkException{
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
			throw new RuntimeException("Tentativa de ocupar campo do meio ja ocupado");
		}
	}

	public int pararRelogioAtual() {
		int segundosRestantes;
		if(jogadorDoTurno == jogadorLocal)
			segundosRestantes = interfaceJogador.pararRelogioJogadorLocal();
		else
			segundosRestantes = interfaceJogador.pararRelogioJogadorRemoto();
		return segundosRestantes;
	}
	
	public void dispararRelogioAtual() {
		if(jogadorDoTurno == jogadorLocal)
			interfaceJogador.dispararRelogioJogadorLocal(jogadorDoTurno.getSegundosRestantes());
		else
			interfaceJogador.dispararRelogioJogadorRemoto(jogadorDoTurno.getSegundosRestantes());
	}

	public void ocuparPosicao(int linha, int coluna) throws NetworkException, CampoOcupadoException {
		this.tratarLance(new JogadaSudoku(linha, coluna, pararRelogioAtual()));
	}
	
	public void tratarTempoEsgotado() throws NetworkException{
		try {
			if(jogadorDoTurno == jogadorLocal)
				this.tratarLance(new JogadaSudoku(-1, -1, 0));
		} catch (CampoOcupadoException e) {
			throw new RuntimeException("Campo inválido tratado");
		}
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
		jogadorLocal.setCor(COR_JOGADOR_1);
		interfaceJogador.configurarPainelJogadorLocal(jogadorLocal.getNome(), jogadorLocal.getCor());
		interfaceRede.iniciarPartida();
	}

	public void iniciarPartidaRecebida() throws NetworkException {
		String nome = interfaceRede.getNomeJogadorRemoto();
		jogadorRemoto = new Jogador(nome);
		
		if(jogadorLocal.isSolicitante()){
			jogadorRemoto.setCor(COR_JOGADOR_2);
		}else{
			jogadorRemoto.setCor(COR_JOGADOR_1);
			jogadorLocal.setCor(COR_JOGADOR_2);
			jogadorRemoto.setSolicitante(true);
			interfaceJogador.configurarPainelJogadorLocal(jogadorLocal.getNome(), jogadorLocal.getCor());
		}
		
		interfaceJogador.configurarPainelJogadorRemoto(nome, jogadorRemoto.getCor());
		
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

	public void conectar(String nome) throws NetworkException {
		this.interfaceRede.conectar(nome);
		this.jogadorLocal = new Jogador(nome);
		interfaceJogador.configurarPainelJogadorLocal(nome, jogadorLocal.getCor());
		this.conectado = true;
	}

	public void desconectar() throws NetworkException {
		this.descartarJogadores();
		this.conectado = false;
		this.interfaceRede.desconectar();
	}

	public void encerrarPartida() throws NetworkException {
		jogadorLocal.zerarPotuacao();
		jogadorRemoto.zerarPotuacao();
		matrizSudoku = null;
		interfaceJogador.finalizarPartida();
		interfaceRede.desconectar();
	}

	public void notificarFinalizacaoInesperada() {
		interfaceJogador.notificarErro("A partida foi encerrada. Pode ter ocorrido um problema de conexao. Você é o vencedor");
		jogadorLocal.zerarPotuacao();
		jogadorRemoto.zerarPotuacao();
		matrizSudoku = null;
		interfaceJogador.finalizarPartida();
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
		sincronizarTempoRestanteJogadorAtual(jogada.getTempoRestante());
		if(jogadorDoTurno == jogadorLocal){
			interfaceRede.enviarJogada(jogada);
		}
		if(jogada.getTempoRestante() == 0){
			interfaceJogador.notificarTempoEsgotado(jogadorDoTurno.getNome());
			if(jogadorDoTurno == jogadorLocal)
				interfaceJogador.notificarVencedor(jogadorRemoto.getNome());
			else
				interfaceJogador.notificarVencedor(jogadorLocal.getNome());
			this.encerrarPartida();
		}
		else{
			Campo campo = matrizSudoku.ocuparPosicaoMatriz(jogada.getLinha(), jogada.getColuna(), jogadorDoTurno);
			int pontos = campo.getValor();
			jogadorDoTurno.addPotuacao(pontos);
			tabuleiroCompletamenteRevelado = this.verificarTabuleiroCompletamenteRevelado();
			
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
	}

	private Jogador getJogadorComMaiorPontuacao() {
		if(jogadorLocal.getPontuacao() == jogadorRemoto.getPontuacao())
			return null;
		return jogadorLocal.getPontuacao() > jogadorRemoto.getPontuacao() ? jogadorLocal : jogadorRemoto;
	}

	public void atualizarInterface(JogadaSudoku jogada) {
		Campo campo = matrizSudoku.getCampo(jogada.getLinha(), jogada.getColuna());
		interfaceJogador.ocuparCampo(jogada.getLinha(), jogada.getColuna(), campo.getValor(), jogadorDoTurno.getCor());
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
		interfaceJogador.notificarMensagemServidor("Conexão Encerrada");
		this.interfaceJogador.finalizarPartida();
	}

	public void notificarPartidaNaoIniciada() {
		interfaceJogador.notificarErro("A partida nao foi iniciada. O tempo de inicio pode ter sido excedido.");
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
