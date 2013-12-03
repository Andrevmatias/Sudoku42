package br.edu.ufsc.sudoku42.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarraDeTarefas extends JMenuBar {
	
	private static final long serialVersionUID = -2313774782441238222L;
	
	protected JMenu menuOpcoes;
	protected JMenuItem itemIniciarPartidas;
	
	protected InterfaceJogador interfaceJogador;
	
	
	public BarraDeTarefas(InterfaceJogador interfaceJogador) {
		this.interfaceJogador = interfaceJogador;
		
		this.menuOpcoes = new JMenu("Op��es");
		
		this.itemIniciarPartidas = new JMenuItem("Conectar");
		this.itemIniciarPartidas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nome = getInterfaceJogador().requisitarNome();
				getInterfaceJogador().conectar(nome);
				
			}
		});
		
		this.menuOpcoes.add(this.itemIniciarPartidas);
		this.add(menuOpcoes);
		
	}
	
	public InterfaceJogador getInterfaceJogador(){
		return this.interfaceJogador;
	}
	

	

}
