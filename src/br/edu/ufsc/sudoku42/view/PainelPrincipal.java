package br.edu.ufsc.sudoku42.view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PainelPrincipal extends JPanel {
	
private static final long serialVersionUID = -4274691600196025428L;
	
	protected PainelTabuleiro painelTabuleiro; 
	protected PainelJogador painelJogadorLocal;
	protected PainelJogador painelJogadorRemoto;
	
	protected JPanel painelTempo;
	protected InterfaceJogador interfaceJogador;
	
	public PainelPrincipal( InterfaceJogador interfaceJogador) {
		
		this.interfaceJogador = interfaceJogador;
		this.painelTabuleiro = new PainelTabuleiro(interfaceJogador);
		this.add(painelTabuleiro);
		
		this.painelJogadorLocal = new PainelJogador("Jogador 1");
		this.painelJogadorRemoto = new PainelJogador("Jogador 2");
		
		this.definirPainelTempo();
		this.add(painelTempo);
		
	}
	
	public void definirPainelTempo(){
		this.painelTempo = new JPanel();
		this.painelTempo.setLayout(new BoxLayout(painelTempo, BoxLayout.PAGE_AXIS));
		
		this.painelTempo.add(painelJogadorLocal);
		
		this.painelTempo.add(Box.createRigidArea(new Dimension(90,90)));
		this.painelTempo.add(painelJogadorRemoto);
	}

	public PainelJogador getPainelJogadorLocal() {
		return painelJogadorLocal;
	}

	public PainelJogador getPainelJogadorRemoto() {
		return painelJogadorRemoto;
	}
	
	public PainelTabuleiro getPainelTabuleiro(){
		return this.painelTabuleiro;
	}
}
