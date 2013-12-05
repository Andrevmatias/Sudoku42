package br.edu.ufsc.sudoku42.model;

public class Campo {
	protected short valor;
	protected boolean revelado;
	protected Jogador jogador;
	
	
	public Campo(short i) {
		valor = i;
		revelado = false;
		jogador = null;
	}

	public void mudarDeEstado() {
		// TODO - implement Campo.mudarDeEstado
		throw new UnsupportedOperationException();
	}

	public int getValor() {
		return valor;
	}

	public void enviarMensagemOcupada() {
		// TODO - implement Campo.enviarMensagemOcupada
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jogador
	 */
	public Campo ocuparCampo(Jogador jogador) {
		Campo retorno;
		
		if(revelado == false){
			this.jogador = jogador;
			revelado = true;
			retorno = this;
		}
		
		else{
			retorno = null;
		}
		
		return retorno;
		
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}

	/**
	 * 
	 * @param jogador
	 */
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

}

