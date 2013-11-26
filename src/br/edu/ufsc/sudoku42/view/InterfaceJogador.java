package br.edu.ufsc.sudoku42.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfaceJogador extends JFrame {

	private static final long serialVersionUID = -4274691600196025428L;
	protected PainelPrincipal painelPrincipal = new PainelPrincipal();
	
	public InterfaceJogador() {
		this.add(painelPrincipal);
		this.pack();
	}
}
