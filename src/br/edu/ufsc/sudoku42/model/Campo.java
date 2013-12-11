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

	public short getValor() {
		return valor;
	}

	public void ocupar(Jogador jogador) throws CampoOcupadoException {
		if(revelado == false){
			this.jogador = jogador;
			revelado = true;
		}
		else{
			throw new CampoOcupadoException("Este campo já foi ocupado");
		}
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}

	public void setRevelado(boolean revelado) {
		this.revelado = revelado;
	}
	
	public boolean isRevelado(){
		return this.revelado;
	}

}

