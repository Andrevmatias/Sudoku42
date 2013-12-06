package br.edu.ufsc.sudoku42.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import br.edu.ufsc.sudoku42.network.NetworkException;

public class BarraDeTarefas extends JMenuBar {
	
	private static final long serialVersionUID = -2313774782441238222L;
	
	protected JMenu menuOpcoes;
	
	protected JMenuItem itemConectar;
	protected JMenuItem itemIniciarPartida;
	protected JMenuItem itemDesistir;
	protected JMenuItem itemDesconectar;
	
	protected InterfaceJogador interfaceJogador;
	
	
	public BarraDeTarefas(InterfaceJogador interfaceJogador) {
		this.interfaceJogador = interfaceJogador;
		
		this.instanciarAtributos();
		this.adicionarActionListener();
		this.setValorDefaultAtributos();
		this.adicionarComponentes();
	}
	
	
	public void instanciarAtributos(){
		this.menuOpcoes = new JMenu("Op��es");
		
		this.itemConectar = new JMenuItem("Conectar");
		this.itemIniciarPartida = new JMenuItem("Iniciar Partida");
		this.itemDesistir = new JMenuItem("Desistir");
		this.itemDesconectar = new JMenuItem("Desconectar");
		
	}
	
	public void adicionarActionListener(){
		this.itemConectar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nome = getInterfaceJogador().requisitarNome();
				getInterfaceJogador().conectar(nome);
				itemIniciarPartida.setEnabled(true);
				itemDesconectar.setEnabled(true);
				
			}
		});
		
		
		this.itemIniciarPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getInterfaceJogador().iniciarPartida();
					itemDesistir.setEnabled(true);
				} catch (NetworkException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		this.itemDesconectar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: desistir
				
			}
		});
		
		this.itemDesistir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: desistir
				
			}
		});
		
		
	}
	
	public void adicionarComponentes(){
		this.menuOpcoes.add(this.itemConectar);
		this.menuOpcoes.add(this.itemIniciarPartida);
		
		this.menuOpcoes.add(new JSeparator());
		
		this.menuOpcoes.add(this.itemDesistir);
		this.menuOpcoes.add(this.itemDesconectar);
		
		this.add(menuOpcoes);
		
	}
	
	public void setValorDefaultAtributos(){
		this.itemIniciarPartida.setEnabled(false);
		this.itemDesistir.setEnabled(false);
		this.itemDesconectar.setEnabled(false);
	}
	
	
	public InterfaceJogador getInterfaceJogador(){
		return this.interfaceJogador;
	}
	

	

}
