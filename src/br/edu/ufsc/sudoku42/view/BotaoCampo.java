package br.edu.ufsc.sudoku42.view;

import javax.swing.JButton;

public class BotaoCampo extends JButton{
	private static final long serialVersionUID = -6105729263689810268L;

	protected int i;
	protected int j;
	
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
