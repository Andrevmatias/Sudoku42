package br.edu.ufsc.sudoku42.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

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
		this.menuOpcoes = new JMenu("Opcoes");
		
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
				interfaceJogador.conectar(nome);
				itemConectar.setEnabled(false);
				itemIniciarPartida.setEnabled(true);
				itemDesistir.setEnabled(false);
				itemDesconectar.setEnabled(true);
			}
		});
		
		
		this.itemIniciarPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getInterfaceJogador().iniciarPartida();
				itemConectar.setEnabled(false);
				itemIniciarPartida.setEnabled(false);
				itemDesistir.setEnabled(true);
				itemDesconectar.setEnabled(true);	
			}
		});
		
		this.itemDesconectar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getInterfaceJogador().desconectar();
				setValorDefaultAtributos();
			}
		});
		
		this.itemDesistir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getInterfaceJogador().desistir();
				setValorDefaultAtributos();
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
		this.itemConectar.setEnabled(true);
		this.itemIniciarPartida.setEnabled(false);
		this.itemDesistir.setEnabled(false);
		this.itemDesconectar.setEnabled(false);
	}
	
	
	public InterfaceJogador getInterfaceJogador(){
		return this.interfaceJogador;
	}


	public void mudarParaModoPartidaEmAndamento() {
		this.itemIniciarPartida.setEnabled(false);
		this.itemDesistir.setEnabled(true);
		this.itemDesconectar.setEnabled(true);
	}
	
	

}
