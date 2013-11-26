package br.edu.ufsc.sudoku42.view;

import javax.swing.JButton;

public class Campo extends JButton{
	int x;
	int y;
	
	public Campo(int x, int y) {
		super(" ");
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}
