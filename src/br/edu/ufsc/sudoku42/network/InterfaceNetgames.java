package br.edu.ufsc.sudoku42.network;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;

public class InterfaceNetgames implements OuvidorProxy {

	private static final long serialVersionUID = -8601889122587006247L;
	
	protected Proxy proxy;
	
	public InterfaceNetgames(){
		initProxy();
	}

	private void initProxy() {
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub

	}

	private void receberJogada(JogadaSudoku jogada) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void receberJogada(Jogada jogada) {
		receberJogada((JogadaSudoku)jogada);
	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

}
