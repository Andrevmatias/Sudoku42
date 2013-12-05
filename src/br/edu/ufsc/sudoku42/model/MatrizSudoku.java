package br.edu.ufsc.sudoku42.model;

import java.util.Random;

public class MatrizSudoku {
	protected Campo[][] campos;
	protected int count;

	/**
	 * 
	 * @param matrizBase
	 */
	public void copiarMatrizBase(Campo[][] matrizBase) {
		campos = matrizBase;
	}

	/**
	 * 
	 * @param seed
	 */
	public void embaralhar(long seed) {
		int escolha = this.escolhaDeEmbaralhamentoAleatoria();
		if(escolha == 1){
			this.transpoemMatriz();
		}
		
		else{
			int numero = this.escolhaDeTipoEmbaralhamento();
			
			switch( numero )
			{
			    case 0:
			            this.trocarGruposHorizontalmente();
			            break;
			    
			    case 1:
			            this.trocarGruposVerticalmente();
			            break;
			    
			    case 2:
			            int grupo = this.escolhaDeGrupoAleatorio();
			            
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

	public int escolhaDeEmbaralhamentoAleatoria() {
		Random gerador = new Random();
		int retorno = gerador.nextInt(2);
		return retorno;
	}

	public void transpoemMatriz() {
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

	public int escolhaDeTipoEmbaralhamento() {
		Random gerador = new Random();
		int retorno = gerador.nextInt(3);
		return retorno;
		
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
		Random gerador = new Random();
		int retorno = gerador.nextInt(2);
		return retorno;
	}

	public void trocarColunas() {
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

	public void trocarLinhas() {
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
		Campo c = campos[linha][coluna];
		System.out.println(linha+ ""+ coluna);
		Campo campoOcupado = c.ocuparCampo(jogadorDoTurno);
		return campoOcupado;
	}
}
