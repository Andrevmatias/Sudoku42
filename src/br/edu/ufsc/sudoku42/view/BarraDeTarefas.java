package br.edu.ufsc.sudoku42.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarraDeTarefas extends JMenuBar {
	protected JMenu menuOpcoes;
	
	protected JMenuItem itemIniciarPartidas;
	
	public BarraDeTarefas() {
		this.menuOpcoes = new JMenu("Opções");
		
		this.itemIniciarPartidas = new JMenuItem("Conectar");
		this.itemIniciarPartidas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: chamar o modelo
				
			}
		});
		
		this.menuOpcoes.add(this.itemIniciarPartidas);
		this.add(menuOpcoes);
		
	}
	
	

}
