package br.edu.ufsc.sudoku42.view;

import java.awt.Color;

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
		super("Sudoku42");
		this.tabuleiro = tabuleiro;
		
		this.painelPrincipal = new PainelPrincipal(this);
		this.barraDeTarefas = new BarraDeTarefas(this);
		
		this.setContentPane(painelPrincipal);
		this.setJMenuBar(barraDeTarefas);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		
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
	
	public void configurarPainelJogadorLocal(String nome, Color cor){
		this.painelPrincipal.getPainelJogadorLocal().configurarPainelJogador(nome, cor);
	}
	
	public void configurarPainelJogadorRemoto(String nome, Color cor){
		this.painelPrincipal.getPainelJogadorRemoto().configurarPainelJogador(nome, cor);
	}
	
	public void dispararRelogioJogadorLocal(int segundosRestantes){
		this.painelPrincipal.getPainelJogadorLocal().dispararTimer(segundosRestantes);
	}
	
	public void dispararRelogioJogadorRemoto(int segundosRestantes){
		this.painelPrincipal.getPainelJogadorRemoto().dispararTimer(segundosRestantes);
	}
	
	public int pararRelogioJogadorLocal(){
		return this.painelPrincipal.getPainelJogadorLocal().pausarTimer();
	}
	
	public int pararRelogioJogadorRemoto(){
		return this.painelPrincipal.getPainelJogadorRemoto().pausarTimer();
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
		painelPrincipal.getPainelTabuleiro().limparCampos();
		painelPrincipal.getPainelTabuleiro().bloquearCampos();
		
		painelPrincipal.getPainelJogadorLocal().reiniciarPainel("Jogador 1");
		painelPrincipal.getPainelJogadorRemoto().reiniciarPainel("Jogador 2");
		
		this.barraDeTarefas.setValorDefaultAtributos();
		this.painelPrincipal.getPainelTabuleiro().limparCampos();
	}
	
	protected void realizarLance(int linha, int coluna) throws NetworkException, CampoOcupadoException{
		tabuleiro.ocuparPosicao(linha, coluna);
	}
	
	public void iniciarPartida(){
		try {
			tabuleiro.solicitarInicioDePartida();
		} catch (NetworkException e) {
			this.notificarErro(e.getMessage());
		}
	}
	

	public void notificarMensagemServidor(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void notificarVencedor(String nome) {
		JOptionPane.showMessageDialog(this, nome + " Venceu!", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void atualizarPontuacaoJogadorLocal(int pontuacao){
		this.painelPrincipal.getPainelJogadorLocal().setPontuacao(pontuacao);
	}

	public void atualizarPontuacaoJogadorRemoto(int pontuacao) {
		this.painelPrincipal.getPainelJogadorRemoto().setPontuacao(pontuacao);
	}
	
	public void desconectar() {
		if(JOptionPane.showConfirmDialog(this, "Tem certeza que quer encerrar a conexão? Seu adversário será o vencedor") == 0){
			this.encerrarPartida();
		}
	}

	public void desistir() {
		if(JOptionPane.showConfirmDialog(this, "Tem certeza que quer desistir? A conexão será encerrada") == 0){
			this.encerrarPartida();
		}
	}
	
	public void encerrarPartida(){
		try {
			this.tabuleiro.desistir();
			this.tabuleiro.desconectar();
		} catch (NetworkException e) {
			e.getMessage();
		}
	}

	public void desbloquearCampos() {
		this.painelPrincipal.getPainelTabuleiro().desbloquearCampos();
	}
	
	public void bloquearCampos() {
		this.painelPrincipal.getPainelTabuleiro().bloquearCampos();
		this.pack();
	}
	
	public void ocuparCampo(int linha, int coluna, int valor, Color cor)
	{
		this.painelPrincipal.getPainelTabuleiro().ocuparCampo(linha, coluna, valor, cor);
		this.pack();
	}

	public void mudarParaModoPartidaEmAndamento() {
		this.barraDeTarefas.mudarParaModoPartidaEmAndamento();
	}

	public void notificarEmpate() {
		JOptionPane.showMessageDialog(this, "Houve um empate!", "Empate", JOptionPane.WARNING_MESSAGE);
	}
	
	public void notificarRelogioZerado(){
			try {
				tabuleiro.tratarTempoEsgotado();
				this.finalizarPartida();
			} catch (NetworkException e) {
				this.notificarErro("Um erro inesperado ocorreu");
			}
	}

	public void notificarTempoEsgotado(String nome) {
		JOptionPane.showMessageDialog(this, "Tempo esgotado", "Tempo Esgotado", JOptionPane.WARNING_MESSAGE);
		
	}
	
}
