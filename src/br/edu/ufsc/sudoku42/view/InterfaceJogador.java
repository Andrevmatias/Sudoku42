package br.edu.ufsc.sudoku42.view;

import javax.swing.JFrame;

public class InterfaceJogador extends JFrame {

	private static final long serialVersionUID = -4274691600196025428L;
	protected PainelPrincipal painelPrincipal;
	protected BarraDeTarefas barraDeTarefas;
	
	public InterfaceJogador() {
		this.painelPrincipal = new PainelPrincipal();
		this.barraDeTarefas = new BarraDeTarefas();
		
		this.setContentPane(painelPrincipal);
		this.setJMenuBar(barraDeTarefas);
		
		this.pack();
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
}
