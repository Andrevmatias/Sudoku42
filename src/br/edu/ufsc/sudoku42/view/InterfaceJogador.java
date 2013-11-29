package br.edu.ufsc.sudoku42.view;

import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.ufsc.sudoku42.model.Tabuleiro;

public class InterfaceJogador extends JFrame {
	private final static Logger LOGGER = Logger.getLogger(InterfaceJogador.class.getName());
	private static final long serialVersionUID = -4274691600196025428L;
	
	protected PainelPrincipal painelPrincipal;
	protected BarraDeTarefas barraDeTarefas;
	
	protected Tabuleiro tabuleiro;
	
	public InterfaceJogador(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		this.painelPrincipal = new PainelPrincipal(this);
		this.barraDeTarefas = new BarraDeTarefas(this);
		
		this.setContentPane(painelPrincipal);
		this.setJMenuBar(barraDeTarefas);
		
		this.pack();
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
	
	public Tabuleiro getTabuleiro(){
		return this.tabuleiro;
	}
	
	public String requisitarNome(){
		String retorno = JOptionPane.showInputDialog("Qual seu nome:");
		return retorno;
	}
	
	public void conectar(String nome){
		try{
		
			this.tabuleiro.conectar(nome);	
		} catch (Exception e){
			
			//TODO: notificar erro
			e.printStackTrace();
		}
	}
}
