package br.edu.ufsc.sudoku42.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.ufsc.sudoku42.model.CampoOcupadoException;
import br.edu.ufsc.sudoku42.model.Tabuleiro;
import br.edu.ufsc.sudoku42.network.NetworkException;

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
	
	public void notificarErro(String erro){
		JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public String requisitarNome(){
		String retorno = JOptionPane.showInputDialog("Qual seu nome:");
		return retorno;
	}
	
	public void dispararRelogioJogadorLocal(int segundosRestantes){
		this.painelPrincipal.getPainelJogadorLocal().iniciarTimer(segundosRestantes);
	}
	
	public void dispararRelogioJogadorRemoto(int segundosRestantes){
		this.painelPrincipal.getPainelJogadorRemoto().iniciarTimer(segundosRestantes);
	}
	
	public int pararRelogioJogadorLocal(){
		return this.painelPrincipal.getPainelJogadorLocal().pausarTimer();
	}
	
	public int pararRelogioJogadorRemoto(){
		return this.painelPrincipal.getPainelJogadorLocal().pausarTimer();
	}
	
	public void conectar(String nome){
		try{
			this.tabuleiro.conectar(nome);	
		} catch (NetworkException e){
			notificarErro(e.getMessage());
		}
	}

	public void finalizarPartida() {
		pararRelogioJogadorLocal();
		pararRelogioJogadorRemoto();
	}
	
	protected void realizarLance(int linha, int coluna) throws NetworkException, CampoOcupadoException{
		tabuleiro.ocuparPosicao(linha, coluna);
	}
	
	public void solicitacaoDeInicioDePartida() throws NetworkException{
		tabuleiro.solicitarInicioDePartida();
	}

	public void notificarMensagemServidor(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void notificarVencedor(String nome) {
		JOptionPane.showMessageDialog(this, nome + " Venceu!", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
	}
}
