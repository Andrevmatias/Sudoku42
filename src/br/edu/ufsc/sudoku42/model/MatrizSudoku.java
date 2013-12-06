package br.edu.ufsc.sudoku42.model;

import java.util.Random;

public class MatrizSudoku {
	protected Campo[][] campos;
	protected int countCamposOcupados;
	protected Random gerador;
	
	public Campo getCampo(int linha, int coluna){
		return campos[linha][coluna];
	}

	public void embaralhar(long seed) {
		int escolha = this.escolhaDeEmbaralhamentoAleatoria(seed);
		if(escolha == 1){
			this.transporMatriz();
		}
		
		else{
			int numero = this.escolhaDeTipoEmbaralhamento(seed);
			
			switch( numero )
			{
			    case 0:
			            this.trocarGruposHorizontalmente();
			            break;
			    
			    case 1:
			            this.trocarGruposVerticalmente();
			            break;
			    
			    case 2:
			            int grupo = this.escolhaDeGrupoAleatorio(seed);
			            
			            if (grupo == 0){
			            	this.trocarColunas();
			            }
			            
			            else{
			            	this.trocarLinhas();
			            }
			            break;
			}
		}
	}

	private int escolhaDeEmbaralhamentoAleatoria(long seed) {
		gerador = new Random(seed);
		int retorno = gerador.nextInt(2);
		return retorno;
	}

	private void transporMatriz() {
		int row, col, last = 0, level = 0, max = 9*(9-1)/2;
		Campo temporario;
		for (int i=0; i < max; i++) {  
		  
		    // evaluate indexes  
		    col = i - (last = ((i - last) % (9 - level) == 0 ? i : last));  
		    row = col + (level = ((i - last) % (9 - level) == 0 ? level+1 : level));  
		  
		    // swap  
		    temporario = campos[row][col];  
		    campos[row][col] = campos[col][row];  
		    campos[col][row] = temporario;  
		}  
	}

	private int escolhaDeTipoEmbaralhamento(long seed) {
		gerador = new Random(seed);
		int retorno = gerador.nextInt(3);
		return retorno;
		
	}

	private void trocarGruposHorizontalmente() {
		System.out.println("hue");
	}

	private void trocarGruposVerticalmente() {
		System.out.println("fazer");
	}

	private int escolhaDeGrupoAleatorio(long seed) {
		gerador = new Random(seed);
		int retorno = gerador.nextInt(2);
		return retorno;
	}

	private void trocarColunas() {
		for(int x = 0; x < 7; x++){
			Random gerador = new Random();
			int col1 = gerador.nextInt(10);
			int col2 = gerador.nextInt(10);
			
			if(col1 != col2){
				for (int count = 0; count < 10; count++) {
					Campo temp = campos[count][col1];
					campos[x][col1] = campos[count][col2];
					campos[x][col2] = temp;
				}
			}
		}
	}

	private void trocarLinhas() {
		for(int x = 0; x < 7; x++){
			Random gerador = new Random();
			int linha1 = gerador.nextInt(10);
			int linha2 = gerador.nextInt(10);
			
			if(linha1 != linha2){
				for (int count = 0; count < 10; count++) {
					Campo temp = campos[count][linha1];
					campos[x][linha1] = campos[count][linha2];
					campos[x][linha2] = temp;
				}
			}
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

	public Campo OcuparPosicaoMeio() throws CampoOcupadoException {
		countCamposOcupados = 1;
		System.out.println(countCamposOcupados);
		Campo c = campos[4][4];
		c.ocupar(null);
		return c;
	}
}
