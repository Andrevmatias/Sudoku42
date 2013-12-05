package br.edu.ufsc.sudoku42.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.ufsc.sudoku42.model.Tabuleiro;

public class InterfaceJogador extends JFrame {
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.pack();
		this.getContentPane().validate();
		this.getContentPane().repaint();
		
	}
	
	public String requisitarNome(){
		String retorno = JOptionPane.showInputDialog("Qual seu nome:");
		return retorno;
	}
	
	public void startRelogioJogadorLocal(){
		this.painelPrincipal.getPainelJogadorLocal().iniciarTimer();
	}
	
	public void startRelogioJogadorRemoto(){
		this.painelPrincipal.getPainelJogadorRemoto().iniciarTimer();
	}
	
	public void pararTempoJogadorLocal(){
		this.painelPrincipal.getPainelJogadorLocal().pausarTimer();
	}
	

	public void pararTempoJogadorRemoto(){
		this.painelPrincipal.getPainelJogadorLocal().pausarTimer();
	}
	
	public void conectar(String nome){
		try{
		
			this.tabuleiro.conectar(nome);	
		} catch (Exception e){
			
			//TODO: notificar erro
			e.printStackTrace();
		}
	}

	public void finalizarPartida() {
		// TODO Auto-generated method stub
	}
}
