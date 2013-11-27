package br.edu.ufsc.sudoku42.view;

import javax.swing.JFrame;

public class InterfaceJogador extends JFrame {

	private static final long serialVersionUID = -4274691600196025428L;
	protected PainelPrincipal painelPrincipal = new PainelPrincipal();
	
	public InterfaceJogador() {
		this.setContentPane(painelPrincipal);
		
		
		this.pack();
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
}
