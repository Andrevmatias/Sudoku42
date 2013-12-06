package br.edu.ufsc.sudoku42.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.edu.ufsc.sudoku42.network.NetworkException;

public class BarraDeTarefas extends JMenuBar {
	
	private static final long serialVersionUID = -2313774782441238222L;
	
	protected JMenu menuOpcoes;
	protected JMenuItem itemConectar;
	protected JMenuItem itemIniciarPartida;
	
	protected InterfaceJogador interfaceJogador;
	
	
	public BarraDeTarefas(InterfaceJogador interfaceJogador) {
		this.interfaceJogador = interfaceJogador;
		
		this.menuOpcoes = new JMenu("Op��es");
		
		this.itemConectar = new JMenuItem("Conectar");
		this.itemConectar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nome = getInterfaceJogador().requisitarNome();
				getInterfaceJogador().conectar(nome);
				itemIniciarPartida.setEnabled(true);
				
			}
		});
		
		
		this.itemIniciarPartida = new JMenuItem("Iniciar Partida");
		this.itemIniciarPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getInterfaceJogador().iniciarPartida();
				} catch (NetworkException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		this.itemIniciarPartida.setEnabled(false);
		
		this.menuOpcoes.add(this.itemConectar);
		this.menuOpcoes.add(this.itemIniciarPartida);
		this.add(menuOpcoes);
		
	}
	
	public InterfaceJogador getInterfaceJogador(){
		return this.interfaceJogador;
	}
	

	

}
