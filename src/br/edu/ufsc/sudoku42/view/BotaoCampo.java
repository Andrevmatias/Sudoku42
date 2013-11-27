package br.edu.ufsc.sudoku42.view;

import javax.swing.JButton;

public class BotaoCampo extends JButton{
	int i;
	int j;
	
	public BotaoCampo(int x, int y) {
		super(" ");
		this.i = x;
		this.j = y;
	}
	
	public int getI(){
		return this.i;
	}
	
	public int getJ(){
		return this.j;
	}
}
