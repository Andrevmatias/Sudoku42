package br.edu.ufsc.sudoku42.model;

public class MatrizSudoku {
	protected Campo[][] campos;
	protected int count;

	/**
	 * 
	 * @param matrizBase
	 */
	public void copiarMatrizBase(MatrizBase matrizBase) {
		// TODO - implement MatrizSudoku.copiarMatrizBase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param seed
	 */
	public void embaralhar(int seed) {
		// TODO - implement MatrizSudoku.embaralhar
		throw new UnsupportedOperationException();
	}

	public int escolhaDeEmbaralhamentoAleatoria() {
		// TODO - implement MatrizSudoku.escolhaDeEmbaralhamentoAleatoria
		throw new UnsupportedOperationException();
	}

	public void transpoemMatriz() {
		// TODO - implement MatrizSudoku.transpoemMatriz
		throw new UnsupportedOperationException();
	}

	public int escolhaDeTipoEmbaralhamento() {
		// TODO - implement MatrizSudoku.escolhaDeTipoEmbaralhamento
		throw new UnsupportedOperationException();
	}

	public void trocarGruposHorizontalmente() {
		// TODO - implement MatrizSudoku.trocarGruposHorizontalmente
		throw new UnsupportedOperationException();
	}

	public void trocarGruposVerticalmente() {
		// TODO - implement MatrizSudoku.trocarGruposVerticalmente
		throw new UnsupportedOperationException();
	}

	public int escolhaDeGrupoAleatorio() {
		// TODO - implement MatrizSudoku.escolhaDeGrupoAleatorio
		throw new UnsupportedOperationException();
	}

	public void trocarColunas() {
		// TODO - implement MatrizSudoku.trocarColunas
		throw new UnsupportedOperationException();
	}

	public void trocarLinhas() {
		// TODO - implement MatrizSudoku.trocarLinhas
		throw new UnsupportedOperationException();
	}

	public void limparMatriz() {
		campos = null;
		count = 0;
	}
	
	public void incrementaCount(){
		count++;
	}
	
	public int getCount(){
		return count;
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public Campo ocuparPosicaoMatriz(int linha, int coluna, Jogador jogadorDoTurno) {
		 //TODO - implement MatrizSudoku.ocuparPosicaoMatriz
		Campo campoOcupado = campos[linha][coluna].ocuparCampo(jogadorDoTurno);
		return campoOcupado;
	}
}
