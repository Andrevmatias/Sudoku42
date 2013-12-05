package br.edu.ufsc.sudoku42;

import br.edu.ufsc.sudoku42.model.Tabuleiro;
import br.edu.ufsc.sudoku42.network.InterfaceNetgames;
import br.edu.ufsc.sudoku42.view.InterfaceJogador;

public class Main {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		InterfaceJogador janela = new InterfaceJogador(tabuleiro);
		InterfaceNetgames rede = new InterfaceNetgames(tabuleiro);
		
		tabuleiro.setInterfaceJogador(janela);
		tabuleiro.setInterfaceRede(rede);
		
		janela.setVisible(true);
	}

}
