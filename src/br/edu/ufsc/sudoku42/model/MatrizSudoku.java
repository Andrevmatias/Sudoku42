package br.edu.ufsc.sudoku42.model;

import java.util.Random;

public class MatrizSudoku {
	private static final int NUMERO_EMBARALHAMENTOS = 15;
	protected Campo[][] campos;
	protected int countCamposOcupados;
	
	public Campo getCampo(int linha, int coluna){
		return campos[linha][coluna];
	}

	public void embaralhar(long seed) {
		Random random = new Random(seed);
		for(int i = 0; i < NUMERO_EMBARALHAMENTOS; i++){
			int indicadorTipoEmbaralhamento = random.nextInt(4);
			if(indicadorTipoEmbaralhamento == 0){//Transpor
				this.transporMatriz();
			}
			else{
				int numero1 = random.nextInt(3);
				int numero2 = random.nextInt(3);
				if(indicadorTipoEmbaralhamento == 1){//Troca grupos vertical
					trocarGruposHorizontalmente(numero1, numero2);
				}
				else if(indicadorTipoEmbaralhamento == 2){//Troca grupos horizontal
					trocarGruposVerticalmente(numero1, numero2);
				}
				else{
					int grupo = random.nextInt(3);
					if(indicadorTipoEmbaralhamento == 3)//Troca colunas de grupo
						trocarColunas(grupo, numero1, numero2);
					else//Troca linhas de grupo
						trocarLinhas(grupo, numero1, numero2);
				}
			}
		}
	}

	private void transporMatriz() {
		int row, col, last = 0, level = 0, max = 9*(9-1)/2;
		Campo temporario;
		for (int i=0; i < max; i++) {  
		    col = i - (last = ((i - last) % (9 - level) == 0 ? i : last));  
		    row = col + (level = ((i - last) % (9 - level) == 0 ? level+1 : level));  
		    temporario = campos[row][col];  
		    campos[row][col] = campos[col][row];  
		    campos[col][row] = temporario;  
		}  
	}

	private void trocarGruposHorizontalmente(int grupo1, int grupo2) {
		for(int i = grupo1 * 3; i < (grupo1 + 1) * 3; i++){
			for(int j = 0; j < 9; j++){
				Campo temp = campos[i][j];
				campos[i][j] = campos[(grupo2 * 3) + (i % 3)][j];
				campos[(grupo2 * 3) + (i % 3)][j] = temp;
			}
		}
	}

	private void trocarGruposVerticalmente(int grupo1, int grupo2) {
		for(int i = grupo1 * 3; i < (grupo1 + 1) * 3; i++){
			for(int j = 0; j < 9; j++){
				Campo temp = campos[j][i];
				campos[j][i] = campos[j][(grupo2 * 3) + (i % 3)];
				campos[j][(grupo2 * 3) + (i % 3)] = temp;
			}
		}
	}

	private void trocarColunas(int grupo, int coluna1, int coluna2) {
		for(int j = 0; j < 9; j++){
			Campo temp = campos[(grupo * 3) + coluna1][j];
			campos[(grupo * 3) + coluna1][j] = campos[(grupo * 3) + coluna2][j];
			campos[(grupo * 3) + coluna2][j] = temp;
		}
	}

	private void trocarLinhas(int grupo, int linha1, int linha2) {
		for(int i = 0; i < 9; i++){
			Campo temp = campos[i][(grupo * 3) + linha1];
			campos[i][(grupo * 3) + linha1] = campos[i][(grupo * 3) + linha2];
			campos[i][(grupo * 3) + linha2] = temp;
		}
	}

	public void limpar() {
		campos = null;
		countCamposOcupados = 0;
	}
	
	public int getCountCamposOcupados(){
		return countCamposOcupados;
	}

	public Campo ocuparPosicaoMatriz(int linha, int coluna, Jogador jogadorDoTurno) throws CampoOcupadoException {
		Campo c = campos[linha][coluna];
		System.out.println(c.getValor());
		c.ocupar(jogadorDoTurno);
		countCamposOcupados++;

		return c;
	}

	public Campo ocuparPosicaoMeio() throws CampoOcupadoException {
		countCamposOcupados++;
		Campo c = campos[4][4];
		return c;
	}
}
