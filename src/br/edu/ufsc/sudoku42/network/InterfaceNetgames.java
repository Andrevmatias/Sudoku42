package br.edu.ufsc.sudoku42.network;

import br.edu.ufsc.sudoku42.configuration.Configurations;
import br.edu.ufsc.sudoku42.model.CampoOcupadoException;
import br.edu.ufsc.sudoku42.model.Tabuleiro;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class InterfaceNetgames implements OuvidorProxy {

	private static final long serialVersionUID = -8601889122587006247L;
	
	protected Proxy proxy;
	protected Tabuleiro tabuleiro;
	
	public InterfaceNetgames(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
		initProxy();
	}

	private void initProxy() {
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		try {
			this.tabuleiro.iniciarPartidaRecebida();
		} catch (NetworkException e) {
			throw new RuntimeException("Esta exceção não deve ocorrer");
		}
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		this.tabuleiro.notificarFinalizacaoInesperada();
	}
	
	public void finalizarPartida() throws NetworkException{
		try{
			this.proxy.finalizarPartida();
		} catch (NaoConectadoException e) {
			throw new NetworkException("O jogo precisa estar conectado para finalizar uma partida", e);
		} catch (NaoJogandoException e) {
			throw new NetworkException("Nenhum jogo foi iniciado", e);
		}
	}

	@Override
	public void receberMensagem(String msg) {
		tabuleiro.notificarMensagemServidor(msg);
	}

	private void receberJogada(JogadaSudoku jogada) throws NetworkException, CampoOcupadoException {
		if(jogada.isEnvioDeMatriz()){
			tabuleiro.criarNovaMatriz(jogada.getSeed());
			tabuleiro.ocuparPosicaoDoMeio();
			tabuleiro.dispararRelogioAtual();
		}
		else{
			try {
				this.tabuleiro.tratarLance(jogada);
			} catch (NetworkException e) {
				throw new RuntimeException("Esta exce��o jamais deve ser lan�ada ao se receber uma jogada");
			}
		}
	}
	
	@Override
	public void receberJogada(Jogada jogada) {
		try {
			receberJogada((JogadaSudoku)jogada);
		} catch (NetworkException | CampoOcupadoException e) {
			throw new RuntimeException("Erro de rede ou campo já ocupado", e);
		}
	}

	@Override
	public void tratarConexaoPerdida() {
		tabuleiro.notificarConexaoPerdida();
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		tabuleiro.notificarPartidaNaoIniciada();
	}

	public void enviarJogada(JogadaSudoku jogada) throws NetworkException{
		try {
			this.proxy.enviaJogada(jogada);
		} catch (NaoJogandoException e) {
			throw new NetworkException("Nenhum jogo foi iniciado", e);
		}
	}
	
	public void iniciarPartida() throws NetworkException{
		try {
			this.proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			throw new NetworkException("O jogo precisa estar conectado para iniciar uma nova partida", e);
		}
	}
	
	public void conectar(String nomeJogador) throws NetworkException{
		try {
			String servidor = Configurations.getIpServidor();
			this.proxy.conectar(servidor, nomeJogador);
		} catch (JahConectadoException e) {
			throw new NetworkException("O jogo já foi conectado", e);
		} catch (NaoPossivelConectarException e) {
			throw new NetworkException("Não foi possível conectarr", e);
		} catch (ArquivoMultiplayerException e) {
			throw new NetworkException("Ocorreu um erro ao tentar ler o arquivo de configuração \"jogoMultiPlayer.properties\"", e);
		}
	}
	
	public void desconectar() throws NetworkException{
		try {
			this.proxy.desconectar();
		} catch (NaoConectadoException e) {
			throw new NetworkException("O jogo precisa estar conectado para se desconectar", e);
		}
	}
	
	public String getNomeJogadorRemoto(){
		return this.proxy.obterNomeAdversarios().get(0);
	}
}
