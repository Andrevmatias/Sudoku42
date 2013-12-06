package br.edu.ufsc.sudoku42.view;

import java.util.HashMap;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.ufsc.sudoku42.model.CampoOcupadoException;
import br.edu.ufsc.sudoku42.model.Tabuleiro;
import br.edu.ufsc.sudoku42.network.NetworkException;

public class InterfaceJogador extends JFrame {
	private static final long serialVersionUID = -4274691600196025428L;
	
	protected PainelPrincipal painelPrincipal;
	protected BarraDeTarefas barraDeTarefas;
	
	protected HashMap<String, PainelJogador> hashPainelJogador;
	
	protected Tabuleiro tabuleiro;
	
	public InterfaceJogador(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.hashPainelJogador = new HashMap<>();
		
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
	
	public int pararRelogio(){
		return this.hashPainelJogador.get(tabuleiro.getJogadorDoTurno()).pausarTimer();
	}
	
	public void dispararRelogio(int segundosRestantes){
		this.hashPainelJogador.get(tabuleiro.getJogadorDoTurno()).dispararTimer(segundosRestantes);
	}
	
	public void conectar(String nome){
		try{
			this.tabuleiro.conectar(nome);	
		} catch (NetworkException e){
			notificarErro(e.getMessage());
		}
	}

	public void finalizarPartida() {
		Set<String> chaves = hashPainelJogador.keySet();
		
		for(String i: chaves){
			this.hashPainelJogador.get(i).pausarTimer();
		}
	}
	
	public void setHashPainelJogador(HashMap<String, PainelJogador> hashPainelJogador){
		this.hashPainelJogador = hashPainelJogador;
	}
	
	protected void realizarLance(int linha, int coluna) throws NetworkException, CampoOcupadoException{
		tabuleiro.ocuparPosicao(linha, coluna);
	}
	
	public void iniciarPartida() throws NetworkException{
		tabuleiro.solicitarInicioDePartida();
		
		this.painelPrincipal.getPainelJogadorLocal().setNome(tabuleiro.getJogadorLocal().getNome());
		this.painelPrincipal.getPainelJogadorRemoto().setNome(tabuleiro.getJogadorRemoto().getNome());

	}

	public void notificarMensagemServidor(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void notificarVencedor(String nome) {
		JOptionPane.showMessageDialog(this, nome + " Venceu!", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
	}
}
