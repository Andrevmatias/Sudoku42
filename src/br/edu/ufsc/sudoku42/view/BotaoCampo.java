package br.edu.ufsc.sudoku42.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.UIManager;

public class BotaoCampo extends JButton{
	private static final long serialVersionUID = -6105729263689810268L;

	protected int i;
	protected int j;
	
	public BotaoCampo(int x, int y) {
		super(" ");
		this.i = x;
		this.j = y;
		this.setText(" ");
	}
	
	public int getI(){
		return this.i;
	}
	
	public int getJ(){
		return this.j;
	}
	
	public void ocupar(int valor, Color cor){
		this.setText(String.valueOf(valor));
		this.setBackground(cor);
	}

	public void limpar() {
		this.setText("");
		this.setBackground(UIManager.getColor("Button.background"));
	}
}
